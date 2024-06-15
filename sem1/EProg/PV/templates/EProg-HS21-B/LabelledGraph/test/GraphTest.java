import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GraphTest {
	
	@Test
	public void testRemoveEdgesExample() {
		Node [] nodes = getExample();
		Graph.removeEdges(nodes[0], "Q", 12);
		
		//n1
		assertEquals(3, nodes[0].getNeighbours().size());
		assertTrue(containsEdge(nodes[0].getNeighbours(), nodes[1], "A", 2));
		assertTrue(containsEdge(nodes[0].getNeighbours(), nodes[2], "A", 4));
		assertTrue(containsEdge(nodes[0].getNeighbours(), nodes[3], "A", 2));
		
		//n2
		assertEquals(2, nodes[1].getNeighbours().size());
		assertTrue(containsEdge(nodes[1].getNeighbours(), nodes[4], "Q", 15));
		assertTrue(containsEdge(nodes[1].getNeighbours(), nodes[5], "S", 4));
		
		
		//n3 
		assertEquals(1, nodes[2].getNeighbours().size());
		assertTrue(containsEdge(nodes[2].getNeighbours(), nodes[6], "S", 5));
		
		//n4
		assertEquals(1, nodes[3].getNeighbours().size());
		assertTrue(containsEdge(nodes[3].getNeighbours(), nodes[6], "S", 1));

		//n5
		assertEquals(0, nodes[4].getNeighbours().size());
		
		//n6
		assertEquals(0, nodes[5].getNeighbours().size());

		//n7
		assertEquals(0, nodes[6].getNeighbours().size());
		
		//n8
		assertEquals(0, nodes[7].getNeighbours().size());
	}
	
	@Test
	public void testFindNodesExample() {		
		Node [] nodes = getExample();
		List<Node> result = Graph.findNodes(nodes[0], List.of("A","S"));
		
		assertNotNull(result);
		assertEquals(2, result.size());
		
		assertTrue(containsNode(result, nodes[6]));
		assertTrue(containsNode(result, nodes[5]));
		
		assertSame(nodes[6], result.get(0));
		assertSame(nodes[5], result.get(1));		
	}
	
	@Test
	public void testFindNodes2() {		
		Node nExit = new Node();
		
		Node nExit2 = new Node();

		List<Edge> n0Edges = new ArrayList<Edge>();
		n0Edges.add(new Edge(nExit, "R", 10));
		n0Edges.add(new Edge(nExit2, "Q", 5));
		Node n0 = new Node(n0Edges);
		
		List<Edge> n1Edges = new ArrayList<Edge>();
		n1Edges.add(new Edge(nExit, "R", 10));
		Node n1 = new Node(n1Edges);
		
		List<Edge> nOriginEdges = new ArrayList<Edge>();
		nOriginEdges.add(new Edge(n0, "R", 5));
		nOriginEdges.add(new Edge(n1, "R", 5));		
		Node nOrigin = new Node(nOriginEdges);
		
		List<Node> result = Graph.findNodes(nOrigin, List.of("R"));		
		assertNotNull(result);
		
		assertEquals(2, result.size());
		
		assertTrue(containsNode(result, n0));
		assertTrue(containsNode(result, n1));

		assertSame(n1, result.get(0));
		assertSame(n0, result.get(1));
	}
	
	
	public Node [] getExample() {
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
	
	
	/** Hilfsfunktionen **/
	
	public static boolean containsEdge(List<Edge> edges, Node target, String label, int w) {
		for(Edge e : edges) {
			if(e.getTarget() == target && e.getLabel().equals(label) && e.getWeight() == w) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean containsNode(List<Node> nodes, Node node) {
		for(Node n : nodes) {
			if(n == node) {
				return true;
			}
		}
		
		return false;
	}

}
