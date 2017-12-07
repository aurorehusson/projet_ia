package regression.tree.main;

import java.io.FileNotFoundException;

import regression.tree.controller.TreeBuilder;
import regression.tree.controller.exception.BuildException;
import regression.tree.controller.exception.InvalidParsingException;
import regression.tree.model.RegressionTreeModel;

public class YearsSong {

	public static void main(String[] args) {
		RegressionTreeModel buildTanagraID3Tree;
		try {
			buildTanagraID3Tree = TreeBuilder.buildTanagraID3Tree();
			System.out.println(buildTanagraID3Tree.toPrettyString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BuildException e) {
			e.printStackTrace();
		} catch (InvalidParsingException e) {
			e.printStackTrace();
		}

	}

}
