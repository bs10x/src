import java.util.NoSuchElementException;

public class CircularLinkedIntList implements IntList {

	IntNode last;
	int size;

	/**
	 * Return the integer value at position 'index'
	 */
	public int get(int index) {
		return getNode(index).value;
	}

	/**
	 * Set the integer value at position 'index' to 'value'
	 */
	public void set(int index, int value) {
		getNode(index).value = value;
	}

	/**
	 * Returns whether the list is empty (has no values)
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the size of the list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Inserts 'value' at position 0 in the list.
	 */
	public void addFirst(int value) {
		IntNode newNode = new IntNode(value);
		if (last == null) {
			last = newNode;
		} else {
			newNode.next = last.next;
		}

		last.next = newNode;
		size += 1;
	}

	/**
	 * Appends 'value' at the end of the list.
	 */
	public void addLast(int value) {
		IntNode newNode = new IntNode(value);

		if (last == null) {
			last = newNode;
		} else {
			newNode.next = last.next;
		}
		last.next = newNode;
		last = newNode;
		size += 1;
	}

	/**
	 * Removes and returns the first value of the list.
	 * Throws a NoSuchElementException if the List is empty.
	 */
	public int removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException("removeFirst() on empty list!");
		}

		int value = last.next.value;
		if (size == 1) {
			// List has only one element, so just clear it
			clear();
		} else {
			last.next = last.next.next;
			size -= 1;
		}

		return value;
	}

	/**
	 * Removes and returns the last value of the list.
	 * Throws a NoSuchElementException if the List is empty.
	 */
	public int removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException("removeFirst() on empty list!");
		}

		int value = last.value;
		if (size == 1) {
			// List has only one element, so just clear it
			clear();
		} else {
			// List has more than one element
			IntNode newLast = getNode(size - 2);
			newLast.next = last.next;
			last = newLast;
			size -= 1;
		}
		return value;
	}

	/**
	 * Removes all values from the list, making the list empty.
	 */
	public void clear() {
		last = null;
		size = 0;
	}

	/**
	 * Returns a new int-array with the same contents as the list.
	 */
	public int[] toArray() {
		int[] array = new int[size];

		if (size != 0) {
			IntNode n = last.next;
			for (int i = 0; i < size; i += 1) {
				array[i] = n.value;
				n = n.next;
			}
		}

		return array;
	}

	/**
	 * For internal use only.
	 */
	public IntNode getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("getNode() with invalid index: " + index);
		}

		IntNode current = last.next;
		for (int i = 0; i < index; i += 1) {
			current = current.next;
		}

		return current;
	}
}
