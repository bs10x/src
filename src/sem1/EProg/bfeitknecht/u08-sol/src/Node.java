
public class Node {
	
	String type;
	
	int value;
	
	Node[] subnodes;
	
	String getType() {
		return this.type;
	}
	
	int getValue() {
		return this.value;
	}
	
	Node[] getSubnodes() {
		return this.subnodes;
	}

	Node(int value, String type, Node[] subnodes) {
		this.value = value;
		this.type = type;
		this.subnodes = subnodes;
	}
	
	static Node newChoiceNode(Node[] subnodes) {
		return new Node(0, "CHOICE", subnodes);
	}
	
	static Node newSeqNode(Node[] subnodes) {
		return new Node(0, "SEQ", subnodes);
	}
	
	static Node newAddNode(int value) {
		return new Node(value, "ADD", new Node[] {});
	}

}
