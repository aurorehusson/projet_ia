package regression.tree.model;

import java.util.ArrayList;
import java.util.Collection;

import regression.tree.model.label.ILabel;

public class Node {
	
	private Collection<Node> child;
	private Node parent;
	
	private ILabel category;
	
	
	public Node(ILabel category) {
		parent = null;
		child = new ArrayList<>();
		this.category = category;
	}
	
	public Node(Node parent, ILabel category) {
		this.parent = parent;
		child = new ArrayList<>();
		this.category = category;
	}
	
	public Boolean isRoot() {
		return parent == null;
	}
	
	public Boolean isLeaf() {
		return child.size() == 0;
	}
	
	public ILabel getLabel() {
		return category;
	}
	
	protected void setLabel(ILabel label) {
		category = label;
	}
	
	public void addChild(Node child) {
		this.child.add(child);
	}
	
	public int getDeepth() {
		return parent==null ? 0 : parent.getDeepth() + 1;
	}
	
	
}
