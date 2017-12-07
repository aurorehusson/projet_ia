package regression.tree.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import regression.tree.controller.exception.BuildException;
import regression.tree.controller.exception.InvalidParsingException;
import regression.tree.model.Node;
import regression.tree.model.RegressionTreeModel;
import regression.tree.model.label.RootCategory;

public class TreeBuilder {
	
	/**
	 * Build a tree from a text format as :
	 * "root ....\n"
	 * "\t node1\n"
	 * "\t\t node2\n"
	 * "\t\t node3\n"
	 * "\t node4\n"
	 * "\t\t node5\n"
	 * "\t\t\t node6\n"
	 * "\t node7\n"
	 * 
	 * @param treeString
	 * @return
	 * @throws BuildException
	 * @throws InvalidParsingException 
	 */
	public static final RegressionTreeModel buildTreeFromString(String treeString) throws BuildException, InvalidParsingException {
		RegressionTreeModel regressionTreeModel = new RegressionTreeModel();
		String[] lines = treeString.split(System.lineSeparator());
		ArrayList<Node> lastNodeUsedAtDeepN = new ArrayList<>();
		
		//NodeAnalyser root = stringToNodeAnalyser(lines[0]);
		Node root = new Node(new RootCategory());
		lastNodeUsedAtDeepN.add(0, root);
		regressionTreeModel.setRoot(root);
		
		for (int i = 0; i < lines.length; i++) {
			NodeAnalyser node = stringToNodeAnalyser(lines[i]);
			lastNodeUsedAtDeepN.add(node.getDeepth(), node);
			if (lastNodeUsedAtDeepN.get(node.getDeepth()-1) == null) {
				throw new BuildException("There is a misstake in the description file of the tree! The build is canceled");
			}
			lastNodeUsedAtDeepN.get(node.getDeepth()-1).addChild(node);
		}
		return regressionTreeModel;
	}
	
	private static final NodeAnalyser stringToNodeAnalyser(String line) throws InvalidParsingException {
		return new NodeAnalyser(line);
	}
	
	public static final RegressionTreeModel buildTanagraID3TreeDepth10() throws FileNotFoundException, BuildException, InvalidParsingException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("treedata/tanagra/donnees_train_tanagra_ID3_depth10.txt")));
		StringBuilder fileContains = buildStringFromBuffer(br);
		return buildTreeFromString(fileContains.toString());
	}
	
	public static final RegressionTreeModel buildTanagraID3TreeDepth15() throws FileNotFoundException, BuildException, InvalidParsingException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("treedata/tanagra/donnees_train_tanagra_ID3_depth15.txt")));
		StringBuilder fileContains = buildStringFromBuffer(br);
		return buildTreeFromString(fileContains.toString());
	}
	
	public static final RegressionTreeModel buildWekaJ48Tree() throws FileNotFoundException, BuildException, InvalidParsingException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("treedata/weka/donnees_train_weka_j48_treeOnly_converted.txt")));
		StringBuilder fileContains = buildStringFromBuffer(br);
		return buildTreeFromString(fileContains.toString());
	}

	private static StringBuilder buildStringFromBuffer(BufferedReader br) {
		StringBuilder fileContains = new StringBuilder();
		String line = "";
		try {
			while ((line = br.readLine())!=null) {
				fileContains.append(line + System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContains;
	}

}
