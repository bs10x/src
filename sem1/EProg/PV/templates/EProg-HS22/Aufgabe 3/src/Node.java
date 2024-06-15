public class Node {
	
	private Node down;
	private Node right;
	
	public void setRight(Node n) {
		this.right = n;
	}
	
	public Node getRight() {
		return this.right;
	}
	
	public void setDown(Node n) {
		this.down = n;
	}
	
	public Node getDown() {
		return this.down;
	}
}
