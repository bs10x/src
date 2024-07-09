package bs10x.var;

public class Node<T> {
	private T data;
	Node<T> next;
	
	public Node(T data) {
		this.data = data;
		this.next = null;
	}

	public T getData() {
		return data;
	}
	

}
