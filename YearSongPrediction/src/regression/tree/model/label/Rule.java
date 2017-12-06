package regression.tree.model.label;

public class Rule implements ILabel {

	private String rule;
	private Integer argumentNumber;
	private String compareSign;
	private Double value;
	
	public Rule(String rule) {
		this.rule = rule;
		
	}
	
	public Rule(Integer argumentNumber, String compareSign, Double value) {
		this.argumentNumber = argumentNumber;
		this.compareSign = compareSign;
		this.value = value;
		
	}
	
	
	
	@Override
	public LabelEnum getLabelTypeID() {
		return LabelEnum.Rule;
	}

}
