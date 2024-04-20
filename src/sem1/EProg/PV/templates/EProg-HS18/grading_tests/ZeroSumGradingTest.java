import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class ZeroSumGradingTest {

    @Test
    public void testExample() {
        Pair pair = ZeroSum.zeroSum(new Scanner("1 30 42 -7 100 -1"));
        assertNotNull(pair);
        assertTrue(pair.i == 0 && pair.j == 5 || pair.i == 5 && pair.j == 0);
    }

    @Test
    public void testExampleOrdered() {
        Pair pair = ZeroSum.zeroSum(new Scanner("1 30 42 -7 100 -1"));
        assertNotNull(pair);
        assertEquals(0, pair.i);
        assertEquals(5, pair.j);
    }

    @Test
    public void testNextToEachOther() {
        Pair pair = ZeroSum.zeroSum(new Scanner("2 3 4 -4 5 6"));
        assertNotNull(pair);
        assertTrue(pair.i == 2 && pair.j == 3 || pair.i == 3 && pair.j == 2);
    }

    @Test
    public void testNextToEachOtherOrdered() {
        Pair pair = ZeroSum.zeroSum(new Scanner("2 3 4 -4 5 6"));
        assertNotNull(pair);
        assertEquals(2, pair.i);
        assertEquals(3, pair.j);
    }

    @Test
    public void testMultiple() {
        Pair pair = ZeroSum.zeroSum(new Scanner("2 3 -3 4 5 -3 3"));
        assertNotNull(pair);
        assertTrue(
                pair.i == 1 && pair.j == 2 ||
                pair.i == 2 && pair.j == 1 ||
                pair.i == 1 && pair.j == 5 ||
                pair.i == 5 && pair.j == 1 ||
                pair.i == 2 && pair.j == 6 ||
                pair.i == 6 && pair.j == 2 ||
                pair.i == 5 && pair.j == 6 ||
                pair.i == 6 && pair.j == 5);
    }

    @Test
    public void testMultipleOrdered() {
        Pair pair = ZeroSum.zeroSum(new Scanner("2 3 -3 4 5 -3 3"));
        assertNotNull(pair);
        assertTrue(
                pair.i == 1 && pair.j == 2 ||
                pair.i == 1 && pair.j == 5 ||
                pair.i == 2 && pair.j == 6 ||
                pair.i == 5 && pair.j == 6);
    }

    @Test
    public void testNoPair() {
        Pair pair = ZeroSum.zeroSum(new Scanner("1 2 3 4 5 -6 -7 -8 -9"));
        assertNull(pair);

        // check that the method does not just always return null
        assertNotNull(ZeroSum.zeroSum(new Scanner("1 30 42 -7 100 -1")));
    }

    @Test
    public void testZero() {
        Pair pair = ZeroSum.zeroSum(new Scanner("1 5 0 9"));
        assertNull(pair);

        assertNotNull(ZeroSum.zeroSum(new Scanner("1 30 42 -7 100 -1")));
    }

    @Test
    public void testSingle() {
        Pair pair = ZeroSum.zeroSum(new Scanner("5"));
        assertNull(pair);

        assertNotNull(ZeroSum.zeroSum(new Scanner("1 30 42 -7 100 -1")));
    }

    @Test
    public void testEmpty() {
        Pair pair = ZeroSum.zeroSum(new Scanner(""));
        assertNull(pair);

        assertNotNull(ZeroSum.zeroSum(new Scanner("1 30 42 -7 100 -1")));
    }
}
