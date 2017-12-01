package parsing.file;

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
import java.util.Iterator;

public class FileConverter {
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
	public FileConverter(String fileNameToConvert, String convertedFileName, Collection<String> initialChar,
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
		StringBuilder firstLine = new StringBuilder();
		firstLine.append("annee\t");
		for (int i = 1; i < 90; i++) {
			firstLine.append("arg" + i + "\t");
		}
		fichierSortie.println(firstLine);
		fichierSortie2.println(firstLine);	
		// Strat reading the file
		String line = br.readLine();
		int i = 1;
		do {
			line = applyRegexOnLine(line);
			i++;
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

	private String applyRegexOnLine(String line) {
		String newLine = line;
		Iterator<String> iteratorNew = remplacementChar.iterator();
		for (Iterator<String> iteratorOld = initialChar.iterator(); iteratorOld.hasNext();) {
			String initialChar = (String) iteratorOld.next();
			String replacementChar = (String) iteratorNew.next();
			newLine = newLine.replaceAll(initialChar, replacementChar);
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
	
	public static void main(String[] args) {
		Collection<String> oldChars = new ArrayList<>();
		oldChars.add(",");
		oldChars.add("\\.");
		Collection<String> newChars = new ArrayList<>();
		newChars.add('\t'+"");
		newChars.add(",");
		
		FileConverter fileConverteur = new FileConverter("YearPredictionMSD.txt", "YearPredictionMSDNew",oldChars,newChars);
		try {
			fileConverteur.executeRewriting();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
