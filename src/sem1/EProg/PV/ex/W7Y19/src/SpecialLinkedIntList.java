
/**
 * A singly-linked list of integer values with fast addFirst and addLast methods
 */
public class SpecialLinkedIntList {
    
    SpecialIntNode first;
    SpecialIntNode last;
    
    /**
     * Appends 'value' at the end of the list.
     */
    void addLast(int value) {
    	
    	// Aendern Sie diese Methode nicht.
    
        SpecialIntNode newNode = new SpecialIntNode(value);
        if(first == null) {
        	first = newNode;
        } else {
        	last.next = newNode;
        }
        last = newNode;
    }
    
    
    SpecialLinkedIntList split(int zahl) {
    	// Ihre Loesung kommt hier hin.
    	return null;
    }
    
    /** Printet die Liste wenn man den 'next' Feldern folgt. */
    void printList() {
    	
    	// Sie koennen diese Methode beliebig aendern.
    	
    	SpecialIntNode n = first;
    	System.out.print("[");
    	if (n != null) {
    		System.out.print(n.value);
    		n = n.next;
    		while(n != null) {
    			System.out.print(", " + n.value);
    			n = n.next;
    		}
    	}
    	System.out.print("]");
    	System.out.println();
    }
    
    /** Printet die Liste wenn man den 'oldNext' Feldern folgt. */
    void printOldList() {
    	
    	// Sie koennen diese Methode beliebig aendern.
    	
    	SpecialIntNode n = first;
    	System.out.print("[");
    	if (n != null) {
    		System.out.print(n.value);
    		n = n.oldNext;
    		while(n != null) {
    			System.out.print(", " + n.value);
    			n = n.oldNext;
    		}
    	}
    	System.out.print("]");
    	System.out.println();
    }
    
}
