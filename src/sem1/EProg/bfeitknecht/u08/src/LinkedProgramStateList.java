/**
 * A singly-linked list of {@link ProgramState}s with fast {@link #addFirst(ProgramState)} and
 * {@link #addLast(ProgramState)} methods.
 */

// Sie duerfen diese Datei nicht aendern
public class LinkedProgramStateList {
    
	ProgramStateNode first;
	ProgramStateNode last;
	int size;
	
	ProgramState get(int index) {
			return getNode(index).value;
	}
	
	void set(int index, ProgramState value) {
			getNode(index).value = value;
	}
	
	boolean isEmpty() {
			return size == 0;
	}
	
	void addFirst(ProgramState value) {
			ProgramStateNode newNode = new ProgramStateNode(value);
			newNode.next = first;
			first = newNode;
			if(last == null) {
				last = newNode;
			}
			
			size++;
	}
	
	void addLast(ProgramState value) {
			ProgramStateNode newNode = new ProgramStateNode(value);
			if(isEmpty()) {
				first = newNode;
			} else {
				last.next = newNode;
			}					
			
			last = newNode;
			size++;
	}
	
	ProgramState removeFirst() {
			if(isEmpty()) {
				throw new AssertionError();
			}
			
			ProgramState value = first.value;
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
	
	ProgramState removeLast() {
			if(isEmpty()) {
				throw new AssertionError();
			}
			
			ProgramState value = last.value;
			if(first == last) {
					// List has only one element, so just clear it
					clear();
			}
			else {
					// List has more than one element
					ProgramStateNode currentNode = first;
					while(currentNode.next != last) {
						currentNode = currentNode.next;
					}	
					
					currentNode.next = null;
					last = currentNode;
					size--;
			}
			return value;
	}
	
	ProgramState remove(int index) {
		if (index == 0) {
			return removeFirst();
		} else if (index == size-1) {
			return removeLast();
		} else {
			ProgramStateNode node = getNode(index-1);
			ProgramState removed = node.next.value;
			node.next = node.next.next;
			size -= 1;
			return removed;
		}
	}
	
	boolean contains(ProgramState room) {
		for(ProgramStateNode n = first; n != null; n = n.next) {
			if (n.value == room) {
				return true;
			}
		}
		return false;
	}
	
	void clear() {
			first = last = null;
			size = 0;
	}
	
	ProgramState[] toArray() {
			ProgramState[] array = new ProgramState[size];
			int i = 0;
			for(ProgramStateNode n = first; n != null; n = n.next, i++) {
				array[i] = n.value;
			}
			return array;
	}
	
	/**
	 * For internal use only.
	 */
	ProgramStateNode getNode(int index) {
			if(index >= size)
					throw new AssertionError();
			
			ProgramStateNode current = first;
			for(int i = 0; i < index; i++) {
				current = current.next;
			}
			return current;
	}
	
	static LinkedProgramStateList of(ProgramState... programStates) {
		LinkedProgramStateList result = new LinkedProgramStateList();
		for (int i = 0; i < programStates.length; i += 1) {
			result.addLast(programStates[i]);
		}
		return result;
	}
}
