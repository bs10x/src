
public class Edge {
	
	private Node target;
	private String label;
	private int weight;
	
	public Edge(Node target, String label, int weight) {
		this.target = target;
		this.label = label;
		this.weight = weight;
	}
	
	public Node getTarget() {
		return target;
	}
	
	public String getLabel() {
		return label;
	}
	
	public int getWeight() {
		return weight;
	}
}
