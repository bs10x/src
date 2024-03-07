
public class Main {

	public static void main(String[] args) {
		
		
		LinkedIntList list = new LinkedIntList();
		list.addLast(0);
		list.addLast(1);
		list.addLast(2);
		list.addLast(3);
		list.addLast(4);
		list.addLast(5);
		
		IntNode oldFirst = list.first;
		IntNode oldLast  = list.last;
		IntNode oldSecond = list.first.next;
		System.out.println("size of the old list: " + list.getSize());
		System.out.println("order of oldList:");
		
		for (int i = 0; i < list.getSize(); i++) {
			System.out.println(list.getNode(i));
		}
		
		System.out.println("\n");
		
		list.reverse();
		
		
//		System.out.println(list.last == oldFirst);
//		System.out.println(list.first == oldLast);
//		System.out.println(list.first.next.next == oldSecond);
		
//		System.out.println(oldLast);
//		System.out.println(oldSecond);
//		System.out.println(oldFirst + "\n");
		System.out.println("size of the new list: " + list.getSize());
		System.out.println("order of newList:");
		
		for (int i = 0; i < list.getSize(); i++) {
			System.out.println(list.getNode(i));
		}
		
//		System.out.println(list.first);
//		System.out.println(list.first.next);
//		System.out.println(list.last);
		
		
//		System.out.println("oldFirst: " + oldFirst);
//		System.out.println("oldSeconds: " + oldSecond);
//		System.out.println("oldLast: " + oldLast +"\n\n");
//		
//		System.out.println("newFirst: " + list.first);
//		System.out.println("newSecond: " + list.first.next);
//		System.out.println("newLast: " + list.last);


	}

}
