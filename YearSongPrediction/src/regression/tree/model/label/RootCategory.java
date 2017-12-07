package regression.tree.model.label;

public class RootCategory extends Category implements ILabel {

	public RootCategory() {
		super("ROOT");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public LabelEnum getLabelTypeID() {
		return LabelEnum.RootCategory;
	}

	@Override
	protected void setCategory(String category) {
		throw new IllegalAccessError("Can't use this method on a Root");
	}
	
}
