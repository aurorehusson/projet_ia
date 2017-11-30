package parsing.file;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Parser parser = new Parser("YearPredictionMSD.txt", ",");
		try {
			parser.parseFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<String> argumentNameLine = new ArrayList<>();
		argumentNameLine.add("Annee");
		for (int i = 1; i <= 90; i++) {
			argumentNameLine.add("arg" + i);
			
		}
		try {
			parser.writeParseLine("output.txt", '\t', ',', argumentNameLine);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
