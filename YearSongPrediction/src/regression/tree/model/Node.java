package regression.tree.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import regression.tree.model.label.ILabel;
/**
 * Node of a tree
 * 
 * @author jorda
 *
 */
public class Node {
	
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private static final String TAB = "\t";
	private Collection<Node> child;
	private Node parent;
	
	private ILabel category;
	
	/**
	 * 
	 * 
	 * @param category
	 */
	
	public Node(ILabel category) {
		parent = null;
		child = new ArrayList<>();
		this.category = category;
	}
	/**
	 * 
	 * 
	 * @param parent
	 * @param category
	 */
	
	public Node(Node parent, ILabel category) {
		this.parent = parent;
		child = new ArrayList<>();
		this.category = category;
	}
	/**
	 * @return if this node is a root
	 * 
	 */
	public Boolean isRoot() {
		return parent == null;
	}
	/**
	 * 
	 * @return if this node is a leaf
	 */
	public Boolean isLeaf() {
		return child.size() == 0;
	}
	
	public ILabel getLabel() {
		return category;
	}
	
	protected void setLabel(ILabel label) {
		category = label;
	}
	
	public void addChild(Node child) {
		this.child.add(child);
	}
	
	public int getDeepth() {
		return parent==null ? 0 : parent.getDeepth() + 1;
	}

	/**
	 * Ca devrait pas être là mais bon on a pas le temsp de réarchitecturer
	 * @return
	 */
	public StringBuilder toPrettyString() {
		StringBuilder childsb = new StringBuilder();
		for (Iterator<Node> iterator = child.iterator(); iterator.hasNext();) {
			Node node = (Node) iterator.next();
			childsb.append(node.toPrettyString() + LINE_SEPARATOR);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < getDeepth(); i++) {
			sb.append(TAB);
		}
		sb.append(getLabel().toPrettyString() + LINE_SEPARATOR);
		return sb.append(childsb);
	}
	
	
}
