import static org.junit.Assert.*;

import org.junit.Test;

public class NestingGradingTest {

    @Test
    public void testExample() {
        assertTrue(Nesting.isCorrectlyNested("(([]))()"));
        assertFalse(Nesting.isCorrectlyNested("([)]"));
        assertFalse(Nesting.isCorrectlyNested("["));
    }

    @Test
    public void testEmpty() {
        assertTrue(Nesting.isCorrectlyNested(""));
    }

    @Test
    public void testUnitParen() {
        assertTrue(Nesting.isCorrectlyNested("()"));
        assertFalse(Nesting.isCorrectlyNested(")("));
    }

    @Test
    public void testUnitBrackets() {
        assertTrue(Nesting.isCorrectlyNested("[]"));
        assertFalse(Nesting.isCorrectlyNested("]["));
    }

    @Test
    public void testUnitAngular() {
        assertTrue(Nesting.isCorrectlyNested("<>"));
        assertFalse(Nesting.isCorrectlyNested("><"));
    }

    @Test
    public void testNestingParen() {
        assertTrue(Nesting.isCorrectlyNested("(((())))"));
        assertFalse(Nesting.isCorrectlyNested("((())"));
        assertFalse(Nesting.isCorrectlyNested("(()))"));
        assertFalse(Nesting.isCorrectlyNested(")("));
    }

    @Test
    public void testNestingBrackets() {
        assertTrue(Nesting.isCorrectlyNested("[[[[]]]]"));
        assertFalse(Nesting.isCorrectlyNested("[[[]]"));
        assertFalse(Nesting.isCorrectlyNested("[[]]]"));
        assertFalse(Nesting.isCorrectlyNested("]["));
    }

    @Test
    public void testNestingAngular() {
        assertTrue(Nesting.isCorrectlyNested("<<<<>>>>"));
        assertFalse(Nesting.isCorrectlyNested("<<<>>"));
        assertFalse(Nesting.isCorrectlyNested("<<>>>"));
        assertFalse(Nesting.isCorrectlyNested("><"));
    }

    @Test
    public void testSequenceParen() {
        assertTrue(Nesting.isCorrectlyNested("(()())(())()"));
        assertFalse(Nesting.isCorrectlyNested("()()("));
        assertFalse(Nesting.isCorrectlyNested("()())"));
        assertFalse(Nesting.isCorrectlyNested(")("));
    }

    @Test
    public void testSequenceBrackets() {
        assertTrue(Nesting.isCorrectlyNested("[[][]][[]][]"));
        assertFalse(Nesting.isCorrectlyNested("[][]["));
        assertFalse(Nesting.isCorrectlyNested("[][]]"));
        assertFalse(Nesting.isCorrectlyNested("]["));
    }

    @Test
    public void testSequenceAngular() {
        assertTrue(Nesting.isCorrectlyNested("<<><>><<>><>"));
        assertFalse(Nesting.isCorrectlyNested("<><><"));
        assertFalse(Nesting.isCorrectlyNested("<><>>"));
        assertFalse(Nesting.isCorrectlyNested("><"));
    }

    @Test
    public void testMixed() {
        assertTrue(Nesting.isCorrectlyNested("(())[[]]<<>>"));
        assertTrue(Nesting.isCorrectlyNested("([<>])"));
        assertTrue(Nesting.isCorrectlyNested("([<>][<()>])"));
        assertFalse(Nesting.isCorrectlyNested("[(])"));
        assertFalse(Nesting.isCorrectlyNested("[(<]>)"));
        assertFalse(Nesting.isCorrectlyNested("<([])]"));
        assertFalse(Nesting.isCorrectlyNested("(]"));
        assertFalse(Nesting.isCorrectlyNested("<]"));
        assertFalse(Nesting.isCorrectlyNested("[)"));
    }

}
