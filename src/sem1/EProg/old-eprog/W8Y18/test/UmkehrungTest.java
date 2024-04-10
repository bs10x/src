import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UmkehrungTest {

	@Test
	public void testReverseSimple() {
		LinkedIntList list = new LinkedIntList();
		list.addLast(0);
		list.addLast(0);
		list.addLast(0);
		list.addLast(0);
		
		IntNode oldFirst = list.first;
		IntNode oldLast  = list.last;
		IntNode oldSecond = list.first.next;
		
		list.reverse();
		
		assertTrue(list.last == oldFirst);
		assertTrue(list.first == oldLast);
		assertTrue(list.first.next.next == oldSecond);
	}
}
