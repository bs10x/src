
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GraphsTest {
	
	@Test
	void test1() {
		Node node0 = Graphs.cube(1);
		Node[] nexts0 = node0.getNeighbors();
		assertEquals(1, nexts0.length);
		Node node1 = nexts0[0];
		Node[] nexts1 = node1.getNeighbors();
		assertEquals(1, nexts1.length);
		assertEquals(node0, nexts1[0]);
	}
	
	@Test
	void test2() {
		Node node0 = Graphs.cube(2);
		
		Node[] nexts0 = node0.getNeighbors();
		assertEquals(2, nexts0.length);
		
		Node node1 = nexts0[0];
		Node node2 = nexts0[1];
		
		// node1 und node2 sind mit node0 verbunden aber nicht gegenseitig verbunden
		Node[] nexts1 = node1.getNeighbors();
		Node[] nexts2 = node2.getNeighbors();
		assertEquals(2, nexts1.length);
		assertEquals(2, nexts2.length);
		assertTrue(node0 == nexts1[0] || node0 == nexts1[1]);
		assertTrue(node0 == nexts2[0] || node0 == nexts2[1]);
		assertTrue(node2 != nexts1[0] && node2 != nexts1[1]);
		assertTrue(node1 != nexts2[0] && node1 != nexts2[1]);
		
		Node node3 = nexts1[0];
		if (node3 == node0) {
			node3 = nexts1[1];
		}
		
		Node node4 = nexts2[0];
		if (node4 == node0) {
			node4 = nexts2[1];
		}
		
		assertEquals(node3, node4);
		
		// node3 ist mit node1 und node2 verbunden
		Node[] nexts3 = node3.getNeighbors();
		assertEquals(2, nexts3.length);
		assertTrue(node1 == nexts3[0] || node1 == nexts3[1]);
		assertTrue(node2 == nexts3[0] || node2 == nexts3[1]);
	}
	
	@Test
	void test5() {
		Node result = Graphs.cube(5);
		assertCube(result, 5);
	}

	
	// Es wird nicht erwartet, dass Sie diese Methode verstehen.
	// Die Methode testet, dass ein Node einem n-dimensionalen Wuerfel entsprechen koennte.
	// Die Methode prueft, dass 
	//   (1) jeder Node n-viele Nachbarn hat.
	//   (2) 2^n Nodes erreichbar sind.
	//   (3) Nodes korrekt verbunden sind.
	void assertCube(Node node, int n) {
		LinkedNodeList visited = new LinkedNodeList();
		int num = assertNodesConnected(node, n, 0, visited);
		// Ein n-dimensionaler Wuerfel hat 2^n nodes.
		assertEquals(1<<n, num, "Falsche Anzahl Nodes.");
		
		assertEdgesConnected(visited.toArray());
	}
	
	int assertNodesConnected(Node node, int n, int idx, LinkedNodeList visited) {
		visited.addFirst(node);
		Node[] neighbors = node.getNeighbors();
		
		// Jeder Knoten hat n Nachbarn
		assertEquals(n, neighbors.length, "Falsche Anzahl Nachbarn.");
		
		int count = idx+1;
		for (Node next : neighbors) {
			// Nachbarn sind nie null
			assertNotNull(next, "Nachbar ist null.");
			if (!visited.contains(next)) {
				count = assertNodesConnected(next, n, count, visited);
			}
		}
		
		return count;
	}
	
	// Testet für alle verbundenen Nodes, 
	// dass die Nachbarn beider Enden direkt verbunden sind.
	void assertEdgesConnected(Node[] nodes) {
		for (Node a : nodes) {
			for (Node b : a.getNeighbors()) {
				assertTrue(isConnectedEdge(a,b));
			}
		}
	}
	
	// Fuer jeden Nachbar x von a gibt es einen Nachbar y von b,
	// sodass x und y verbunden sind und x != b und y != a gilt.
	boolean isConnectedEdge(Node a, Node b) {
		Node[] neighborsA = a.getNeighbors();
		Node[] neighborsB = b.getNeighbors();
		
		for (Node x : neighborsA) {
			if (x != b && !hasConnectedNeighbor(a, x, neighborsB)) {
				return false;
			}
		}
		
		return true;
	}
	
	boolean hasConnectedNeighbor(Node a, Node x, Node[] neighborsB) {
		for (Node y : neighborsB) {
			if (y != a) {
				Node[] neighborsY = y.getNeighbors();
				for (Node neighborY : neighborsY) {
					if (neighborY == x) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
