
/**
 * A singly-linked list of integer values with fast addFirst and addLast methods
 */
public class SpecialLinkedIntList {
    
    SpecialIntNode first;
    SpecialIntNode last;
    int size;
    
    SpecialLinkedIntList subtract(SpecialLinkedIntList list) {
    	// TODO: Implementieren Sie die Methode
    	// Wie immer, Sie koennen weitere Methoden und Felder hinzufügen.
    	// Aendern Sie aber nicht die anderen bestehenden Methoden und Felder.
    	
    	return null;
    }
    
    /**
     * Return the integer value at position 'index'
     */
    int get(int index) {
        return getNode(index).box.value;
    }
    
    /**
     * Set the integer value at position 'index' to 'value'
     */
    void set(int index, int value) {
        getNode(index).box.value = value;
    }
    
    /**
     * Returns whether the list is empty (has no values)
     */
    boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Inserts 'value' at position 0 in the list.
     */
    void addFirst(int value) {
        SpecialIntNode newNode = new SpecialIntNode(value);
        newNode.next = first;
        first = newNode;
        if(last == null) {
        	last = newNode;
        }
        size++;
    }
    
    /**
     * Appends 'value' at the end of the list.
     */
    void addLast(int value) {
        SpecialIntNode newNode = new SpecialIntNode(value);
        if(isEmpty()) {
        	first = newNode;
        } else {
        	last.next = newNode;
        }
        
        last = newNode;
        size++;
    }
    
    /**
     * Removes and returns the first value of the list.
     */
    int removeFirst() {
        if(isEmpty()) {
        	Errors.error("removeFirst() on empty list!");
        }
        
        int value = first.box.value;
        if(first == last) {
            // List has only one element, so just clear it
            clear();
        }
        else {
            first = first.next;
            size--;
        }
        
        return value;
    }

    /**
     * Removes and returns the last value of the list.
     */
    int removeLast() {
        if(isEmpty()) {
            Errors.error("removeLast() on empty list!");
        }
        
        int value = last.box.value;
        if(first == last) {
            // List has only one element, so just clear it
            clear();
        }
        else {
            // List has more than one element
            SpecialIntNode currentNode = first;
            while(currentNode.next != last) {
            	currentNode = currentNode.next;
            }
            
            currentNode.next = null;
            last = currentNode;
            size--;
        }
        return value;
    }
    
    /**
     * Removes all values from the list, making the list empty.
     */
    void clear() {
        first = last = null;
        size = 0;
    }
    
    /**
     * Returns a new int-array with the same contents as the list.
     */
    int[] toArray() {
        int[] array = new int[size];
        int i = 0;
        for(SpecialIntNode n = first; n != null; n = n.next, i++) {
        	array[i] = n.box.value;
        }
        return array;
    }
    
    /**
     * For internal use only.
     */
    SpecialIntNode getNode(int index) {
        if(index < 0 || index >= size) {
            Errors.error("getNode() with invalid index: " + index);
        }
        
        SpecialIntNode current = first;
        for(int i = 0; i < index; i++) {
        	current = current.next;
        }
        
        return current;
    }
}
