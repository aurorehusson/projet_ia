package regression.tree.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import regression.tree.controller.TreeTraveller;
import regression.tree.controller.exception.UnknownTokenException;
import regression.tree.model.TestingExample;

public abstract class AbstractYearsSongRegressionTreeID3 {
	
	protected static void playAllExamplesOnTree(TreeTraveller treeTraveller, String examplesFileName, String resultsFileName)
			throws FileNotFoundException, IOException, UnknownTokenException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(examplesFileName)));
		PrintWriter fichierSortie = new PrintWriter(new BufferedWriter(new FileWriter(resultsFileName)));
		ArrayList<Integer> expectedYear = new ArrayList<>();
		ArrayList<Integer> obtainedYear = new ArrayList<>();
		fichierSortie.println("Obtained Year\tExpected Year");
		String line = br.readLine();
		int i = 0;
		do {
			if (!line.startsWith("annee")) {
				i++;
				TestingExample testingExample = testOneStringLineExemple(treeTraveller, line);
				expectedYear.add(Integer.valueOf(testingExample.getExpectedYear()));
				obtainedYear.add(Integer.valueOf(testingExample.getObtainedYear()));
				// writing data logs
				fichierSortie.println(testingExample.getObtainedYear() + "\t" + testingExample.getExpectedYear());
			}
		} while ((line=br.readLine())!=null);
		br.close();
		// Data treatment
		ArrayList<Integer> gaps = new ArrayList<>();
		Integer sum = 0;
		Double accuracy = 0.;
		for (Iterator<Integer> iteratorExpected = expectedYear.iterator(); iteratorExpected.hasNext();) {
			Integer integerExpected = (Integer) iteratorExpected.next();
			Iterator<Integer> iteratorObtained = obtainedYear.iterator();
			Integer integerObtained = (Integer) iteratorObtained.next();
			// get positive gap
			if (integerExpected.equals(integerObtained)) {
				accuracy+= 1;
			}
			Integer gap = new Integer(Integer.max(integerExpected - integerObtained, integerObtained - integerExpected));
			sum = sum + gap;
			gaps.add(gap);
		}
		fichierSortie.println();
		Double double1 = new Double(sum/i);
		fichierSortie.println("Mean Gap : " + double1);
		fichierSortie.println("Accuracy : " + accuracy/i);
		System.out.println("Mean Gap : " + double1);
		System.out.println("Accuracy : " + accuracy/i);
		fichierSortie.close();
	}

	/**
	 * Method to run only one example presets 
	 * (Should be placed in unit tests but no time enough to make unit tests)
	 * 
	 * @param treeTraveller
	 * @throws UnknownTokenException
	 */
	
	protected static void testingOnOneExample(TreeTraveller treeTraveller) throws UnknownTokenException {
		String testLine = "\"2007\"	50,32201	6,71191	54,05607	-7,56020	-39,23615	-10,95161	-40,51556	-3,63441	6,58425	0,99665	3,44979	10,66774	14,39176	357,67468	256,26447	307,24135	384,22932	147,64072	198,98512	136,89055	153,26322	97,75724	60,09959	95,97517	6,13606	-109,31960	17,42367	-39,33058	1,42108	16,31536	29,46618	19,17606	37,76174	-26,96804	5,56925	5,97154	-62,61939	-23,64915	-94,03422	42,69713	5,95091	25,33652	-12,71863	-19,75615	-6,27169	1,26876	101,75777	42,07987	-34,22020	49,52080	-0,99609	36,08086	-4,20498	22,68037	11,44203	-19,96362	-11,89117	6,74539	88,24425	15,04845	-23,19230	16,07038	-13,68572	66,13375	-21,10724	35,13594	76,97871	-2,92617	18,24242	-8,74882	29,34742	41,68882	-45,17903	-60,31865	4,95446	-3,94878	-65,04633	1,24298	-17,36863	-9,40208	7,75069	1,77275	12,78713	18,00105	-1,47984	-20,11586	-9,00316	3,77736	-42,94888	0,05278";
		TestingExample testingExample = testOneStringLineExemple(treeTraveller, testLine);
		System.out.println("Exptected : " + testingExample.getExpectedYear() + "\n" + "Obtained : " + testingExample.getObtainedYear());
	}
	/**
	 * 
	 * @param treeTraveller
	 * @param testLine
	 * @return
	 * @throws UnknownTokenException
	 */

	protected static TestingExample testOneStringLineExemple(TreeTraveller treeTraveller, String testLine)
			throws UnknownTokenException {
		TestingExample testingExample = new TestingExample(testLine);
		treeTraveller.travelTreeWithTestingExample(testingExample);
		return testingExample;
	}

}
