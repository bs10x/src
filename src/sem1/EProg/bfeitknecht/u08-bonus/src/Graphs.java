
public class Graphs {

	public static Node cube(int n) {
		
		NodeLink first;
		NodeLink last;
		int anzahlKnoten = (int) Math.pow(2, n);
		
		LinkedNodeList hypercube = new LinkedNodeList();
		
		
		Node[] nodes = new Node[anzahlKnoten];
	
		for(int i = 0; i < anzahlKnoten; i++) {
			Node currentNode = new Node(Integer.toBinaryString(i));
			nodes[i] = currentNode;
			hypercube.addLast(currentNode);
			
		}
		return nodes[0];
	}
	
	
	
	
	// Eine Hilfsmethod zum printen von Graphen.
	// Ueberschreiben Sie Node.toString() um einen schoeneren Output zu bekommen.
	public static void printGraph(Node node) {
		LinkedNodeList visited = new LinkedNodeList();
		printGraphRecursive(node, visited);
	}
	
	public static void printGraphRecursive(Node node, LinkedNodeList visited) {
		visited.addFirst(node);
		
		Node[] nexts = node.getNeighbors();
		
		String[] names = new String[nexts.length];
		for (int i = 0; i < nexts.length; i += 1) { 
			names[i] = nexts[i].toString();
		}
		
		System.out.println(node + " -> " + String.join(",", names));
		
		for (Node next : node.getNeighbors()) {
			if (!visited.contains(next)) {
				printGraphRecursive(next, visited);
			}
		}
	}
}
