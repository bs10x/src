/**
 * A singly-linked list of integer values.
 */
public class LinkedIntList {

    IntNode first;
    IntNode last;
    int size;


    /**
     * Appends 'value' at the end of the list.
     */
    public void addLast(int value) {
        IntNode newNode = new IntNode(value);
        if (isEmpty())
            first = newNode;
        else
            last.next = newNode;

        last = newNode;
        size++;
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Reverses all underlying nodes.
     */
    public void reverse() {
    	
    	int sizeBefore = getSize();
    	
    	IntNode current;
    	LinkedIntList reversedOrder = new LinkedIntList();
    	
    	for (int i = sizeBefore -1; i >= 0; i--) {
    		System.out.println(i);
    		System.out.println(getNode(i));
    		
    		current = getNode(i);
    		
    		reversedOrder.addLastNode(current);
//    		current = current.next;
//    		System.out.println("size before anything: " + size + " at loop: " + i);
    		//addLastNode(getNode(i));
//    		System.out.println("size after addLastNode: " + size + " at loop: " + i);
//    		removeFirstNode();
//    		System.out.println("size after removeFirstNode: " + size + " at loop: " + i);
    		
//    		if (i == size -1) {
//    			IntNode newFirst = getNode(i);
//    			removeFirstNode();
//    			first = newFirst;
//    		}
    	}
    	
    	clear();
    	for (int i = 0; i < sizeBefore; i++) {    		
    		addFirstNode(reversedOrder.getNode(i));
    	}
    	
    	for (int i = 0; i < sizeBefore; i++) {
//    		removeFirstNode();
    	}
    }
    
//     F			   L
//    [0] [1] [2] [3] [4]
//    [0] [1] [2] [3] [4] [4] 
//    

    public void addLastNode(IntNode newLast) {
    	

//    	IntNode current = first;
//    	for (int i = 0; i < size -1; i++) {
//    		current = current.next;
//    	}
//    	current.next = newLast;
    	
    	
    	if (isEmpty()) first = newLast;
    	else last.next = newLast;
    	last = newLast;
    	size++;
    }
    
    
    
    public void addFirstNode(IntNode newFirst) {
    	if (isEmpty()) last = newFirst;
    	else newFirst.next = first;
    	first = newFirst;
    	size ++;
    	} 
    	
    
    public void removeFirstNode() {
    	if (isEmpty()) {
    		Errors.error("removeFirstNode() on empty list!");
    	}
    	if (size == 1) {
    		// List has only one element, so just clear it
    		clear();
    	} else {
    		first = first.next;
    		size--;
    	}
    }
    
    public void removeLastNode() {
    	if (isEmpty()) {
    		Errors.error("removeLastNode() on empty list!");
    	}
    	if (size == 1) {
    		// List has only one element, so just clear it
    		clear();
    	} else {
    		IntNode current = first;
    		for (int i = 0; i < size -1; i++) {

    			
    		}
    		first = first.next;
    		size--;
    	}
    	
    	
    	
    }
//    
//    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Inserts 'value' at position 0 in the list.
     */
    public void addFirst(int value) {
        IntNode newNode = new IntNode(value);
        newNode.next = first;
        first = newNode;
        if (last == null)
            last = newNode;
        size++;
    }

    /**
     * Removes and returns the first value of the list.
     */
    public int removeFirst() {
        if (isEmpty()) {
            Errors.error("removeFirst() on empty list!");
        }

        int value = first.value;
        if (first == last) {
            // List has only one element, so just clear it
            clear();
        } else {
            first = first.next;
            size--;
        }

        return value;
    }
    

    /**
     * Removes and returns the last value of the list.
     */
    public int removeLast() {
        if (isEmpty()) {
            Errors.error("removeLast() on empty list!");
        }

        int value = last.value;
        if (first == last) {
            // List has only one element, so just clear it
            clear();
        } else {
            // List has more than one element
            IntNode currentNode = first;
            while (currentNode.next != last)
                currentNode = currentNode.next;

            currentNode.next = null;
            last = currentNode;
            size--;
        }
        return value;
    }

    /**
     * Removes all values from the list, making the list empty.
     */
    public void clear() {
        first = last = null;
        size = 0;
    }

    /**
     * Returns whether the list is empty (has no values).
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the integer value at position 'index'.
     */
    public int get(int index) {
        return getNode(index).value;
    }

    /**
     * Sets the integer value at position 'index' to 'value'.
     */
    public void set(int index, int value) {
        getNode(index).value = value;
    }

    /**
     * Returns the size of the list.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns a new int-array with the same contents as the list.
     */
    public int[] toArray() {
        int[] array = new int[size];
        int i = 0;
        for (IntNode n = first; n != null; n = n.next, i++)
            array[i] = n.value;
        return array;
    }

    /**
     * For internal use only.
     */
    public IntNode getNode(int index) {
        if (index < 0 || index >= size) {
            Errors.error("getNode() with invalid index: " + index);
        }

        IntNode current = first;
        for (int i = 0; i < index; i++)
            current = current.next;
        return current;
    }
}
