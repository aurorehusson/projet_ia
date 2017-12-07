package data;

import java.util.ArrayList;

import org.omg.CORBA.DoubleSeqHelper;

public class DataSorting {

	//public ArrayList<ArrayList<ArrayList<Double>>> dataTrainSort;
	public ArrayList<Double[][]> dataTrainSort;
	public ArrayList<ArrayList<Double>> dataTest;
	public Double min, max;
	
	private ArrayList<ArrayList<Double>> dataTrain;
	
	public DataSorting(){
		DataReading dr = new DataReading();
		dr.Lecture();
		this.dataTrain = dr.dataTrain;
		this.dataTest=dr.dataTest;
		this.min=dr.min;
		this.max=dr.max;
	}
	
	public void Tri(){
		System.out.println("Tri");
		//Initialisation
		//dataTrainSort = new ArrayList<ArrayList<ArrayList<Double>>>();
		dataTrainSort = new ArrayList<Double[][]>();
		Double[][] listeAnnee = new Double[2][];
		
		//Initiailisation de la liste avec le premier element
		Double annee = dataTrain.get(0).get(0);
		Double[] anneeListe = new Double[1];
		anneeListe[0] = annee;
		ArrayList<Double> element = dataTrain.get(0);
		element.remove(0);
		listeAnnee[0]=anneeListe;
		Double[] elts = new Double[element.size()];
		elts = element.toArray(elts);
		listeAnnee[1]=elts;
		dataTrainSort.add(listeAnnee);
		
		//Boucle sur tous les elements
		int i, j;
		for(i=1; i<dataTrain.size(); i++){
			System.out.println("Tri :" +i);
			annee = dataTrain.get(i).get(0);
			j = 0;
			boolean anneeTrouvee = false;
			while(j<dataTrainSort.size() && anneeTrouvee == false){
				//System.out.println(annee);
				//System.out.println(dataTrainSort.get(j)[0][0]);
				if (dataTrainSort.get(j)[0][0].equals(annee)){
					anneeTrouvee = true;
					int n = dataTrainSort.get(j).length;
					//System.out.println(n);
					listeAnnee = new Double[n+1][];
					//System.out.println(listeAnnee.length);
					for(int indiceAnnee=0; indiceAnnee<n; indiceAnnee++){
						listeAnnee[indiceAnnee] = dataTrainSort.get(j)[indiceAnnee];
					}
					//System.out.println(listeAnnee.length);
					dataTrainSort.remove(j);
					element = dataTrain.get(i);
					element.remove(0);
					elts = new Double[element.size()];
					elts = element.toArray(elts);
					//System.out.println(elts.length);
					listeAnnee[n]=elts;
					dataTrainSort.add(listeAnnee);
				}
				j++;
			}
			if(anneeTrouvee == false){
				listeAnnee = new Double[2][];
				anneeListe = new Double[1];
				anneeListe[0] = annee;
				element = dataTrain.get(i);
				element.remove(0);
				listeAnnee[0] = anneeListe;
				elts = new Double[element.size()];
				elts = element.toArray(elts);
				listeAnnee[1] = elts;
				dataTrainSort.add(listeAnnee);
			}
		}
	}
}
