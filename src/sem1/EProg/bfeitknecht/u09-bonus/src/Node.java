public class Node {

	/***
	*   Sie koennen weitere Attribute (und auch Methoden) hinzufuegen, wenn Sie wollen
	***/

	private Node down;
	private Node right;
	
	public void setDown(Node n) {
		this.down = n;
	}

	public void setRight(Node n) {
		this.right = n;
	}
	
	public boolean hasDown( ) {
		return this.getDown() != null;
	}

	public boolean hasRight( ) {
		return this.getRight() != null;
	}
	
	public Node getRight() {
		return this.right;
	}
	
	public Node getDown() {
		return this.down;
	}
	
	
	public boolean hasUniqueDownRight() {
		return this != null
			&& this.hasDown() && this.hasRight()
			&& this.getDown() != this.getRight();
    }
	
	public boolean uniqueDownRightCloses() {
		return this.hasUniqueDownRight()
			&& this.getDown().hasRight() && this.getRight().hasDown()
			&& this.getDown().getRight() == this.getRight().getDown();
	}
	
	public Node squareClosingNode() {
		if (this.uniqueDownRightCloses()) return this.getDown().getRight();
		else return null;
	}
	
}
