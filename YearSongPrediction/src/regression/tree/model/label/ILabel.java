package regression.tree.model.label;

public interface ILabel {

	/**
	 * Return what kind of label it is, they are defined in LabelEnum
	 * 
	 * @return The type of Label
	 */
	public LabelEnum getLabelTypeID();
}
