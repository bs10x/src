import java.util.Random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UmkehrungTest {

	@Test
	public void testReverseSimple() {
		LinkedIntList list = new LinkedIntList();
		list.addLast(0);
		list.addLast(0);
		list.addLast(0);
		list.addLast(0);

		IntNode oldFirst = list.first;
		IntNode oldLast = list.last;
		IntNode oldSecond = list.first.next;

		list.reverse();

        assertSame(list.last, oldFirst);
        assertSame(list.first, oldLast);
        assertSame(list.first.next.next, oldSecond);
	}

	@Test
	public void testNoCrash() {
		new LinkedIntList().reverse();
		arrayToList(new int[] { 42 }).reverse();
		arrayToList(new int[] { 23 }).reverse();
		// and not a no-op implementation of reverse:
		reverseAndCheck(arrayToList(new int[] { 42, 23 }), true);
	}

	@Test
	public void testTwoElem() {
		reverseAndCheck(arrayToList(new int[] { 42, 23 }), false);
	}

	@Test
	public void testTwoElemLenient() {
		reverseAndCheck(arrayToList(new int[] { 42, 23 }), true);
	}

	@Test
	public void testThreeElem() {
		reverseAndCheck(arrayToList(new int[] { 42, 23, 7 }), false);
	}

	@Test
	public void testThreeElemLenient() {
		reverseAndCheck(arrayToList(new int[] { 42, 23, 7 }), true);
	}

	@Test
	public void testStress() {
		Random rnd = new Random(1337);
		for (int size = 0; size < 23; size++) {
			int[] elems = new int[size];
			for (int i = 0; i < size; i++) {
				elems[i] = rnd.nextInt();
			}
			reverseAndCheck(arrayToList(elems), false);
		}
	}

	@Test
	public void testStressLenient() {
		Random rnd = new Random(1337);
		for (int size = 0; size < 23; size++) {
			int[] elems = new int[size];
			for (int i = 0; i < size; i++) {
				elems[i] = rnd.nextInt();
			}
			reverseAndCheck(arrayToList(elems), true);
		}
	}

	private void reverseAndCheck(LinkedIntList ll, boolean beLenient) {
		int oldSize = ll.size;
		// store int values and node instances in array
		int[] oldValues = new int[oldSize];
		IntNode[] oldNodes = new IntNode[oldSize];
		int i = 0;
		for (IntNode n = ll.first; n != null; n = n.next, i++) {
			oldNodes[i] = n;
			oldValues[i] = n.value;
		}

		ll.reverse();

		// check values are now reversed
		i = 0;
		for (IntNode n = ll.first; n != null; n = n.next, i++) {
			if (beLenient && i >= oldSize) {
				// ignore that we found more elements than should be there.
				break;
			}
			assertTrue(i < oldSize, "more elements after reversal");
			assertEquals(oldValues[oldSize - i - 1], n.value, "value not reversed");
		}

		if (!beLenient) {
			assertEquals(oldSize, ll.size);
			assertConsistency(ll);

			// check instances reversed
			i = 0;
			for (IntNode n = ll.first; n != null; n = n.next, i++) {
				assertTrue(i < oldSize);
				assertSame(oldNodes[oldSize - i - 1], n);
			}
		}
	}

	private LinkedIntList arrayToList(int[] elems) {
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

	private void assertConsistency(LinkedIntList ll) {
		assertEquals(ll.first == null, ll.last == null);
		assertEquals(ll.first == null, ll.size == 0);

		if (ll.size != 0)
            assertNull(ll.last.next);

		int count = 0;
		for (IntNode n = ll.first; n != null; n = n.next) {
			count++;
			if (n.next == null)
                assertSame(n, ll.last);
		}

		assertEquals(count, ll.size);
	}

}
