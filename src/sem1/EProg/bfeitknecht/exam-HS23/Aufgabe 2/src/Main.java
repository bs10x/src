import java.util.Set;

public class Main {
	
	public static void main(String[] args) {
	    // Sie koennen die main Methode beliebig aendern.
		
		// Einstiegsknoten erstellen
		Node start = new Node();
		
		// Woerter zur State Machine hinzufuegen
		Matcher m = new Matcher(start, Set.of(
				start.addWord("defcd"),
				start.addWord("abcd"),
				start.addWord("abc")
		));
		
		// Suffixe testen		
		System.out.println("Woerter die mit zusaetzlichem Suffix 'cd' akzeptiert werden: " + m.suffix("cd"));
	}
	
}
