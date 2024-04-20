public class Node {
	
	private Node right, bottom;
	
	public Node(Node right, Node bottom) {
		setRight(right);
		setBottom(bottom);
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
	
	public Node getBottom() {
		return bottom;
	}
	
	public void setBottom(Node bottom) {
		this.bottom = bottom;
	}

}
