import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// Sie koennen die main Methode beliebig aendern.

		Node [] nodes = getExample();
		Graph.removeEdges(nodes[0], "Q", 12);
		
		Node [] nodes2 = getExample();
		List<Node> result = Graph.findNodes(nodes2[0], List.of("A","S"));
		System.out.println(result.size());
	}
	
	public static Node [] getExample() {
		Node n8 = new Node();
		
		/********* n5, n6, n7 *********/
		Node n5 = new Node();
		
		List<Edge> n6Edges = new ArrayList<Edge>();
		n6Edges.add(new Edge(n8, "Q", 12));		
		Node n6 = new Node(n6Edges);
		
		Node n7 = new Node();
		
		/********* n2, n3, n4 *********/
		
		List<Edge> n2Edges = new ArrayList<Edge>();
		n2Edges.add(new Edge(n5, "Q", 15));
		n2Edges.add(new Edge(n6, "S", 4));
		Node n2 = new Node(n2Edges);
		
		List<Edge> n3Edges = new ArrayList<Edge>();
		n3Edges.add(new Edge(n6, "Q", 4));
		n3Edges.add(new Edge(n7, "S", 5));
		Node n3 = new Node(n3Edges);
		
		List<Edge> n4Edges = new ArrayList<Edge>();
		n4Edges.add(new Edge(n7, "S", 1));
		Node n4 = new Node(n4Edges);
		
		/********* n1 *********/
		List<Edge> n1Edges = new ArrayList<Edge>();
		n1Edges.add(new Edge(n2, "A", 2));
		n1Edges.add(new Edge(n3, "A", 4));
		n1Edges.add(new Edge(n4, "A", 2));
		Node n1 = new Node(n1Edges);
		
		return new Node [] {n1, n2, n3, n4, n5, n6, n7, n8};
	}
}
