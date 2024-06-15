import static org.junit.Assert.*;

import org.junit.Test;

public class NestingTest {

    @Test
    public void testExample() {
        assertTrue(Nesting.isCorrectlyNested("(([]))()"));
        assertFalse(Nesting.isCorrectlyNested("([)]"));
        assertFalse(Nesting.isCorrectlyNested("["));
    }
}
