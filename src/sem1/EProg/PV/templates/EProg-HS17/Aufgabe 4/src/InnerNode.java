/**
 * Ein innerer Knoten in einem {@link Tree}. Hat selber keinen Wert, aber
 * bis zu 3 Kindknoten. Die Kindknoten werden durch 'L', 'M' und 'R'
 * bezeichnet. Siehe auch {@link Tree#dirToIndex(char)}.
 */
public class InnerNode implements Node {

	Node[] children = new Node[3];

}
