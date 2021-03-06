package regression.tree.controller;

import regression.tree.controller.exception.InvalidParsingException;
import regression.tree.model.Node;
import regression.tree.model.label.Category;
import regression.tree.model.label.ILabel;
import regression.tree.model.label.Rule;
/**
 * 
 * @author jordan
 *
 */
public class NodeAnalyser extends Node {

	private String line;
	private int lastIndexOf;
	
	/**
	 * 
	 * @param category
	 */
	public NodeAnalyser(ILabel category) {
		super(category);
	}
	/**
	 * 
	 * @param line
	 * @throws InvalidParsingException
	 */
	public NodeAnalyser(String line) throws InvalidParsingException {
		super(null);
		this.line = line;
		analyseString();
	}
	
	/**
	 * 
	 * @throws InvalidParsingException
	 */

	private void analyseString() throws InvalidParsingException {
		String newLine;
		newLine = line.substring(getDeepth());
		
		String[] split = newLine.split(" then ");
		switch (split.length) {
		
			case 2:
				if (!split[1].startsWith("annee = \"")) {
					throw new InvalidParsingException(split[1] + " This String doesn't start with \"annee = \"\"");
				}
				String substring = split[1].substring(9, 13);
				this.addChild(new Node(this, new Category(substring)));
				//No break here because we want to execute case 1 when we execute case 2
			case 1: 
				String[] split2 = split[0].split(" ", 3);
				if (!split2[0].startsWith("arg")) {
					throw new InvalidParsingException("\"" + split2[0] + "\"" + " This String doesn't start with \"arg\"");
				}
				// we free the 3 first characters "arg"
				Integer argNumber = Integer.valueOf(split2[0].substring(3));
				String compareSign = split2[1];
				String parsableDouble = split2[2].replace(",", ".");
				Double value = Double.valueOf(parsableDouble);
				setLabel(new Rule(argNumber,compareSign,value));
				break;
			default:
				throw new InvalidParsingException("this line : " + this.line + " can't be parsed");
				//throw >> break
		}
	}
	/**
	 * @return the deepth of a node, supposing the number of '\t'
	 */
	public int getDeepth() {
		lastIndexOf = line.lastIndexOf("\t");
		return (lastIndexOf == -1) ? 0 : lastIndexOf + 1;
	} 
	
}
