import java.util.HashMap;
import java.util.Map;

public class Node {
	public Map<Character, Node> transitions = new HashMap<>();
	
	public Node() {
		this.transitions = new HashMap<>();
	}
	
	public String toString() {
		String t = "";
		for (char c : transitions.keySet()) {
			t = t + c + " ";
		}
		return t;
	}
	
	/**
	 * Utility Methode die neue {@link Node} Objekte verlinkt und erstellt, sodass
	 * Wort {@code w}, von diesem Node aus akzeptiert wird.
	 * 
	 * Um einer State Machine, die durch {@link Matcher} repräsentiert wird, ein Wort \
	 * so hinzuzufügen, muss der zurückgegebene Knoten, zur Menge der Enknoten hinzugefügt
	 * werden. Zum Beispiel:
	 * 
	 * <code>
	 * Node start = new Node();
	 * 
	 * Matcher m = new Matcher(start, Set.of(
	 * 	start.addWord("mnop"),
	 * 	start.addWord("mnqr")
	 * ));
	 * // 'm' akzeptiert nun "mnop" und "mnqr" 
	 * </code>
	 * 
	 */
	public Node addWord(String w) {
		Node n = this;
		
		char c = w.charAt(0);
		// match existing prefix
		while (n.transitions.get(c) != null && w.length() > 0) {
			n = n.transitions.get(c);
			w = w.substring(1);
			
			if (w.length() > 0) {
				c = w.charAt(0);
			}
		}
		
		// create new nodes for remainder of 'w'
		while (w.length() > 0) {
			c = w.charAt(0);
			Node next = new Node();
			n.transitions.put(c, next);
			w = w.substring(1);
			
			n = next;
		}
		
		return n;
	}
}
