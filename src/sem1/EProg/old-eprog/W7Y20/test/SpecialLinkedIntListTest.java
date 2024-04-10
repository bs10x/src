import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class SpecialLinkedIntListTest {

	public SpecialLinkedIntList arrayToList(int[] elems) {
		SpecialLinkedIntList res = new SpecialLinkedIntList();

		if (elems.length == 1) {
			res.first = res.last = new SpecialIntNode(elems[0]);
			res.size = 1;
		} else if (elems.length > 1) {
			SpecialIntNode newNode = new SpecialIntNode(elems[0]);
			res.first = newNode;

			for (int i = 1; i < elems.length; i++) {
				newNode.next = new SpecialIntNode(elems[i]);
				newNode = newNode.next;
			}
			res.last = newNode;
			res.size = elems.length;
		}

		return res;
	}
    
    @Test
    public void exampleTest() {
    	SpecialLinkedIntList listA = arrayToList(new int[] {3, 7, 5, 5, 2});
    	SpecialLinkedIntList listB = arrayToList(new int[] {5, 3, 8});
    	
    	// Resultiert in einer Liste mit den Werten 7, 2 (diese Reihenfolge!)
    	SpecialLinkedIntList result = listA.subtract(listB);
    	assertArrayEquals(new int[] {7, 2}, result.toArray());
    	
    	// Updated den Wert 2 zu dem Wert 8
    	result.set(1, 8);
    	assertArrayEquals(new int[] {7, 8}, result.toArray());
    	
    	// Weil result und listA die gleichen Nodes haben,
    	// ist auch listA von dem Update des Wertes betroffen.
    	assertArrayEquals(new int[] {3, 7, 5, 5, 8}, listA.toArray());
    	
    	// Das Gleiche gilt auch umgekehrt.
    	listA.set(1, 52);
    	assertArrayEquals(new int[] {3, 52, 5, 5, 8}, listA.toArray());
    	assertArrayEquals(new int[] {52, 8}, result.toArray());
    }
    
    
    
    
    
    
    
    
    
    
    
    // Tests for the other functions of the list:
    
    public void assertConsistency(SpecialLinkedIntList ll) {
		assertEquals(ll.first == null, ll.last == null);
		assertEquals(ll.first == null, ll.isEmpty());
		assertEquals(ll.first == null, ll.size == 0);

		if (!ll.isEmpty())
			assertTrue(ll.last.next == null);
		
		int count = 0;
		for (SpecialIntNode n = ll.first; n != null; n = n.next) {
			count++;
			if (n.next == null)
				assertTrue(n == ll.last);
		}
		
		assertEquals(count, ll.size);
	}
    
    @Test
    public void testRandom() {
        Random rand = new Random(42);
        SpecialLinkedIntList ll = new SpecialLinkedIntList();
        for(int n = 0; n < 9721; n++) {
            int choice = rand.nextInt(6);
            int value = rand.nextInt();
            
            if(choice == 0)
                ll.addFirst(value);
            else if(choice == 1)
                ll.addLast(value);
            else if(choice == 2 && !ll.isEmpty())
                ll.removeFirst();
            else if(choice == 3 && !ll.isEmpty())
                ll.removeLast();
            else if(choice == 4 && !ll.isEmpty())
                ll.set(rand.nextInt(ll.size), value);
            else if(choice == 5 && value % 5 == 0)
                ll.clear();
        }
//        IntNode cur = ll.getNode(0);
//        do{
//        	System.out.println(cur.value);
//        	cur=cur.next;
//        }while(cur!=null&&cur!=null);
        
        String msg = "There is a bug in your LinkedIntList";
        assertFalse(msg, ll.isEmpty());
        assertEquals(msg, 5, ll.size);
        int[] expect = new int[]{-1759285598, -424345731,
                -1746103561, -1623993329, -1831395423};
        assertArrayEquals(msg, expect, ll.toArray());
    }
    
	int[] one = { 42 };
	int[] two = { 3, 1 };
	int[] three = { 2, 7, 1 };

	@Test
	public void testGet() {
		assertEquals(42, arrayToList(one).get(0));

		SpecialLinkedIntList ll = arrayToList(two);
		assertEquals(3, ll.get(0));
		assertEquals(1, ll.get(1));

		ll = arrayToList(three);
		assertEquals(2, ll.get(0));
		assertEquals(7, ll.get(1));
		assertEquals(1, ll.get(2));
		assertEquals(3, ll.size);
	}

	@Test
	public void testSet() {
		SpecialLinkedIntList ll = arrayToList(one);
		ll.set(0, 11);
		assertEquals(11, ll.get(0));

		ll = arrayToList(two);
		ll.set(1, 11);
		assertArrayEquals(new int[] { 3, 11 }, ll.toArray());
		ll.set(0, 12);
		assertArrayEquals(new int[] { 12, 11 }, ll.toArray());

		ll = arrayToList(three);
		ll.set(0, 0);
		ll.set(1, 1);
		ll.set(2, 2);
		assertArrayEquals(new int[] { 0, 1, 2 }, ll.toArray());
		assertEquals(3, ll.size);

	}

	@Test
	public void testIsEmpty() {
		assertEquals(true, new SpecialLinkedIntList().isEmpty());
		assertEquals(false, arrayToList(one).isEmpty());
		assertEquals(false, arrayToList(three).isEmpty());
	}

	@Test
	public void testAddFirst() {
		SpecialLinkedIntList ll = new SpecialLinkedIntList();
		ll.addFirst(11);
		assertEquals(11, ll.get(0));
		assertEquals(1, ll.size);
		assertConsistency(ll);

		ll = arrayToList(one);
		ll.addFirst(11);
		assertArrayEquals(new int[] { 11, 42 }, ll.toArray());
		assertEquals(2, ll.size);
		assertConsistency(ll);
		ll.addFirst(12);
		assertArrayEquals(new int[] { 12, 11, 42 }, ll.toArray());
		assertEquals(3, ll.size);
		assertConsistency(ll);
	}

	@Test
	public void testAddLast() {
		SpecialLinkedIntList ll = new SpecialLinkedIntList();
		ll.addLast(11);
		assertEquals(11, ll.get(0));
		assertEquals(1, ll.size);
		assertConsistency(ll);

		ll = arrayToList(one);
		ll.addLast(11);
		assertArrayEquals(new int[] { 42, 11 }, ll.toArray());
		assertEquals(2, ll.size);
		assertConsistency(ll);
		ll.addLast(12);
		assertArrayEquals(new int[] { 42, 11, 12 }, ll.toArray());
		assertEquals(3, ll.size);
		assertConsistency(ll);
	}

	@Test
	public void testRemoveFirst() {
		SpecialLinkedIntList ll = arrayToList(one);
		assertEquals(42, ll.removeFirst());
		assertEquals(0, ll.size);
		assertConsistency(ll);

		ll = arrayToList(three);
		assertEquals(2, ll.removeFirst());
		assertEquals(2, ll.size);
		assertConsistency(ll);
		assertEquals(7, ll.removeFirst());
		assertEquals(1, ll.size);
		assertConsistency(ll);
		assertEquals(1, ll.removeFirst());
		assertEquals(0, ll.size);
		assertConsistency(ll);
	}

	@Test
	public void testRemoveLast() {
		SpecialLinkedIntList ll = arrayToList(one);
		assertEquals(42, ll.removeLast());
		assertEquals(0, ll.size);
		assertConsistency(ll);

		ll = arrayToList(three);
		assertEquals(1, ll.removeLast());
		assertEquals(2, ll.size);
		assertConsistency(ll);
		assertEquals(7, ll.removeLast());
		assertEquals(1, ll.size);
		assertConsistency(ll);
		assertEquals(2, ll.removeLast());
		assertEquals(0, ll.size);
		assertConsistency(ll);
	}

	@Test
	public void testClear() {
		SpecialLinkedIntList ll = new SpecialLinkedIntList();
		ll.clear();
		assertEquals(0, ll.size);
		assertConsistency(ll);

		ll = arrayToList(one);
		ll.clear();
		assertEquals(0, ll.size);
		assertConsistency(ll);

		ll = arrayToList(two);
		ll.clear();
		assertEquals(0, ll.size);
		assertConsistency(ll);

		ll = arrayToList(three);
		ll.clear();
		assertEquals(0, ll.size);
		assertConsistency(ll);
	}

	@Test
	public void testToArray() {
		SpecialLinkedIntList ll = new SpecialLinkedIntList();
		assertArrayEquals(new int[]{}, ll.toArray());
		assertArrayEquals(one, arrayToList(one).toArray());
		assertArrayEquals(two, arrayToList(two).toArray());
		assertArrayEquals(three, arrayToList(three).toArray());
	}

}