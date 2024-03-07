/**
 * A singly-linked list of {@link Node}s with fast {@link #addFirst(Node)} and
 * {@link #addLast(Node)} methods.
 */

public class LinkedNodeList {
    
	NodeLink first;
	NodeLink last;
	int size;
	

	
	Node get(int index) {
			return getNode(index).value;
	}
	
	void set(int index, Node value) {
			getNode(index).value = value;
	}
	
	boolean isEmpty() {
			return size == 0;
	}
	
	void addFirst(Node value) {
			NodeLink newNode = new NodeLink(value);
			newNode.next = first;
			first = newNode;
			if(last == null) {
				last = newNode;
			}
			
			size++;
	}
	
	void addLast(Node value) {
			NodeLink newNode = new NodeLink(value);
			if(isEmpty()) {
				first = newNode;
			} else {
				last.next = newNode;
			}					
			
			last = newNode;
			size++;
	}
	
	Node removeFirst() {
			if(isEmpty()) {
				throw new AssertionError();
			}
			
			Node value = first.value;
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
	
	Node removeLast() {
			if(isEmpty()) {
				throw new AssertionError();
			}
			
			Node value = last.value;
			if(first == last) {
					// List has only one element, so just clear it
					clear();
			}
			else {
					// List has more than one element
					NodeLink currentNode = first;
					while(currentNode.next != last) {
						currentNode = currentNode.next;
					}	
					
					currentNode.next = null;
					last = currentNode;
					size--;
			}
			return value;
	}
	
	Node remove(int index) {
		if (index == 0) {
			return removeFirst();
		} else if (index == size-1) {
			return removeLast();
		} else {
			NodeLink node = getNode(index-1);
			Node removed = node.next.value;
			node.next = node.next.next;
			size -= 1;
			return removed;
		}
	}
	
	boolean contains(Node room) {
		for(NodeLink n = first; n != null; n = n.next) {
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
	
	Node[] toArray() {
			Node[] array = new Node[size];
			int i = 0;
			for(NodeLink n = first; n != null; n = n.next, i++) {
				array[i] = n.value;
			}
			return array;
	}
	
	/**
	 * For internal use only.
	 */
	NodeLink getNode(int index) {
			if(index >= size)
					throw new AssertionError();
			
			NodeLink current = first;
			for(int i = 0; i < index; i++) {
				current = current.next;
			}
			return current;
	}
	
	static LinkedNodeList of(Node... Nodes) {
		LinkedNodeList result = new LinkedNodeList();
		for (int i = 0; i < Nodes.length; i += 1) {
			result.addLast(Nodes[i]);
		}
		return result;
	}
}
