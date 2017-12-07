package regression.space;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

import data.DataSorting;

public class Regression {
	
	public ArrayList<Double[][]> dataTrain;
	public ArrayList<ArrayList<Double>> dataTest;
	private FileWriter fw;
	public double[][] beta;
	public double[][] residuals;
	public int points = 0;
	public double[] point;
	public Double min, max;
	
	public Regression(){
		DataSorting ds = new DataSorting();
		ds.Tri();
		dataTrain = ds.dataTrainSort;
		this.dataTest = ds.dataTest;
		this.min = ds.min;
		this.max = ds.max;
		
		try {
			fw = new FileWriter("Results.txt", false);
		} catch (IOException e) {
			e.printStackTrace();
		} 	
	}
	public void addLog(String str) {
		try {
			fw.write(str+"\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void droiteRegression(){
		int i;
		beta = new double[dataTrain.size()][]; 
		residuals = new double[dataTrain.size()][];
		for(i=0; i<dataTrain.size();i++){
			
			OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
			//int nombrePoints = dataTrain.get(0).length;
			
			Double[][] elements;			
			elements = dataTrain.get(i);
			
			/*for(int sizeE=0; sizeE<elements.length; sizeE++){
				for(int nbElement=0; nbElement<elements[sizeE].length; nbElement++){
					elements[sizeE][nbElement] = (elements[sizeE][nbElement] - min)/(max-min);
				}
			}*/
			
			double[] y = new double[elements.length-1/*nombrePoints-1*/];
			double annee = dataTrain.get(i/*0*/)[0][0];
			/*for(int j=0; j<nombrePoints-1; j++){
				y[j]=annee;
			}*/
			
			double[][] doubles = new double[elements.length-1][];
			
			for (int b = 1; b<elements.length; b++){
				doubles[b-1]=ArrayUtils.toPrimitive(elements[b]);
				/*new*/y[b-1]=(annee-min)/(max-min);
			}
			/*if(i==45){
				point = doubles[0];
				System.out.println(annee);
			}*/
			if(doubles.length >=90){
			
				addLog("----------");
				addLog("Annee : "+y[0]);
				addLog("nbr de points : "+doubles.length);
				points += doubles.length;
				addLog("doubles[0]"+doubles[0][0]);
				addLog("index : "+i);
			regression.newSampleData(y,doubles);
			regression.setNoIntercept(true);
			regression.newSampleData(y,doubles);
			beta[i] = regression.estimateRegressionParameters();
			residuals[i] = regression.estimateResiduals();
			addLog("");
			addLog("beta[i][1] : "+ beta[i][42]);
			addLog("residuals[i][0] : "+residuals[i][0] );
			addLog("beta[i].length : "+beta[i].length);
			addLog("residuals[i].length : "+residuals[i].length);
			addLog("----------");
			}
			else{
				addLog("----------");
				addLog("Annee : "+y[0]);
				addLog("ERREUR - PAS ASSEZ DE POINTS");
				points += doubles.length;
				addLog("nbr de points : "+doubles.length);
				addLog("----------");
			}
				
			
		}
		System.err.println(points);
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 

}
