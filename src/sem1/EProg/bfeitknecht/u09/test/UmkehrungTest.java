import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

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
		
		IntNode current = list.first;
		for (int i = 0; i < list.size; i++) {
			System.out.println(current.toString());
			current = current.next;
			
		}

        assertSame(list.last, oldFirst);
        assertSame(list.first, oldLast);
        assertSame(list.first.next.next, oldSecond);
	}
}
