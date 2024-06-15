
import java.util.*;

// Sie duerfen alles an dieser Klasse aendern,
// ausser dem Namen der Klasse und der Signatur des Konstruktors und der getSum Methode.

public class CountingNode implements Node /* Sie koennen auch implements und extends aendern. */{

	CountingNode(Map<Integer, Node> routingTable) {
		// TODO
	}

	private int sum = 0;
	
	public int getSum() {
		return sum;
	}

	@Override
	public void receive(Message msg) {
		
	}
}
