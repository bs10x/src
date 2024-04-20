public class Node {
	
	private Node outer;
	private Node inner;
	
	public Node(Node outer, Node inner) {		
		setOuter(outer);
		setInner(inner);
	}
	
	public Node getOuter() {
		return outer;
	}
	
	public void setOuter(Node outer) {
		this.outer = outer;
	}
	
	public Node getInner() {
		return inner;
	}
	
	public void setInner(Node inner) {
		this.inner = inner;
	}	

}
