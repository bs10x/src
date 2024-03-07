import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TokenizerTest {
    private String[] tokenize(String expr) {
        Tokenizer t = new Tokenizer(expr);
        ArrayList<String> res = new ArrayList<String>();
        while(t.hasNext()) {
            res.add(t.next());
        }
        return res.toArray(new String[0]);
    }
    
    @Test
    public void testSomeExprs() {
        assertArrayEquals(new String[]{}, tokenize(""));
        assertArrayEquals(new String[]{"1"}, tokenize("1"));
        assertArrayEquals(new String[]{"1", "+", "2"}, tokenize("1+2"));
        assertArrayEquals(new String[]{"sin(", "x", ")"}, tokenize("sin(x)"));
        assertArrayEquals(new String[]{"(", "(", ")", ")"}, tokenize("(())"));
        assertArrayEquals(new String[]{"1.12", "+", "va1r"}, tokenize("1.12+va1r"));
        assertArrayEquals(new String[]{"+", "*", "/", "-", "^"}, tokenize("+*/-^"));
        assertArrayEquals(new String[]{"+", "*", "/", "-", "^"}, tokenize("+*/-^"));
    }
    
    @Test
    public void testNextNum() {
        assertTrue(new Tokenizer("123").hasNextNum());
        assertTrue(new Tokenizer("123.0").hasNextNum());
        assertEquals(123.0, new Tokenizer("123").nextNum(), 0.0);
        assertFalse(new Tokenizer("123b").hasNextNum());
        assertFalse(new Tokenizer("123.").hasNextNum());
    }
    
    @Test
    public void testNextVar() {
        assertTrue(new Tokenizer("hi").hasNextVar());
        assertEquals("hi", new Tokenizer("hi").nextVar());
        assertFalse(new Tokenizer("hi123").hasNextVar());
    }
    
    @Test
    public void testNextFunc() {
        assertTrue(new Tokenizer("name(").hasNextFunc());
        assertTrue(new Tokenizer("name(1").hasNextFunc());
        assertEquals("name(", new Tokenizer("name(").nextFunc());
        assertFalse(new Tokenizer("name (").hasNextFunc());
        assertFalse(new Tokenizer("n)").hasNextFunc());
    }
    
    @Test
    public void testNextOp() {
        for(String op : new String[]{"+", "-", "*", "/", "^"}) {
            assertTrue(new Tokenizer(op).hasNextOp());
            assertEquals(op, new Tokenizer(op).nextOp());
        }
        assertFalse(new Tokenizer("1").hasNextOp());
        assertFalse(new Tokenizer("n").hasNextOp());
    }
    
    @Test
    public void testNextOpenParenthesis() {
        assertTrue(new Tokenizer("(").hasNextOpen());
        new Tokenizer("(").nextOpen();
    }
    
    @Test
    public void testNextCloseParenthesis() {
        assertTrue(new Tokenizer(")").hasNextClose());
        new Tokenizer(")").nextClose();
    }

}
