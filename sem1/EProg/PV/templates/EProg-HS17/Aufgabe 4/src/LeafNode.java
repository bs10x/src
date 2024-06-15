/**
 * Ein "Blattknoten" in einem {@link Tree}. Hat einen <code>int</code>-Wert
 * und keine Kindknoten.
 */
public class LeafNode implements Node {

	int value;

	public LeafNode(int value) {
		this.value = value;
	}
}