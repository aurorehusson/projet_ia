package parsing.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Parser {

	private String pathOfFile;
	private String argSeparator;
	private Collection<ParsedLine> FileParsed;
	
	public Parser(String pathOfFile, String argSeparator) {
		this.pathOfFile = pathOfFile;
		this.argSeparator = argSeparator;
	}
	

	public void writeParseLine(String fichier, char ArgumentSeparator, char doubleSeparator, Collection<String> argumentNameLine) {
		StringBuffer buffer = new StringBuffer();
		int i = 0;
		for (Iterator<String> iterator = argumentNameLine.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			buffer.append(string + ArgumentSeparator);
		}
		buffer.append('\n');
		for (Iterator<ParsedLine> iterator = FileParsed.iterator(); iterator.hasNext();) {
			ParsedLine parsedLine = (ParsedLine) iterator.next();
			buffer.append(parsedLine.getAnnee() + ArgumentSeparator);
			for (Iterator<Double> iterator2 = parsedLine.getArguments().iterator(); iterator2.hasNext();) {
				Double arg = (Double) iterator2.next();
				buffer.append(arg.toString().replace('.', doubleSeparator) + ArgumentSeparator);
			}
			buffer.append('\n');
			i++;
			if (i%10000==0) {
				System.out.println(i + " données écrites");
			}
		}
		try {
			FileWriter fw = new FileWriter (fichier);
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fichierSortie = new PrintWriter (bw); 
			fichierSortie.println (buffer); 
			fichierSortie.close();
		}
		catch (Exception e){
			System.out.println(e.toString());
		}	

	}
	
	public void parseFile() throws Exception {
		InputStream ips=new FileInputStream(pathOfFile); 
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		String ligne;
		Collection<ParsedLine> maCollection = new ArrayList<ParsedLine>();
		ligne=br.readLine();
		if (ligne.startsWith("année")) {
			ligne=br.readLine();
		}
		int i = 0;
		do {
			maCollection.add(parseLine(ligne));
			i++;
			if (i%10000==0) {
				System.out.println(i + " données lues");
			}
		} while ((ligne=br.readLine())!=null);
		br.close(); 
		setFileParsed(maCollection);
	}
	
	public ParsedLine parseLine(String line) {
		ParsedLine parsedLine = new ParsedLine();
		String[] stringTable = line.split(argSeparator);
		parsedLine.setAnnee(stringTable[0]);
		for (int i = 1;i<=90;i++) {
			parsedLine.setNextArg(Double.valueOf(stringTable[i]));
		}
		return parsedLine;
	}
	
	public String getArgSeparator() {
		return argSeparator;
	}

	public void setArgSeparator(String argSeparator) {
		this.argSeparator = argSeparator;
	}


	public String getPathOfFile() {
		return pathOfFile;
	}

	public void setPathOfFile(String pathOfFile) {
		this.pathOfFile = pathOfFile;
	}

	public Collection<ParsedLine> getFileParsed() {
		return FileParsed;
	}

	public void setFileParsed(Collection<ParsedLine> fileParsed) {
		FileParsed = fileParsed;
	}
}
