package regression.tree.parsing.file;

import java.io.IOException;
import java.io.PrintWriter;

public class WekaFileConverter extends FileConverter implements IFileConverter {

	public WekaFileConverter(String fileNameToConvert, String convertedFileName) {
		super(fileNameToConvert, convertedFileName);
	}
	
	@Override
	public void writeHeader(PrintWriter fichierSortie, PrintWriter fichierSortie2) {
		fichierSortie.println("@RELATION traindata");
		fichierSortie2.println("@RELATION testdata");
		fichierSortie.println("");
		fichierSortie2.println("");
		
		StringBuilder line = new StringBuilder();
		for (int i = 1; i <= 90; i++) {
			line = new StringBuilder();
			line.append("@ATTRIBUTE arg" + i + "\t REAL");
			fichierSortie.println(line);
			fichierSortie2.println(line);
		}
		
		StringBuilder classLine = new StringBuilder();
		classLine.append("@ATTRIBUTE class	{");
		for (int i = 1922; i <= 2010; i++) {
			classLine.append(i+",");
		}
		classLine.append("2011}");
		fichierSortie.println(classLine);
		fichierSortie2.println(classLine);
		
		fichierSortie.println("");
		fichierSortie2.println("");
		fichierSortie.println("@DATA");
		fichierSortie2.println("@DATA");
	}

	@Override
	public String applyRegexOnLine(String line) {
		String year = line.substring(0, 4);
		String newLine = line.substring(5)+","+year;
		return newLine;
	}

	public static void main(String[] args) {
		WekaFileConverter fileConverteur = new WekaFileConverter("/home/aurore/Bureau/YearPredictionMSD.txt","/home/aurore/Bureau/YearPredictionWeka");
		try {
			fileConverteur.executeRewriting();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
