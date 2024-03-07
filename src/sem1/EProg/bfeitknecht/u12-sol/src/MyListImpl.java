import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyListImpl<T> implements MyList<T> {

	MyListNodeImpl<T> first;
	MyListNodeImpl<T> last;
    int size;
    
    public T get(int index) {
        return getNode(index).value;
    }
    
    public void set(int index, T value) {
        getNode(index).value = value;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void addFirst(T value) {
        MyListNodeImpl<T> newNode = new MyListNodeImpl<T>(value);
        newNode.next = first;
        first = newNode;
        if(last == null)
            last = newNode;
        size++;
    }
    
    public void addLast(T value) {
    	MyListNodeImpl<T> newNode = new MyListNodeImpl<T>(value);
        if(isEmpty())
            first = newNode;
        else
            last.next = newNode;
        
        last = newNode;
        size++;
    }
    
    public void addAll(MyList<T> other) {
    	if (!other.isEmpty()) {
    		MyListNodeImpl<T> newNode = (MyListNodeImpl<T>) other.getNode(0);
            if(isEmpty())
                first = newNode;
            else
                last.next = newNode;
            
            last = (MyListNodeImpl<T>) other.getNode(other.getSize() - 1);
            size += other.getSize();
    	}
    }
    
    public T removeFirst() {
        if(isEmpty())
            throw new NoSuchElementException();
        
        T value = first.value;
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
    
    public T removeLast() {
        if(isEmpty())
            throw new NoSuchElementException();
        
        T value = last.value;
        if(first == last) {
            // List has only one element, so just clear it
            clear();
        }
        else {
            // List has more than one element
            MyListNodeImpl<T> currentNode = first;
            while(currentNode.next != last)
                currentNode = currentNode.next;
            
            currentNode.next = null;
            last = currentNode;
            size--;
        }
        return value;
    }
    
    public void clear() {
        first = last = null;
        size = 0;
    }
    
    public Iterator<T> iterator() {
    	return new MyListIterator<T>(first);
    }
    
    public MyListNodeImpl<T> getNode(int index) {
        if(index >= size)
            throw new NoSuchElementException();
        
        MyListNodeImpl<T> current = first;
        for(int i = 0; i < index; i++) {
        	current = current.next;
        }
        return current;
    }

	@Override
	public int getSize() {
		return size;
	}
}
