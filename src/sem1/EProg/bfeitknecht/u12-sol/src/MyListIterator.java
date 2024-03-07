
import java.util.Iterator;

public class MyListIterator<T> implements Iterator<T> {
	
	MyListNode<T> current;
	
	public MyListIterator(MyListNode<T> current) {
		this.current = current;
	}

	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public T next() {
		T result = current.value();
		current = current.next();
		return result;
	}
	
	
}
