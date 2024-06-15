import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BlocksTest {

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
}
