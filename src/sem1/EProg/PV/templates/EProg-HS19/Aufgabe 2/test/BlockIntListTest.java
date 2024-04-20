import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BlockIntListTest {

	@Test
	public void testAddSize() {
		BlockIntList list = new BlockIntList();
		assertEquals(0, list.size());

		list.add(0);
		assertEquals(1, list.size());
		list.add(0);
		assertEquals(2, list.size());
		list.add(0);
		assertEquals(3, list.size());

		for (int i = 1; i <= 27; i++) {
			list.add(0);
		}
		assertEquals(30, list.size());
	}

	@Test
	public void testAddToString() {
		BlockIntList list = new BlockIntList();
		assertEquals("[]", list.toString());

		list.add(0);
		assertEquals("[0]", list.toString());
		list.add(1);
		assertEquals("[0, 1]", list.toString());
		list.add(2);
		assertEquals("[0, 1, 2]", list.toString());

		for (int i = 3; i < 30; i++) {
			list.add(i);
		}
		String expected = range(0, 30)
				.mapToObj(String::valueOf)
				.collect(joining(", ", "[", "]"));
		assertEquals(expected, list.toString());
	}

	@Test
	public void testAddGet() {
		BlockIntList list = new BlockIntList();

		list.add(0);
		assertEquals(0, list.get(0));
		list.add(1);
		assertEquals(1, list.get(1));
		list.add(2);
		assertEquals(2, list.get(2));

		for (int i = 3; i < 30; i++) {
			list.add(i);
			for (int j = 0; j <= i; j++) {
				assertEquals(j, list.get(j));
			}
		}
	}

	@Test
	public void testAddFirst() {
		BlockIntList list = new BlockIntList();

		list.addFirst(0);
		assertEquals("[0]", list.toString());
		list.addFirst(1);
		assertEquals("[1, 0]", list.toString());
		list.addFirst(2);
		assertEquals("[2, 1, 0]", list.toString());
		list.addFirst(3);
		assertEquals("[3, 2, 1, 0]", list.toString());

		for (int i = 4; i < 30; i++) {
			list.addFirst(i);
		}
		String expected = range(0, 30)
				.map(i -> 29 - i)
				.mapToObj(String::valueOf)
				.collect(joining(", ", "[", "]"));
		assertEquals(expected, list.toString());
	}

	@Test
	public void testAddFirstSize() {
		BlockIntList list = new BlockIntList();

		list.addFirst(0);
		assertEquals(1, list.size());
		list.addFirst(0);
		assertEquals(2, list.size());
		list.addFirst(0);
		assertEquals(3, list.size());

		for (int i = 1; i <= 27; i++) {
			list.addFirst(0);
		}
		assertEquals(30, list.size());
	}
}
