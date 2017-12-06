package regression.tree.model.label;

public class Category implements ILabel {

	private String category;
	
	public Category(String category) {
		this.category = category;
	}
	
	@Override
	public LabelEnum getLabelTypeID() {
		return LabelEnum.Category;
	}

}
