
import java.util.*;

// Implementieren Sie die noetigen Methoden.

public class RoutingNode implements Node {
	
	private Map<Integer, Node> routingTable;
	
	// Sie koennen Felder und Methoden hinzufuegen,
	// aber aendern Sie nicht die gegebenen Signaturen!
	
	RoutingNode(Map<Integer, Node> routingTable) {
		this.routingTable = routingTable;
	}
	
	Map<Integer, Node> getRoutingTable() {
		return new HashMap<Integer, Node>(routingTable);
	}
	
	public Set<Integer> candidates(List<Set<Integer>> path) {
		// TODO
		return null;
	}
	
	public void incrementCount(int id) {
		// TODO
	}
	
	public int selectConnection(Set<Integer> candidates) {
		// TODO
		return 42;
	}
	
	@Override
	public void receive(Message msg) {
		List<Set<Integer>> path = new LinkedList<Set<Integer>>(msg.getPath());
		if (path.isEmpty()) {
			process(msg);
		} else {
			Set<Integer> candidates = candidates(path);
			if (!candidates.isEmpty()) {
				Integer target = selectConnection(candidates);
				
				List<Set<Integer>> newPath = path.subList(1, path.size());
				Message newMsg = msg.withPath(newPath);
				incrementCount(target);
				
				routingTable.get(target).receive(newMsg);
			}
		}
	}
	
	public void process(Message msg) {
		// TODO
	}
	
}
