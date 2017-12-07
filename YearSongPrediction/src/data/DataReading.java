package data;

import java.io.*;
import java.util.ArrayList;

public class DataReading {

	static String fichier = "YearPredictionMSD.txt";
	public ArrayList<ArrayList<Double>> dataTrain, dataTest;
	public Double min, max;

	public void Lecture() {
		min = Double.MAX_VALUE;
		max = Double.MIN_VALUE;
		dataTrain = new ArrayList<ArrayList<Double>>();
		dataTest = new ArrayList<ArrayList<Double>>();
		try {
			InputStream ips = new FileInputStream(fichier);

			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			int i, j;
			i = 0;
			System.out.println("lecture");
			while ((ligne = br.readLine()) != null) {

				j = 0;
				ArrayList<Double> simpleList = new ArrayList<Double>();
				String[] elements = ligne.split(",");
				while (j < elements.length) {
					if (new Double(elements[j]) < min) {
						min = new Double(elements[j]);
					}
					if (new Double(elements[j]) > max) {
						max = new Double(elements[j]);
					}
					simpleList.add(new Double(elements[j]));
					j++;
				}
				if (i < 463715) {
					dataTrain.add(simpleList);
				} else {
					dataTest.add(simpleList);
				}
				i++;
			}
			br.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
