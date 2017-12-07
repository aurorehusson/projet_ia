package results;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadResults {
	/**
	 * 
	 * 
	 */
	private String dataPath;
	private String resultsPath;
	ArrayList<ArrayList<Integer>> resultsTable; // [year, nb_true, nb_results_true, nb_results_false]

	public ReadResults(String dataPath, String resultsPath) {
		this.dataPath = dataPath;
		this.resultsPath = resultsPath;
		this.resultsTable = new ArrayList<ArrayList<Integer>>();
	}
	
	int index = 0;
	
	// do a recursive dichotomous search in a table
	// add some entries to the table when needed
	private int dichoSearch(int year, int nbinf, int nbsup) {
	    if (nbinf == nbsup) {
	    	if (resultsTable.size() == 0 || year < resultsTable.get(nbinf).get(0)) {
	    		ArrayList<Integer> var = new ArrayList<Integer>();
		    	var.add(year);
		    	var.add(0);
		    	var.add(0);
		    	var.add(0);
		    	resultsTable.add(nbinf,var);
	    		return nbinf;
	    	} else if (year == resultsTable.get(nbinf).get(0)) {
	    		return nbinf;
	    	} else {
		    	ArrayList<Integer> var = new ArrayList<Integer>();
		    	var.add(year);
		    	var.add(0);
		    	var.add(0);
		    	var.add(0);
	    		resultsTable.add(nbinf+1,var);
	    		return nbinf+1;
	    	}
	    } else {
	        index = (nbsup+nbinf) / 2;
	        if (year == resultsTable.get(index).get(0)) {
	            return index;
	        } else if (year < resultsTable.get(index).get(0)) {
	        	if (index-1 >= nbinf) {
	        		return dichoSearch(year, nbinf, index-1);
	        	} else {
	        		return dichoSearch(year, nbinf, nbinf);
	        	}
	        } else {
	        	if (index+1 <= nbsup) {
	        		return dichoSearch(year, index+1, nbsup);
	        	} else {
	        		return dichoSearch(year, nbsup, nbsup);
	        	}
	        }
	    }
	}
	
	public void analyseResults() throws IOException {
		// read file from data file
		BufferedReader dataBf = new BufferedReader(new FileReader(dataPath));
		String dataLine = dataBf.readLine();
		
		// read line from results file
		BufferedReader resultsBf = new BufferedReader(new FileReader(resultsPath));
		String resultsLine = resultsBf.readLine();
		
		// declare some variables
		String dataYear = "";
		String resultsYear = "";
		int dataIndex, resultsIndex, valueTrue, valueFound = 0;
		
		// read each line
		while (dataLine != null) {
			// catch years
			dataYear = dataLine.substring(0, 4);
			resultsYear = resultsLine.substring(0, 4);
			
			if (dataYear.equals(resultsYear)) {
				// find index of year
				if (resultsTable.size() == 0) {
					dataIndex = dichoSearch(Integer.parseInt(dataYear), 0, 0);
				} else {
					dataIndex = dichoSearch(Integer.parseInt(dataYear), 0, resultsTable.size()-1);
				}
				
				// increase number of true values for this year
				valueTrue = resultsTable.get(dataIndex).get(1);
				resultsTable.get(dataIndex).set(1,valueTrue+1);
				
				// increase number of true found values for this year
				valueFound = resultsTable.get(dataIndex).get(2);
				resultsTable.get(dataIndex).set(2,valueFound+1);
			} else {
				// find index of data year
				if (resultsTable.size() == 0) {
					dataIndex = dichoSearch(Integer.parseInt(dataYear), 0, 0);
				} else {
					dataIndex = dichoSearch(Integer.parseInt(dataYear), 0, resultsTable.size()-1);
				}
				
				// increase number of true values for data year
				valueTrue = resultsTable.get(dataIndex).get(1);
				resultsTable.get(dataIndex).set(1,valueTrue+1);
				
				// find index of results year
				if (resultsTable.size() == 0) {
					resultsIndex = dichoSearch(Integer.parseInt(resultsYear), 0, 0);
				} else {
					resultsIndex = dichoSearch(Integer.parseInt(resultsYear), 0, resultsTable.size()-1);
				}
				
				// increase number of false found values for results year
				valueFound = resultsTable.get(resultsIndex).get(3);
				resultsTable.get(resultsIndex).set(3,valueFound+1);
			}
			// read next lines
			dataLine = dataBf.readLine();		
			resultsLine = resultsBf.readLine();
		}	
		dataBf.close();
		resultsBf.close();
		
		System.out.println(resultsTable);
	}
	
	public double recall() {
		double recall = 0;
		int n = resultsTable.size();
		for (ArrayList<Integer> yearResults : resultsTable) {
			if (yearResults.get(1) != 0) {
				recall += yearResults.get(2)/yearResults.get(1);
			} else {
				System.out.println("Warning : division by 0 avoided in recall for year "+yearResults.get(0));
				n --;
			}
		}
		return recall/n;
	}
	
	public double accuracy() {
		double accuracy = 0;
		int n = resultsTable.size();
		for (ArrayList<Integer> yearResults : resultsTable) {
			if (yearResults.get(2)+yearResults.get(3) != 0) {
				accuracy += yearResults.get(2)/(yearResults.get(2)+yearResults.get(3));
			} else {
				System.out.println("Warning : division by 0 avoided in accuracy for year "+yearResults.get(0));
				n --;
			}
		}
		return accuracy/n;
	}
	
	public static void main(String[] args) throws IOException {
		ReadResults results = new ReadResults("YearPredictionMSDNew_test","results/weka/results_Weka_j48");
		results.analyseResults();
		System.out.println("accuracy : "+results.accuracy());
		System.out.println("recall : "+results.recall());
	}


}
