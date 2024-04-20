import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		// Sie koennen die main Methode beliebig aendern.
		
		// Wir erzeugen 5 Knoten mit folgenden Verbindungen:
		// N2 --[3]--> N0
		// N2 --[1]--> N1
		// N3 --[1]--> N2
		RoutingNode n0 = new RoutingNode(new HashMap<Integer, Node>());
		RoutingNode n1 = new RoutingNode(new HashMap<Integer, Node>());
		
		// Wenn noch nichts implementiert wurde, dann wird der Code eine NullPointerException werfen.
		Map<Integer, Node> routingTableN2 = new HashMap<Integer, Node>();
		routingTableN2.put(3, n0);
		routingTableN2.put(1, n1);
		RoutingNode n2 = new RoutingNode(routingTableN2);
		
		Map<Integer, Node> routingTableN3 = new HashMap<Integer, Node>();
		routingTableN3.put(1, n2);
		RoutingNode n3 = new RoutingNode(routingTableN3);
		
		// Die Nachricht sollte zu N1 geschickt werden.
		IntMessage m1 = new IntMessage(1, List.of(Set.of(1, 5), Set.of(1, 3)));
		n3.receive(m1);
		
		System.out.println("Ende");
	}

}
