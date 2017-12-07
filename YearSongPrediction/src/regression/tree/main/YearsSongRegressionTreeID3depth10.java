package regression.tree.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import regression.tree.controller.TreeBuilder;
import regression.tree.controller.TreeTraveller;
import regression.tree.controller.exception.BuildException;
import regression.tree.controller.exception.InvalidParsingException;
import regression.tree.controller.exception.UnknownTokenException;
import regression.tree.model.RegressionTreeModel;

public class YearsSongRegressionTreeID3depth10 extends AbstractYearsSongRegressionTreeID3 {


	/***
	 * Build tree from file and load all tests. 
	 * Run TanagraFileConverter.main to get YearPredictionMSDNew_test
	 * Generate the files of results
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RegressionTreeModel buildTanagraID3Tree = TreeBuilder.buildTanagraID3TreeDepth10();
			System.out.println(buildTanagraID3Tree.toPrettyString());
			TreeTraveller treeTraveller = new TreeTraveller(buildTanagraID3Tree);
			//testingOnOneExample(treeTraveller);
			playAllExamplesOnTree(treeTraveller, "YearPredictionMSDNew_test", "results/tanagra/results_ID3_depth10");	
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BuildException e) {
			e.printStackTrace();
		} catch (InvalidParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnknownTokenException e) {
			e.printStackTrace();
		}
	}
}
