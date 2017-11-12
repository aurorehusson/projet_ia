import data.DataReading;

public class Main {
	
	public static void main(String[] args){
		
	DataReading dr = new DataReading();
	dr.Lecture();
	System.out.println(dr.dataTrain.get(0));
	System.out.println(dr.dataTest.get(50));
	}
}
