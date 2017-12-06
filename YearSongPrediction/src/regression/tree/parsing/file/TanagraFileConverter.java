package regression.tree.parsing.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.FormatterClosedException;
import java.util.Iterator;
/**
 * 
 * @author jordan
 *
 */
public class TanagraFileConverter {
	private String fileNameToConvert;
	private String convertedFileName;
	private Collection<String> initialChar;
	private Collection<String> remplacementChar;

	/**
	 * Java class defining the source file that will be converted into the
	 * destination file It defines too the initial char that will be converted into
	 * the new char Throw an exception if both collection of char don't have the
	 * same size
	 * 
	 * Order in the changes matters
	 * 
	 * @param fileNameToConvert
	 *            source
	 * @param convertedFileName
	 *            destination file
	 * @param initialChar
	 *            collection of char to convert
	 * @param remplacementChar
	 *            char Replacement collection
	 */
	public TanagraFileConverter(String fileNameToConvert, String convertedFileName, Collection<String> initialChar,
			Collection<String> remplacementChar) {
		this.setFileNameToConvert(fileNameToConvert);
		this.setConvertedFileName(convertedFileName);
		this.initialChar = initialChar;
		this.remplacementChar = remplacementChar;
	}

	public void executeRewriting() throws IOException {
		// Open the file to convert
		InputStream ips;
		ips = new FileInputStream(getFileNameToConvert());
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		// Open the converted file to write in
		FileWriter fw = new FileWriter(getConvertedFileName() + "_train");
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter fichierSortie = new PrintWriter(bw);
		// the second file were we store the others line over 463715
		FileWriter fw2 = new FileWriter(getConvertedFileName() + "_test");
		BufferedWriter bw2 = new BufferedWriter(fw2);
		PrintWriter fichierSortie2 = new PrintWriter(bw2);
		// Set up the headline of both output files
		writeHeader(fichierSortie, fichierSortie2);	
		// Strat reading the file
		String line = br.readLine();
		int i = 1;
		do {
			i++;
			String original = line;
			try {
				
				line = applyRegexOnLine(line);
			} catch (FormatterClosedException e) {
				System.out.println("Error line " + i);
				System.out.println("line originiale : ");
				System.out.println(original);
				
				System.out.println("line modifiée : ");
				System.out.println(line);
				
			}
			if (i <463715) { // we sort the lines of training and the lines of tests
				fichierSortie.println(line);
			}else {
				fichierSortie2.println(line);
			}
			if (i%10000==0) {
				System.out.println(i + " lignes traitées");
			}
		} while ((line=br.readLine())!=null);
		br.close();
		fichierSortie.close();
		fichierSortie2.close();
	}

	private void writeHeader(PrintWriter fichierSortie, PrintWriter fichierSortie2) {
		StringBuilder firstLine = new StringBuilder();
		firstLine.append("annee\t");
		for (int i = 1; i <= 90; i++) {
			firstLine.append("arg" + i + "\t");
		}
		fichierSortie.println(firstLine);
		fichierSortie2.println(firstLine);
	}

	private String applyRegexOnLine(String line) {
		String newLine = line;
		Iterator<String> iteratorNew = remplacementChar.iterator();
		for (Iterator<String> iteratorOld = initialChar.iterator(); iteratorOld.hasNext();) {
			String initialChar = (String) iteratorOld.next();
			String replacementChar = (String) iteratorNew.next();
			newLine = newLine.replaceAll(initialChar, replacementChar);
		}
		// Set the year between quote
		newLine = "\"" + newLine;
		newLine = newLine.replaceFirst("\t", "\"\t");
		if (newLine.split("\t").length != 91) {
			throw new FormatterClosedException();
		}
		return newLine;
	}

	/**
	 * @return the fileNameToConvert
	 */
	public String getFileNameToConvert() {
		return fileNameToConvert;
	}

	/**
	 * @param fileNameToConvert
	 *            the fileNameToConvert to set
	 */
	private void setFileNameToConvert(String fileNameToConvert) {
		this.fileNameToConvert = fileNameToConvert;
	}

	/**
	 * @return the convertedFileName
	 */
	public String getConvertedFileName() {
		return convertedFileName;
	}

	/**
	 * @param convertedFileName
	 *            the convertedFileName to set
	 */
	private void setConvertedFileName(String convertedFileName) {
		this.convertedFileName = convertedFileName;
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
