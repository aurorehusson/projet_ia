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
		this.rule = this.argumentNumber + this.compareSign + this.value;
		
	}

	public String getRule() {
		return rule;
	}

	public Integer getArgumentNumber() {
		return argumentNumber;
	}

	public String getCompareSign() {
		return compareSign;
	}

	public Double getValue() {
		return value;
	}

	@Override
	public LabelEnum getLabelTypeID() {
		return LabelEnum.Rule;
	}

	@Override
	public String toPrettyString() {
		return getRule();
	}

}
