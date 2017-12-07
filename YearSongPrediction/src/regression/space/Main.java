package regression.space;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.ml.distance.*;

public class Main {
	
	public static double[] point;
	
	public static void main(String[] args) {
		
	/*DataReading dr = new DataReading();
	dr.Lecture();
	System.out.println(dr.dataTrain.get(0));
	System.out.println(dr.dataTest.get(50));*/
	
	/*Regression reg = new Regression();
	reg.droiteRegression();
	point = reg.point;
	System.out.println(point[0]);
	EuclideanDistance ed= new EuclideanDistance();
	double[] beta = new double[90];
	double minimum=100000;
	for (int nombreAnnee=0; nombreAnnee<reg.beta.length; nombreAnnee++){
		if(reg.beta[nombreAnnee]!=null){
			for(int indice=1; indice<reg.beta[nombreAnnee].length; indice++){
				beta[indice-1]=reg.beta[nombreAnnee][indice];
			}
			System.out.println(nombreAnnee);
			System.out.println(ed.compute(beta, point));
		}
	}*/
		
	EvaluationAnnee ea = new EvaluationAnnee();
	ArrayList<ArrayList<Double>> dataTest = ea.dataTest;
	double[] yreel = new double[dataTest.size()];
	double[] ycalc = new double[dataTest.size()];
	int nombreJuste = 0;
	for(int taille=0; taille<dataTest.size(); taille++){
		ArrayList<Double> element = dataTest.get(taille);
		yreel[taille] = element.remove(0);
		Double[] elts = new Double[element.size()];
		elts = element.toArray(elts);
		double[] point = ArrayUtils.toPrimitive(elts);
		ycalc[taille] = ea.evaluerPoint(point);
		if(yreel[taille]-3<=ycalc[taille]&&yreel[taille]+3>=ycalc[taille]){
			nombreJuste++;
		}
		System.out.println("Année : " + yreel[taille]);
		System.out.println("Prédiction : " + ycalc[taille]);
		System.out.println("");
		System.out.println("------------------------");
	}
	float rapport = new Float(nombreJuste)/new Float(dataTest.size());
	System.out.println("Bonnes réponses : " + nombreJuste/dataTest.size());
	}
	

}
