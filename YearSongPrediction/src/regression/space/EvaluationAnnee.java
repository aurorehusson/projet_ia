package regression.space;

import java.util.ArrayList;

import org.apache.commons.math3.ml.distance.EuclideanDistance;

public class EvaluationAnnee {
	
	private double[][] beta;
	public ArrayList<ArrayList<Double>> dataTest;
	public ArrayList<Double[][]> dataTrain;
	public double min, max;
	
	public EvaluationAnnee(){
		Regression rg = new Regression();
		rg.droiteRegression();
		beta = rg.beta;
		this.dataTest = rg.dataTest;
		this.dataTrain = rg.dataTrain;
		this.min = rg.min;
		this.max = rg.max;
	}

	public double evaluerPoint(double[] point){
		
		/*for(int taillePoint=0; taillePoint<point.length; taillePoint++){
			point[taillePoint] = (point[taillePoint]-min)/(max-min);
		}*/
		//System.out.println(point.length);
		EuclideanDistance ed= new EuclideanDistance();
		double[] parameters = new double[90];
		double minimum=10000000;
		int indiceMin = 92;
		System.out.println("longueur beta" + beta.length);
		for (int nombreAnnee=0; nombreAnnee<beta.length; nombreAnnee++){
			if(this.beta[nombreAnnee]!=null){
				for(int indice=1; indice<this.beta[nombreAnnee].length; indice++){
					parameters[indice-1]=this.beta[nombreAnnee][indice];
				}
				/*System.out.println(nombreAnnee);*/
				/*if(nombreAnnee==16){
					System.out.println(ed.compute(parameters, point));
				}*/
				if(ed.compute(parameters, point)<minimum){
					minimum = ed.compute(parameters, point);
					indiceMin = nombreAnnee;
				}
				//System.out.println(indiceMin);
			}
		}
		return(this.dataTrain.get(indiceMin)[0][0]);
	}
	
}
