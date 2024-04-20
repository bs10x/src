import java.util.List;

public class Node {
	
	private final String type;
	
	private final int value;
	
	private List<Node> subnodes;
	
	public String getType() {
		return this.type;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public List<Node> getSubnodes() {
		return this.subnodes;
	}

	public Node(int value, String type, List<Node> subnodes) {
		this.value = value;
		this.type = type;
		this.subnodes = subnodes;
	}
	
	public static Node newChoiceNode(List<Node> subnodes) {
		return new Node(0, "CHOICE", subnodes);
	}
	
	public static Node newSeqNode(List<Node> subnodes) {
		return new Node(0, "SEQ", subnodes);
	}
	
	public static Node newAddNode(int value) {
		return new Node(value, "ADD", List.of());
	}

}
