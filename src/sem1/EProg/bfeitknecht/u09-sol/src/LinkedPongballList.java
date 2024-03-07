/**
 * A doubly-linked list of {@link Pongball}s with fast {@link #addFirst(Pongball)},
 * {@link #addLast(Pongball)}, {@link #removeFirst()}, and {@link #removeLast()} methods. Removal in
 * the interior of the list is supported as well.
 */
public class LinkedPongballList {

    PongballNode first;
    PongballNode last;
    int size;

    Pongball get(int index) {
        return getNode(index).ball;
    }

    void set(int index, Pongball value) {
        getNode(index).ball = value;
    }

    boolean isEmpty() {
        return size == 0;
    }

    void addFirst(Pongball value) {
        PongballNode newNode = new PongballNode(value);
        if (isEmpty())
            last = newNode;
        else {
            first.prev = newNode;
            newNode.next = first;
        }

        first = newNode;
        size++;
    }

    void addLast(Pongball value) {
        PongballNode newNode = new PongballNode(value);
        if (isEmpty())
            first = newNode;
        else {
            last.next = newNode;
            newNode.prev = last;
        }

        last = newNode;
        size++;
    }

    Pongball removeFirst() {
        if (isEmpty())
            Errors.error("nothing to remove");

        Pongball value = first.ball;
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

    Pongball removeLast() {
        if (isEmpty())
            Errors.error("nothing to remove");

        Pongball value = last.ball;
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

    void removeNode(PongballNode node) {
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
     * Returns a new Pongball-array with the same contents as the list.
     */
    Pongball[] toArray() {
        Pongball[] array = new Pongball[size];
        int i = 0;
        for (PongballNode n = first; n != null; n = n.next, i++)
            array[i] = n.ball;
        return array;
    }

    /**
     * For internal use only.
     */
    PongballNode getNode(int index) {
        if (index < 0 || index >= size)
            Errors.error("index out of bounds: " + index);

        PongballNode current = first;
        for (int i = 0; i < index; i++)
            current = current.next;
        return current;
    }
}
