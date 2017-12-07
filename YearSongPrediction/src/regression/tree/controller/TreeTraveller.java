package regression.tree.controller;

import java.util.Iterator;

import regression.tree.controller.exception.UnknownTokenException;
import regression.tree.model.Node;
import regression.tree.model.RegressionTreeModel;
import regression.tree.model.TestingExample;
import regression.tree.model.label.Category;
import regression.tree.model.label.LabelEnum;
import regression.tree.model.label.Rule;

public class TreeTraveller {
	private RegressionTreeModel tree;
	
	public TreeTraveller(RegressionTreeModel tree) {
		this.tree = tree;
	}
	/**
	 * Result of the travel is store in the testingExample
	 * @param testingExample
	 * @throws UnknownTokenException
	 */
	public void travelTreeWithTestingExample(TestingExample testingExample) throws UnknownTokenException {
		Node node = getTree().getRoot();
		while (node.getLabel().getLabelTypeID() != LabelEnum.Category) {
			for (Iterator<Node> iterator = node.getChild().iterator(); iterator.hasNext();) {
				Node child = (Node) iterator.next();
				// get the argument on which we apply the rule
				if (child.getLabel().getLabelTypeID() == LabelEnum.Rule) {
					if (exampleFitRule(testingExample, child)) {
						node = child;
					}					
				} else if (child.getLabel().getLabelTypeID() == LabelEnum.Category) {
					node = child;
				}
			}
		}
		testingExample.setObtainedYear(((Category) node.getLabel()).getCategory());
	}
	
	private Boolean exampleFitRule(TestingExample testingExample, Node child) throws UnknownTokenException {
		Rule label = (Rule) child.getLabel();
		Double exampleValue = testingExample.getArgument(label.getArgumentNumber());
		Double valueToCompareTo = label.getValue();
		String compareSign = label.getCompareSign();
		switch (compareSign) {
			case "<":
				return exampleValue < valueToCompareTo;
			case "<=":
				return exampleValue <= valueToCompareTo;
			case ">":
				return exampleValue > valueToCompareTo;
			case ">=":
				return exampleValue >= valueToCompareTo;
			case "=":
				return exampleValue == valueToCompareTo;
			default:
				throw new UnknownTokenException(label.toPrettyString() + " rule can't be applied");
		}
	}
	
	private RegressionTreeModel getTree() {
		return tree;
	}
}
