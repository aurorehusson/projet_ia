package regression.tree.parsing.file.converter;

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
 * @author jorda
 *
 */
public class TreeTranslator {
	/**
	 * this class simply have to translate the tree that weka gives, with some kind
	 * of characters like '| '
	 * @throws IOException 
	 * 
	 */
	public static void main(String[] args) throws IOException {
		// Open the file to convert
		InputStream ips;
		ips = new FileInputStream("treedata/weka/donnees_train_weka_j48_treeOnly.txt");
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		// Open the converted file to write in
		FileWriter fw = new FileWriter("treedata/weka/donnees_train_weka_j48_treeOnly_converted.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter fichierSortie = new PrintWriter(bw);

		// Strat reading the file
		String line = br.readLine();
		do {
			line = applyRegexOnLine(line);
			fichierSortie.println(line);
		} while ((line = br.readLine()) != null);
		br.close();
		fichierSortie.close();
	}

	private static String applyRegexOnLine(String line) {
		String output = line.replaceAll(": ", " then annee = \"");
		return output.replaceAll(".   ", "\t");
	}

}
