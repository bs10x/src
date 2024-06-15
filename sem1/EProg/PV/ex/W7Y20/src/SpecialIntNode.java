
/**
 * A node of a singly-linked list of integer values
 */
public class SpecialIntNode {
    
    IntBox box;
    SpecialIntNode next;
    
    public SpecialIntNode() {}
    
    public SpecialIntNode(int value) {
        this.box = new IntBox(value);
    }
}
