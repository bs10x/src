

public interface MyListNode<T> {
	
	/** Returns the value stored in the node. */
	public T value(); 
	
	/** Returns false iff this is the last node of the list. */
	public boolean hasNext();
	
	/** Returns next node. */
	public MyListNode<T> next();
}
