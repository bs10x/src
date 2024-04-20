import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

// Wir verwenden diese Node Implementation fuer Tests
class TestNode implements Node {
	public int numberOfReceivedMessages = 0;
	@Override 
	public void receive(Message msg) {
		numberOfReceivedMessages += 1;
	}
}

public class RoutingTest {
	
	public static Node[] createNetwork1() {
		// Wir erzeugen 5 Knoten mit folgenden Verbindungen:
		// N2 --[3]--> N0
		// N2 --[1]--> N1
		// N3 --[1]--> N2
		// N4 --[2]--> N2
		
		Node n0 = new TestNode();
		Node n1 = new TestNode();
		
		Map<Integer, Node> routingTableN2 = new HashMap<Integer, Node>();
		routingTableN2.put(3, n0);
		routingTableN2.put(1, n1);
		Node n2 = new RoutingNode(routingTableN2);
		
		Map<Integer, Node> routingTableN3 = new HashMap<Integer, Node>();
		routingTableN3.put(1, n2);
		Node n3 = new RoutingNode(routingTableN3);
		
		Map<Integer, Node> routingTableN4 = new HashMap<Integer, Node>();
		routingTableN4.put(2, n2);
		Node n4 = new CountingNode(routingTableN4);
		
		return new Node[] {n0, n1, n2, n3, n4};
	}
	
	@Test
	public void constructionTest() {
		// Dieser Test sollte niemals fehlschlagen.
		Node[] network = createNetwork1();
		TestNode n0 = (TestNode) network[0];
		TestNode n1 = (TestNode) network[1];
		RoutingNode n2 = (RoutingNode) network[2];
		RoutingNode n3 = (RoutingNode) network[3];
		
		Map<Integer, Node> routingTableN2 = new HashMap<Integer, Node>();
		routingTableN2.put(3, n0);
		routingTableN2.put(1, n1);
		assertEquals(routingTableN2, n2.getRoutingTable());
		
		Map<Integer, Node> routingTableN3 = new HashMap<Integer, Node>();
		routingTableN3.put(1, n2);
		assertEquals(routingTableN3, n3.getRoutingTable());
	}
	
	@Test
	public void testCandidates1() {
		Node[] network = createNetwork1();
		RoutingNode n2 = (RoutingNode) network[2];
		
		List<Set<Integer>> path1 = List.of(Set.of(1, 3), Set.of(42));
		assertEquals(Set.of(1, 3), n2.candidates(path1));
		
		List<Set<Integer>> path2 = List.of(Set.of(1, 3, 7), Set.of(42));
		assertEquals(Set.of(1, 3), n2.candidates(path2));	
	}
	
	@Test
	public void testSelectConnection1() {
		Node[] network = createNetwork1();
		RoutingNode n2 = (RoutingNode) network[2];
		
		
		assertEquals(1, n2.selectConnection(Set.of(1, 3)));
		
		// Verbindung 1 von N2 wird genutzt
		n2.incrementCount(1);
		assertEquals(3, n2.selectConnection(Set.of(1, 3)));
		
		// Verbindung 3 von N2 wird genutzt
		n2.incrementCount(3);
		assertEquals(1, n2.selectConnection(Set.of(1, 3)));
	}
	
	@Test
	public void testReceive1() {
		Node[] network = createNetwork1();
		TestNode n0 = (TestNode) network[0];
		TestNode n1 = (TestNode) network[1];
		RoutingNode n3 = (RoutingNode) network[3];
		
		IntMessage m1 = new IntMessage(1, List.of(Set.of(1, 5), Set.of(1, 3)));
		n3.receive(m1);
		// Die Nachricht sollte zu N1 geschickt werden.
		assertEquals(0, n0.numberOfReceivedMessages);
		assertEquals(1, n1.numberOfReceivedMessages);
		
		IntMessage m2 = new IntMessage(2, List.of(Set.of(1, 5), Set.of(1, 3)));
		n3.receive(m2);
		// Die Nachricht sollte zu N0 geschickt werden.
		assertEquals(1, n0.numberOfReceivedMessages);
		assertEquals(1, n1.numberOfReceivedMessages);
		
		IntMessage m3 = new IntMessage(3, List.of(Set.of(1, 5), Set.of(1, 3)));
		n3.receive(m3);
		// Die Nachricht sollte wieder zu N1 geschickt werden.
		assertEquals(1, n0.numberOfReceivedMessages);
		assertEquals(2, n1.numberOfReceivedMessages);
	}
	
	@Test
	public void testProcess1() {
		Node[] network = createNetwork1();
		RoutingNode n2 = (RoutingNode) network[2];
		Map<Integer, Node> expectedMap = new HashMap<Integer, Node>(n2.getRoutingTable());
		
		Node t1 = new TestNode();
		n2.process(new UpdateMessage(7, t1, List.of()));
		expectedMap.put(7, t1);
		assertEquals(expectedMap, n2.getRoutingTable());
		
		Node t2 = new TestNode();
		n2.process(new UpdateMessage(6, t2, List.of()));
		expectedMap.put(6, t2);
		assertEquals(expectedMap, n2.getRoutingTable());
			
		Node t3 = new TestNode();
		n2.process(new UpdateMessage(6, t3, List.of()));
		expectedMap.put(6, t3);
		assertEquals(expectedMap, n2.getRoutingTable());
	}
	
	@Test
	public void testCountNode1() {
		Node[] network = createNetwork1();
		TestNode n0 = (TestNode) network[0];
		TestNode n1 = (TestNode) network[1];
		CountingNode n4 = (CountingNode) network[4];
		
		IntMessage m1 = new IntMessage(1, List.of(Set.of(2, 5), Set.of(1, 3)));
		n4.receive(m1);
		// Die Nachricht sollte zu N1 geschickt werden.
		assertEquals(0, n0.numberOfReceivedMessages);
		assertEquals(1, n1.numberOfReceivedMessages);
		
		assertEquals(0, n4.getSum());
		n4.receive(new IntMessage(3, List.of()));
		assertEquals(3, n4.getSum());
	}
	
	@Test
	public void testReceive2() {
		RoutingNode q0 = new RoutingNode(new HashMap<Integer, Node>());
		RoutingNode q1 = new RoutingNode(new HashMap<Integer, Node>());
		RoutingNode q2 = new RoutingNode(new HashMap<Integer, Node>());
		RoutingNode q3 = new RoutingNode(new HashMap<Integer, Node>());
		RoutingNode q4 = new RoutingNode(new HashMap<Integer, Node>());
		
		// Wir fuegen eine Verbindung Q0 --[3]--> Q1 hinzu
		UpdateMessage m1 = new UpdateMessage(3, q1, List.of());
		q0.receive(m1);
		
		Map<Integer, Node> expectedRoutingMapQ1 = new HashMap<Integer, Node>();
		assertEquals(expectedRoutingMapQ1, q1.getRoutingTable());
		
		// Wir fuegen eine Verbindung Q1 --[5]--> Q2 hinzu
		UpdateMessage m2 = new UpdateMessage(5, q2, List.of(Set.of(3, 4)));
		q0.receive(m2);
		
		expectedRoutingMapQ1.put(5, q2);
		assertEquals(expectedRoutingMapQ1, q1.getRoutingTable());
		
		// Wir fuegen eine Verbindung Q1 --[3]--> Q3 hinzu
		UpdateMessage m3 = new UpdateMessage(3, q3, List.of(Set.of(3, 4)));
		q0.receive(m3);
		
		expectedRoutingMapQ1.put(3, q3);
		assertEquals(expectedRoutingMapQ1, q1.getRoutingTable());
		
		Map<Integer, Node> expectedRoutingMapQ3 = new HashMap<Integer, Node>();
		assertEquals(expectedRoutingMapQ3, q3.getRoutingTable());
		
		// Wir fuegen eine Verbindung Q3 --[4]--> Q4 hinzu
		UpdateMessage m4 = new UpdateMessage(4, q4, List.of(Set.of(3, 4), Set.of(3, 5)));
		q0.receive(m4);
				
		expectedRoutingMapQ3.put(4, q4);
		assertEquals(expectedRoutingMapQ3, q3.getRoutingTable());
	}
}



