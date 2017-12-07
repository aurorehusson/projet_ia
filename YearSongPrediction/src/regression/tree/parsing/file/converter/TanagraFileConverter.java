package regression.tree.parsing.file.converter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/**
 * 
 * @author jordan
 *
 */
public class TanagraFileConverter extends DataFileConverter implements IFileConverter {
	
	private Collection<String> initialChar;
	private Collection<String> remplacementChar;

	public TanagraFileConverter(String fileNameToConvert, String convertedFileName, Collection<String> initialChar,
			Collection<String> remplacementChar) {
		super(fileNameToConvert, convertedFileName);
		this.setInitialChar(initialChar);
		this.setRemplacementChar(remplacementChar);
	}

	public void writeHeader(PrintWriter fichierSortie, PrintWriter fichierSortie2) {
		StringBuilder firstLine = new StringBuilder();
		firstLine.append("annee\t");
		for (int i = 1; i <= 90; i++) {
			firstLine.append("arg" + i + "\t");
		}
		fichierSortie.println(firstLine);
		fichierSortie2.println(firstLine);
	}

	public String applyRegexOnLine(String line) throws FormatException {
		String newLine = line;
		Iterator<String> iteratorNew = getRemplacementChar().iterator();
		for (Iterator<String> iteratorOld = getInitialChar().iterator(); iteratorOld.hasNext();) {
			String initialChar = (String) iteratorOld.next();
			String replacementChar = (String) iteratorNew.next();
			newLine = newLine.replaceAll(initialChar, replacementChar);
		}
		// Set the year between quote
		newLine = "\"" + newLine;
		newLine = newLine.replaceFirst("\t", "\"\t");
		if (newLine.split("\t").length != 91) {
			throw new FormatException();
		}
		return newLine;
	}
	
	/**
	 * @return the remplacementChar
	 */
	private Collection<String> getRemplacementChar() {
		return remplacementChar;
	}

	/**
	 * @param remplacementChar the remplacementChar to set
	 */
	private void setRemplacementChar(Collection<String> remplacementChar) {
		this.remplacementChar = remplacementChar;
	}

	/**
	 * @return the initialChar
	 */
	private Collection<String> getInitialChar() {
		return initialChar;
	}

	/**
	 * @param initialChar the initialChar to set
	 */
	private void setInitialChar(Collection<String> initialChar) {
		this.initialChar = initialChar;
	}
	
	/*
	 * Lauch build of file, so it can be read in Tanagra
	 * 
	 */
	public static void main(String[] args) {
		Collection<String> oldChars = new ArrayList<>();
		oldChars.add(",");
		oldChars.add("\\.");
		Collection<String> newChars = new ArrayList<>();
		newChars.add('\t'+"");
		newChars.add(",");
		
		TanagraFileConverter fileConverteur = new TanagraFileConverter("YearPredictionMSD.txt", "YearPredictionMSDNew",oldChars,newChars);
		try {
			fileConverteur.executeRewriting();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
