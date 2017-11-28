package data;

import java.util.ArrayList;

public class DataSorting {

	public ArrayList<ArrayList<ArrayList<Double>>> dataTrainSort;
	private ArrayList<ArrayList<Double>> dataTrain;
	
	public DataSorting(){
		DataReading dr = new DataReading();
		dr.Lecture();
		this.dataTrain = dr.dataTrain;
	}
	
	public void Tri(){
		//Initialisation
		dataTrainSort = new ArrayList<ArrayList<ArrayList<Double>>>();
		ArrayList<ArrayList<Double>> listeAnnee = new ArrayList<ArrayList<Double>>();
		
		//Initiailisation de la liste avec le premier element
		Double annee = dataTrain.get(0).get(0);
		ArrayList<Double> anneeListe = new ArrayList<Double>();
		anneeListe.add(annee);
		ArrayList<Double> element = dataTrain.get(0);
		listeAnnee.add(anneeListe);
		listeAnnee.add(element);
		dataTrainSort.add(listeAnnee);
		
		//Boucle sur tous les elements
		int i, j;
		for(i=1; i<dataTrain.size(); i++){
			annee = dataTrain.get(i).get(0);
			j = 0;
			boolean anneeTrouvee = false;
			while(j<dataTrainSort.size() && anneeTrouvee == false){
				if (dataTrainSort.get(j).get(0).get(0)==annee){
					anneeTrouvee = true;
					listeAnnee = dataTrainSort.get(j);
					dataTrainSort.remove(j);
					element = dataTrain.get(i);
					listeAnnee.add(element);
					dataTrainSort.add(listeAnnee);
				}
				j++;
			}
			if(anneeTrouvee == false){
				anneeListe = new ArrayList<Double>();
				anneeListe.add(annee);
				element = dataTrain.get(i);
				listeAnnee.add(anneeListe);
				listeAnnee.add(element);
				dataTrainSort.add(listeAnnee);
			}
		}
	}
}
