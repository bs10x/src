import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

class TestCase {
	
	String expr;
	int eval;
	
	TestCase(String expr, int eval) {
		this.expr = expr;
		this.eval = eval;
	}
}

public class RechnerTest {
	
	TestCase[] exampleInputs = {
			new TestCase("+ 7 8", 15),
			new TestCase("+ * * + + 1 2 3 4 5 6", 126),
			new TestCase("+ - + - 4 3 + 5 - 9", -3)
	};
	
	@Test
	public void testParseToStringIdentity() {
		
		for (TestCase testCase : exampleInputs) {
			String once = Rechner.parse(testCase.expr).toString();
			String twice = Rechner.parse(once).toString();
			
			assertEquals(once, twice);
		}
	}
	
	@Test
	public void testEval() {
		
		for (TestCase testCase : exampleInputs) {
			int actual = Rechner.parse(testCase.expr).eval();
			int expected = testCase.eval;
			assertEquals(actual, expected);
		}
	}
	
	@Test
	public void testStructure() {
		Expr e1 = Rechner.parse("+ * 1 - 34 5");
		Expr l = ((BinOp) e1).left();
		Expr lr = ((BinOp) l).right();
		Constant lre = (Constant) ((UnaryOp) lr).operand();
		
		assertEquals(lre.val(), 34);
		assertTrue(e1.children().length == 2);
		assertTrue(l == e1.children()[0] || l == e1.children()[1]);
		assertTrue(lr.children().length == 1);
		assertTrue(lr != e1.children()[0] && lr != e1.children()[1]);
	}

}
