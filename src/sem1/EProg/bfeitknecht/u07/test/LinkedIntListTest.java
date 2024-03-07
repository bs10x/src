import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LinkedIntListTest {

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
        } else if (expectedSize > 0) {
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
}
