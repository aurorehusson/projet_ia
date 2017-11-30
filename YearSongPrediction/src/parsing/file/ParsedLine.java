package parsing.file;

import java.util.Collection;
import java.util.LinkedList;

public class ParsedLine {
	private String Annee;
	Collection<Double> arguments;
	
	public ParsedLine() {
		Annee = "";
		arguments = new LinkedList<Double>();
		
	}
	
	void setAnnee(String annee) {
		this.Annee = annee;
	}
	
	public String getAnnee() {
		return Annee;
	}
	
	public void setNextArg(Double arg) {
		arguments.add(arg);
	}
	
	public Collection<Double> getArguments() {
		return arguments;

	}
	
}
