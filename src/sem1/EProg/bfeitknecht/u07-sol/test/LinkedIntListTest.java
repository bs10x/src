import java.util.Random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LinkedIntListTest {

	// region additional tests for solution
	public LinkedIntList arrayToList(int[] elems) {
		LinkedIntList res = new LinkedIntList();

		if (elems.length == 1) {
			res.first = res.last = new IntNode(elems[0]);
			res.size = 1;
		} else if (elems.length > 1) {
			IntNode newNode = new IntNode(elems[0]);
			res.first = newNode;

			for (int i = 1; i < elems.length; i++) {
				newNode.next = new IntNode(elems[i]);
				newNode = newNode.next;
			}
			res.last = newNode;
			res.size = elems.length;
		}

		return res;
	}

	public void assertConsistency(LinkedIntList ll) {
		assertEquals(ll.first == null, ll.last == null);
		assertEquals(ll.first == null, ll.isEmpty());
		assertEquals(ll.first == null, ll.size == 0);

		if (!ll.isEmpty())
			assertNull(ll.last.next);
		
		int count = 0;
		for (IntNode n = ll.first; n != null; n = n.next) {
			count++;
			if (n.next == null)
				assertSame(n, ll.last);
		}
		
		assertEquals(count, ll.size);
	}
    
    @Test
    public void testRandom() {
        Random rand = new Random(42);
        LinkedIntList ll = new LinkedIntList();
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

        String msg = "There is a bug in your LinkedIntList";
        assertFalse(ll.isEmpty(), msg);
        assertEquals(5, ll.size, msg);
        int[] expect = new int[]{-1759285598, -424345731,
                -1746103561, -1623993329, -1831395423};
        assertArrayEquals(expect, ll.toArray(), msg);
    }
    
	int[] one = { 42 };
	int[] two = { 3, 1 };
	int[] three = { 2, 7, 1 };

	@Test
	public void testGet() {
		assertEquals(42, arrayToList(one).get(0));

		LinkedIntList ll = arrayToList(two);
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
		LinkedIntList ll = arrayToList(one);
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
	public void testIsEmpty2() {
		assertTrue(new LinkedIntList().isEmpty());
		assertFalse(arrayToList(one).isEmpty());
		assertFalse(arrayToList(three).isEmpty());
	}

	@Test
	public void testAddFirst() {
		LinkedIntList ll = new LinkedIntList();
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
		LinkedIntList ll = new LinkedIntList();
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
		LinkedIntList ll = arrayToList(one);
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
		LinkedIntList ll = arrayToList(one);
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
	public void testClear2() {
		LinkedIntList ll = new LinkedIntList();
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
		LinkedIntList ll = new LinkedIntList();
		assertArrayEquals(new int[]{}, ll.toArray());
		assertArrayEquals(one, arrayToList(one).toArray());
		assertArrayEquals(two, arrayToList(two).toArray());
		assertArrayEquals(three, arrayToList(three).toArray());
	}

	// endregion

	// region tests from template
	@Test
	public void testAddRemoveLast() {
		LinkedIntList list = listFromArray(new int[]{3, 4, 5});
		assertSize(list, 3);

		assertEquals(5, list.removeLast());
		assertSize(list, 2);

		list.addLast(6);
		assertSize(list, 3);

		assertEquals(6, list.removeLast());
		assertSize(list, 2);

		assertEquals(4, list.removeLast());
		assertSize(list, 1);
	}

	@Test
	public void testAddRemoveFirst() {
		LinkedIntList list = listFromArray(new int[]{2, 3, 4});
		assertSize(list, 3);

		assertEquals(2, list.removeFirst());
		assertSize(list, 2);

		list.addFirst(1);
		assertSize(list, 3);

		assertEquals(1, list.removeFirst());
		assertSize(list, 2);

		assertEquals(3, list.removeFirst());
		assertSize(list, 1);
	}

	@Test
	public void testClear() {
		LinkedIntList list = listFromArray(new int[]{10, 20, 30});
		assertSize(list, 3);

		list.clear();
		assertSize(list, 0);
	}

	@Test
	public void testIsEmpty() {
		LinkedIntList list = listFromArray(new int[]{30, 20, 10});
		assertFalse(list.isEmpty());

		assertTrue(new LinkedIntList().isEmpty());
	}

	@Test
	public void testGetSet() {
		LinkedIntList list = listFromArray(new int[]{7, 8, 9, 10});
		assertSize(list, 4);

		assertEquals(7, list.get(0));
		assertEquals(8, list.get(1));
		assertEquals(9, list.get(2));
		assertEquals(10, list.get(3));

		list.set(0, 1);
		assertEquals(1, list.get(0));
		assertEquals(8, list.get(1));
		assertEquals(9, list.get(2));
		assertEquals(10, list.get(3));

		list.set(3, 0);
		assertEquals(1, list.get(0));
		assertEquals(8, list.get(1));
		assertEquals(9, list.get(2));
		assertEquals(0, list.get(3));
	}

	private static void assertSize(LinkedIntList list, int expectedSize) {
		assertEquals(expectedSize, list.getSize());

		if (expectedSize == 0) {
			assertTrue(list.isEmpty());
		} else if (expectedSize > 0){
			assertFalse(list.isEmpty());
		}
	}

	private static LinkedIntList listFromArray(int[] elements) {
		LinkedIntList list = new LinkedIntList();

		for (int elem : elements) {
			list.addLast(elem);
		}

		return list;
	}
	// endregion
}
