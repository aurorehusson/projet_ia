package regression.tree.parsing.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
/**
 * 
 * @author jordan
 *
 */
public abstract class FileConverter {
	private String fileNameToConvert;
	private String convertedFileName;

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
	public FileConverter(String fileNameToConvert, String convertedFileName) {
		this.setFileNameToConvert(fileNameToConvert);
		this.setConvertedFileName(convertedFileName);
	}

	/**
	 * Execute the reading of original file and format each line in the right shape on the both output files.
	 * 
	 * @throws IOException
	 */
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
			} catch (FormatException e) {
				System.out.println("Error line " + i);
				System.out.println("line originiale : ");
				System.out.println(original);
				
				System.out.println("line modifiee : ");
				System.out.println(line);
				
			}
			if (i <463715) { // we sort the lines of training and the lines of tests
				fichierSortie.println(line);
			}else {
				fichierSortie2.println(line);
			}
			if (i%10000==0) {
				System.out.println(i + " lignes trait�es");
			}
		} while ((line=br.readLine())!=null);
		br.close();
		fichierSortie.close();
		fichierSortie2.close();
	}

	public abstract void writeHeader(PrintWriter fichierSortie, PrintWriter fichierSortie2);

	public abstract String applyRegexOnLine(String line) throws FormatException;

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
	protected void setFileNameToConvert(String fileNameToConvert) {
		this.fileNameToConvert = fileNameToConvert;
	}

	/**
	 * @return the convertedFileName
	 */
	protected String getConvertedFileName() {
		return convertedFileName;
	}

	/**
	 * @param convertedFileName
	 *            the convertedFileName to set
	 */
	protected void setConvertedFileName(String convertedFileName) {
		this.convertedFileName = convertedFileName;
	}


}
