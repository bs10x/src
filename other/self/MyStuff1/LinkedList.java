package bs10x.var;

import java.util.List;

public class LinkedList<T> {
	private Node<T> head;
	private int size;
	
	public LinkedList() {
		this.head = null;
		this.size = 0;
	}
	
	public LinkedList(T[] dataArray) {
		if (dataArray.equals(null)) throw new IllegalArgumentException();
		for (T data : dataArray) {
			add(data);
		}
	}
	
	public LinkedList(List<T> dataList) {
		if (dataList.equals(null)) throw new IllegalArgumentException();
		for (T data : dataList) {
			add(data);
		}
	}
	
	public int size() {
		return size;
	}
	
	public void add(T data) {
		add(size, data);
	}
	
	public void add(int index, T data) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		
		Node<T> newNode = new Node<>(data);
		if (index == 0) {
			newNode.next = head;
			head = newNode;
		}
		else {
			Node<T> current = head;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			newNode.next = current.next;
			current.next = newNode;
		}
		size++;
	}
	
	public void remove(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		
		if (index == 0) {
			head = head.next;
		}
		else {
			Node<T> current = head;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = current.next.next;
		}
		size--;	
	}
	
	public T get(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
	        current = current.next;
	    }
		return current.getData();
	}
	
	
	
	
	

}
