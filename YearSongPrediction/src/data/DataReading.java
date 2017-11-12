package data;

import java.io.*;
import java.util.ArrayList;

public class DataReading{
	
	static String fichier = "YearPredictionMSD.txt";
	public ArrayList<ArrayList<Double>> dataTrain, dataTest;
	
	public void Lecture (){
		try{
			dataTrain = new ArrayList<ArrayList<Double>>();
			dataTest = new ArrayList<ArrayList<Double>>();
			InputStream ips=new FileInputStream(fichier);
			if(ips!= null){
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				int i, j;
				i = 0;
				while ((ligne=br.readLine())!=null){
					j = 0;
					ArrayList<Double> simpleList = new ArrayList<Double>();
					String[] elements = ligne.split(",");
					while(j<elements.length){
						simpleList.add(new Double(elements[j]));
						j++;
					}
					if(i<463715){
						dataTrain.add(simpleList);
					} else {
						dataTest.add(simpleList);
					}
					i++;
				}
				br.close(); 
			} else {
				System.out.println("Impossible d'ouvrir le fichier");
			}
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}

}
