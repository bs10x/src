import java.util.List;

/**
 * Ein ternärer Baum, der aus {@link Node}s ({@link InnerNode}s und
 * {@link LeafNode}s) besteht. Nur {@link LeafNode}s haben Werte.
 */
public class Tree {

	Node root;

	/**
	 * Erstellt einen neuen Baum, der aus einer einzigen {@link LeafNode}
	 * mit Wert <code>rootValue</code> besteht.
	 */
	Tree(int rootValue) {
		root = new LeafNode(rootValue);
	}

	/**
	 * Fügt den gegebenen Wert <code>value</code> an der Stelle ein, die
	 * durch den Pfad <code>path</code> gegeben ist. Fehlende {@link Node}s
	 * werden erstellt. Falls an dieser Stelle bereits ein Wert oder ein
	 * Subbaum vorhanden ist, wird dieser ersetzt. Falls der Pfad ungültig
	 * oder null ist, wird eine {@link IllegalArgumentException} geworfen.
	 */
	void insert(int value, String path) {
		if(path == null) {
			throw new IllegalArgumentException();
		}
		// Ein leerer Pfad bezeichnet die Wurzel des Baums:
		if(path.isEmpty()) {
			root = new LeafNode(value);
			return;
		}

		// Falls Pfad nicht leer ist, muss Wurzel ein innerer Knoten sein:
		if(root instanceof LeafNode) {
			root = new InnerNode();
		}

		// Folge dem Pfad und füge falls nötig innere Knoten ein (oder
		// ersetze Blattknoten):
		InnerNode node = (InnerNode) root;
		while(path.length() > 1) {
			int index = dirToIndex(path.charAt(0));
			if(!(node.children[index] instanceof InnerNode))
				node.children[index] = new InnerNode();
			path = path.substring(1);
			node = (InnerNode) node.children[index];
		}

		// Pfad hat jetzt noch eine Richtung übrig. Erstelle neuen
		// Blattknoten mit gegebenem Wert:
		node.children[dirToIndex(path.charAt(0))] = new LeafNode(value);
	}

	/**
	 * Gibt den Wert zurück, der an der durch <code>path</code>
	 * gegebenen Stelle steht. Falls der Pfad ungültig ist, falls an dieser
	 * Stelle kein Wert steht, oder falls die Stelle nicht existiert, wird
	 * eine {@link IllegalArgumentException} geworfen.
	 */
	int lookup(String path) {
		// TODO
		return 0;
	}

	/**
	 * Gibt alle Werte in diesem Baum (von links nach rechts) in einer
	 * Liste zurück.
	 */
	List<Integer> toList() {
		// TODO
		return null;
	}

	/**
	 * Hilfsmethode, um eine Richtung 'L', 'M' oder 'R' in einen Index
	 * 0, 1 oder 2 umzuwandeln.
	 */
	static int dirToIndex(char dir) {
		if (dir == 'L')
			return 0;
		else if (dir == 'M')
			return 1;
		else if (dir == 'R')
			return 2;
		else
			throw new IllegalArgumentException();
	}
}
