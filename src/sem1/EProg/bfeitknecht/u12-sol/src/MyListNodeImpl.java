
public class MyListNodeImpl<T> implements MyListNode<T> {
	
	T value;
	MyListNodeImpl<T> next;
	
	public MyListNodeImpl(T value, MyListNodeImpl<T> next){
		this.value = value;
		this.next = next;
	}
	
	public MyListNodeImpl(T value){
		this.value = value;
		this.next = null;
	}
	
	public T value() { return value; }
	public boolean hasNext() { return next != null; }
	public MyListNode<T> next() { 
		return next;
	}
}
