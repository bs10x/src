/**
 * A doubly-linked list of {@link String}s with fast {@link #addFirst(String)},
 * {@link #addLast(String)}, {@link #removeFirst()}, and {@link #removeLast()} methods. Removal in
 * the interior of the list is supported as well.
 */
public class LinkedStringList {

    StringNode first;
    StringNode last;
    int size;

    String get(int index) {
        return getNode(index).value;
    }

    void set(int index, String value) {
        getNode(index).value = value;
    }

    boolean isEmpty() {
        return size == 0;
    }

    void addFirst(String value) {
        StringNode newNode = new StringNode(value);
        if (isEmpty())
            last = newNode;
        else {
            first.prev = newNode;
            newNode.next = first;
        }

        first = newNode;
        size++;
    }

    void addLast(String value) {
        StringNode newNode = new StringNode(value);
        if (isEmpty())
            first = newNode;
        else {
            last.next = newNode;
            newNode.prev = last;
        }

        last = newNode;
        size++;
    }

    String removeFirst() {
        if (isEmpty())
            throw new AssertionError();

        String value = first.value;
        if (first == last) {
            // List has only one element, so just clear it
            clear();
        } else {
            first = first.next;
            first.prev = null;
            size--;
        }

        return value;
    }

    String removeLast() {
        if (isEmpty())
            throw new AssertionError();

        String value = last.value;
        if (first == last) {
            // List has only one element, so just clear it
            clear();
        } else {
            last = last.prev;
            last.next = null;
            size--;
        }
        return value;
    }

    void removeNode(StringNode node) {
        if (node == first)
            removeFirst();
        else if (node == last)
            removeLast();
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
    }

    void clear() {
        first = last = null;
        size = 0;
    }

    /**
     * Returns a new String-array with the same contents as the list.
     */
    String[] toArray() {
        String[] array = new String[size];
        int i = 0;
        for (StringNode n = first; n != null; n = n.next, i++)
            array[i] = n.value;
        return array;
    }

    /**
     * For internal use only.
     */
    StringNode getNode(int index) {
        if (index >= size)
            throw new AssertionError();

        StringNode current = first;
        for (int i = 0; i < index; i++)
            current = current.next;
        return current;
    }
}
