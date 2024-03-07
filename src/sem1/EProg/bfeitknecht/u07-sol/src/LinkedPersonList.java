/**
 * A doubly-linked list of {@link Person}s with fast {@link #addFirst(Person)},
 * {@link #addLast(Person)}, {@link #removeFirst()}, and {@link #removeLast()} methods. Removal in
 * the interior of the list is supported as well.
 */
public class LinkedPersonList {
    
    PersonNode first;
    PersonNode last;
    int size;
    
    Person get(int index) {
        return getNode(index).person;
    }
    
    void set(int index, Person value) {
        getNode(index).person = value;
    }
    
    boolean isEmpty() {
        return size == 0;
    }
    
    void addFirst(Person value) {
        PersonNode newNode = new PersonNode(value);
        if(isEmpty())
            last = newNode;
        else {
            first.prev = newNode;
            newNode.next = first;
        }
        
        first = newNode;
        size++;
    }
    
    void addLast(Person value) {
        PersonNode newNode = new PersonNode(value);
        if(isEmpty())
            first = newNode;
        else {
            last.next = newNode;
            newNode.prev = last;
        }
        
        last = newNode;
        size++;
    }
    
    Person removeFirst() {
        if(isEmpty())
            Errors.error("nothing to remove");
        
        Person value = first.person;
        if(first == last) {
            // List has only one element, so just clear it
            clear();
        }
        else {
            first = first.next;
            first.prev = null;
            size--;
        }
        
        return value;
    }
    
    Person removeLast() {
        if(isEmpty())
            Errors.error("nothing to remove");
        
        Person value = last.person;
        if(first == last) {
            // List has only one element, so just clear it
            clear();
        }
        else {
            last = last.prev;
            last.next = null;
            size--;
        }
        return value;
    }
    
    void removeNode(PersonNode node) {
        if(node == first)
            removeFirst();
        else if(node == last)
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
     * Returns a new Person-array with the same contents as the list.
     */
    Person[] toArray() {
        Person[] array = new Person[size];
        int i = 0;
        for(PersonNode n = first; n != null; n = n.next, i++)
            array[i] = n.person;
        return array;
    }
    
    /**
     * For internal use only.
     */
    PersonNode getNode(int index) {
        if(index < 0 || index >= size)
            Errors.error("index out of bounds: " + index);
        
        PersonNode current = first;
        for(int i = 0; i < index; i++)
            current = current.next;
        return current;
    }
}
