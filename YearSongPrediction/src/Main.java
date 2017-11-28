import data.DataReading;
import data.DataSorting;

public class Main {
	
	public static void main(String[] args){
		
	/*DataReading dr = new DataReading();
	dr.Lecture();
	System.out.println(dr.dataTrain.get(0));
	System.out.println(dr.dataTest.get(50));*/
		
	DataSorting ds = new DataSorting();
	ds.Tri();
	System.out.println(ds.dataTrainSort.get(0).get(3));
	
	}
}
