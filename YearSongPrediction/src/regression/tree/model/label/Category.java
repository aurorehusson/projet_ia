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

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	protected void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toPrettyString() {
		return getCategory();
	}

}
