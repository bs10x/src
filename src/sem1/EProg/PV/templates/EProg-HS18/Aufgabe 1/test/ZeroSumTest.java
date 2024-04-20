import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class ZeroSumTest {

    @Test
    public void testExample() {
        Pair pair = ZeroSum.zeroSum(new Scanner("1 30 42 -7 100 -1"));
        assertNotNull(pair);
        assertEquals(0, pair.i);
        assertEquals(5, pair.j);
    }
}
