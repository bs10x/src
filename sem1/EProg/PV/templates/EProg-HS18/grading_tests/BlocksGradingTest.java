import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BlocksGradingTest {

    @Test
    public void testExample() {
        String input = "7\n" +
                "1 2 3 1 2 2 2\n" +
                "4 5 6 2 2 2 2\n" +
                "8 8 7 6 2 2 2\n" +
                "8 8 9 2 3 0 9\n" +
                "7 8 9 2 4 9 5\n" +
                "4 5 6 2 2 3 2\n" +
                "9 9 9 9 0 3 1";

        int k = Blocks.largestBlock(input);
        assertEquals(3, k);
    }

    @Test
    public void testBlocksOfOne() {
        String input = "6\n" +
                "1 2 3 4 5 6\n" +
                "2 3 4 5 6 7\n" +
                "3 4 5 6 7 8\n" +
                "4 5 6 7 8 9\n" +
                "5 6 7 8 9 0\n" +
                "6 7 8 9 0 1";

        int k = Blocks.largestBlock(input);
        assertEquals(1, k);
    }

    @Test
    public void testColumns() {
        String input = "6\n" +
                "1 2 1 2 1 2\n" +
                "1 2 1 2 1 2\n" +
                "1 2 1 2 1 2\n" +
                "1 2 1 2 1 2\n" +
                "1 2 1 2 1 2\n" +
                "1 2 1 2 1 2";

        int k = Blocks.largestBlock(input);
        assertEquals(1, k);
    }

    @Test
    public void testRows() {
        String input = "6\n" +
                "1 1 1 1 1 1\n" +
                "0 0 0 0 0 0\n" +
                "1 1 1 1 1 1\n" +
                "0 0 0 0 0 0\n" +
                "1 1 1 1 1 1\n" +
                "0 0 0 0 0 0";

        int k = Blocks.largestBlock(input);
        assertEquals(1, k);
    }

    @Test
    public void testDoubleColumns() {
        String input = "6\n" +
                "0 0 1 1 0 0\n" +
                "0 0 1 1 0 0\n" +
                "0 0 1 1 0 0\n" +
                "0 0 1 1 0 0\n" +
                "0 0 1 1 0 0\n" +
                "0 0 1 1 0 0";

        int k = Blocks.largestBlock(input);
        assertEquals(2, k);
    }

    @Test
    public void testDoubleRows() {
        String input = "6\n" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0\n" +
                "1 1 1 1 1 1\n" +
                "1 1 1 1 1 1\n" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0";

        int k = Blocks.largestBlock(input);
        assertEquals(2, k);
    }

    @Test
    public void testCorners() {
        String input = "6\n" +
                "2 2 2 0 0 0\n" +
                "2 2 2 0 2 0\n" +
                "2 2 2 0 2 0\n" +
                "0 0 0 0 2 0\n" +
                "0 2 2 2 2 0\n" +
                "0 0 0 0 0 0";

        int k = Blocks.largestBlock(input);
        assertEquals(3, k);

        input = "6\n" +
                "0 0 0 2 2 2\n" +
                "0 2 0 2 2 2\n" +
                "0 2 0 2 2 2\n" +
                "0 2 0 0 0 0\n" +
                "0 2 2 2 2 0\n" +
                "0 0 0 0 0 0";

        k = Blocks.largestBlock(input);
        assertEquals(3, k);

        input = "6\n" +
                "0 0 0 0 0 0\n" +
                "0 2 2 2 2 2\n" +
                "0 2 0 0 0 0\n" +
                "0 2 0 2 2 2\n" +
                "0 2 0 2 2 2\n" +
                "0 0 0 2 2 2";

        k = Blocks.largestBlock(input);
        assertEquals(3, k);

        input = "6\n" +
                "0 0 0 0 0 0\n" +
                "0 2 2 2 2 0\n" +
                "0 0 0 0 2 0\n" +
                "2 2 2 0 2 0\n" +
                "2 2 2 0 2 0\n" +
                "2 2 2 0 0 0";

        k = Blocks.largestBlock(input);
        assertEquals(3, k);
    }

    @Test
    public void testAllSame() {
        String input = "3\n" +
                "1 1 1\n" +
                "1 1 1\n" +
                "1 1 1";

        int k = Blocks.largestBlock(input);
        assertEquals(3, k);
    }

    @Test
    public void testTwoDigits() {
        String input = "3\n" +
                "11 11 11\n" +
                "11 11 11\n" +
                "11 11 0";

        int k = Blocks.largestBlock(input);
        assertEquals(2, k);
    }

    @Test
    public void testSingleton() {
        String input = "1\n" +
                "1";

        int k = Blocks.largestBlock(input);
        assertEquals(1, k);
    }
}
