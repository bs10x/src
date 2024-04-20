import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private List<Edge> neighbours;
	
	public Node(List<Edge> neighbours) {
		this.neighbours = neighbours;
	}
	
	public Node() {
		this.neighbours = new ArrayList<Edge>();
	}
	
	public List<Edge> getNeighbours() {
		return neighbours;
	}

}
