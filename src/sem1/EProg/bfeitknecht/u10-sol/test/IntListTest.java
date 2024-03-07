import static org.junit.Assert.*;

import java.util.Random;
import org.junit.rules.Timeout;
import org.junit.Rule;
import org.junit.Test;

public class IntListTest {
	
	@Rule
	public Timeout globalTimeout= Timeout.seconds(5);
	
	
	
	@Test
	public void testAddLastEasy() {
		IntList ll = createIntList();
		ll.addLast(11);
		assertEquals(11, ll.get(0));
		assertEquals(1, ll.getSize());
		assertConsistency(ll);
		
		ll.addLast(42);
		assertEquals(11, ll.get(0));
		assertEquals(42, ll.get(1));
		assertEquals(2, ll.getSize());
		assertConsistency(ll);
		
		ll.addLast(57);
		assertEquals(11, ll.get(0));
		assertEquals(42, ll.get(1));
		assertEquals(57, ll.get(2));
		assertEquals(3, ll.getSize());
		assertConsistency(ll);
	}
	
	
    
    @Test
    public void testRandom() {
        Random rand = new Random(42);
        IntList ll = arrayToList(zero);
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
                ll.set(rand.nextInt(ll.getSize()), value);
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
        assertEquals(msg, 5, ll.getSize());
        int[] expect = new int[]{-1759285598, -424345731,
                -1746103561, -1623993329, -1831395423};
        assertArrayEquals(msg, expect, ll.toArray());
    }
    
    int[] zero = { };
	int[] one = { 42 };
	int[] two = { 3, 1 };
	int[] three = { 2, 7, 1 };

	@Test
	public void testGet() {
		assertEquals(42, arrayToList(one).get(0));

		IntList ll = arrayToList(two);
		assertEquals(3, ll.get(0));
		assertEquals(1, ll.get(1));

		ll = arrayToList(three);
		assertEquals(2, ll.get(0));
		assertEquals(7, ll.get(1));
		assertEquals(1, ll.get(2));
		assertEquals(3, ll.getSize());
	}

	@Test
	public void testSet() {
		IntList ll = arrayToList(one);
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
		assertEquals(3, ll.getSize());

	}

	@Test
	public void testIsEmpty() {
		assertEquals(true,  arrayToList(zero).isEmpty());
		assertEquals(false, arrayToList(one).isEmpty());
		assertEquals(false, arrayToList(three).isEmpty());
	}

	@Test
	public void testAddFirst() {
		IntList ll = arrayToList(zero);
		ll.addFirst(11);
		assertEquals(11, ll.get(0));
		assertEquals(1, ll.getSize());
		assertConsistency(ll);

		ll = arrayToList(one);
		ll.addFirst(11);
		assertArrayEquals(new int[] { 11, 42 }, ll.toArray());
		assertEquals(2, ll.getSize());
		assertConsistency(ll);
		ll.addFirst(12);
		assertArrayEquals(new int[] { 12, 11, 42 }, ll.toArray());
		assertEquals(3, ll.getSize());
		assertConsistency(ll);
	}

	@Test
	public void testAddLast() {
		IntList ll = arrayToList(zero);
		ll.addLast(11);
		assertEquals(11, ll.get(0));
		assertEquals(1, ll.getSize());
		assertConsistency(ll);

		ll = arrayToList(one);
		ll.addLast(11);
		assertArrayEquals(new int[] { 42, 11 }, ll.toArray());
		assertEquals(2, ll.getSize());
		assertConsistency(ll);
		ll.addLast(12);
		assertArrayEquals(new int[] { 42, 11, 12 }, ll.toArray());
		assertEquals(3, ll.getSize());
		assertConsistency(ll);
	}

	@Test
	public void testRemoveFirst() {
		IntList ll = arrayToList(one);
		assertEquals(42, ll.removeFirst());
		assertEquals(0, ll.getSize());
		assertConsistency(ll);

		ll = arrayToList(three);
		assertEquals(2, ll.removeFirst());
		assertEquals(2, ll.getSize());
		assertConsistency(ll);
		assertEquals(7, ll.removeFirst());
		assertEquals(1, ll.getSize());
		assertConsistency(ll);
		assertEquals(1, ll.removeFirst());
		assertEquals(0, ll.getSize());
		assertConsistency(ll);
	}

	@Test
	public void testRemoveLast() {
		IntList ll = arrayToList(one);
		assertEquals(42, ll.removeLast());
		assertEquals(0, ll.getSize());
		assertConsistency(ll);

		ll = arrayToList(three);
		assertEquals(1, ll.removeLast());
		assertEquals(2, ll.getSize());
		assertConsistency(ll);
		assertEquals(7, ll.removeLast());
		assertEquals(1, ll.getSize());
		assertConsistency(ll);
		assertEquals(2, ll.removeLast());
		assertEquals(0, ll.getSize());
		assertConsistency(ll);
	}

	@Test
	public void testClear() {
		IntList ll = arrayToList(zero);
		ll.clear();
		assertEquals(0, ll.getSize());
		assertConsistency(ll);

		ll = arrayToList(one);
		ll.clear();
		assertEquals(0, ll.getSize());
		assertConsistency(ll);

		ll = arrayToList(two);
		ll.clear();
		assertEquals(0, ll.getSize());
		assertConsistency(ll);

		ll = arrayToList(three);
		ll.clear();
		assertEquals(0, ll.getSize());
		assertConsistency(ll);
	}

	@Test
	public void testToArray() {
		IntList ll = arrayToList(zero);
		assertArrayEquals(new int[]{}, ll.toArray());
		assertArrayEquals(one, arrayToList(one).toArray());
		assertArrayEquals(two, arrayToList(two).toArray());
		assertArrayEquals(three, arrayToList(three).toArray());
	}
	
	private IntList createIntList() {
		return new CircularLinkedIntList();
	}


	private IntList arrayToList(int[] elems) {
		IntList res = createIntList();

		
		for (int i = 0; i < elems.length; i++) {
			res.addLast(elems[i]);
		}

		return res;
	}

	private void assertConsistency(IntList ll) {
		CircularLinkedIntList list = (CircularLinkedIntList) ll;
		assertEquals(list.last == null, list.isEmpty());
		assertEquals(list.last == null, list.size == 0);
		

		if (!ll.isEmpty()) {
			assertNotEquals(null, list.last);
			
			IntNode n = list.last.next;
			IntNode m = null;
			for (int i = 0; i < list.size; i += 1) {
				assertNotEquals(null, n.next);
				m = n;
				n = n.next;
			}
			
			assertEquals(list.last, m);
		}
	}

}
