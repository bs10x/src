import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ExprEvaluatorTest {

	private void expect(Double expectedOrNull, String expr) {
		try {
			double result = new ExprEvaluator(1.0).eval(expr);
			if (expectedOrNull == null)
				fail("Expected exception, but eval(" + expr + ") yielded " + result);
			assertEquals(expectedOrNull, result, 0.01);
		} catch (EvaluationException e) {
			if (expectedOrNull != null)
				fail("Expected eval(" + expr + ") = " + expectedOrNull + ", but got Exception: " + e.getMessage());
		}
	}

	@Test
	public void testEvalSimple() {
		expect(1.0, "x");
		expect(null, "x x");
		expect(1.0, "1");
		expect(1.0, "1.0");
		expect(2.0, "1+1");
		expect(2.0, "(1+1)");
		expect(null, "1+1+1");
		expect(3.0, "1+(1+1)");
		expect(3.0, "(1+1)+1");
		expect(6.0, "(1.0+1.0)+(1.0+(1.0+(1.0+x)))");
	}

	@Test
	public void testEvalOps() {
		expect(1.0 + 1.0, "1+1");
		expect(2.0 * 2.0, "2*2");
		expect(3.0 / 2.0, "3/2");
		expect(Math.pow(3.0, 0.5), "3^0.5");
	}

	@Test
	public void testEvalFuncs() {
		expect(Math.sin(1.2), "sin(1.2)");
		expect(Math.cos(1.2), "cos(1.2)");
		expect(Math.tan(1.2), "tan(1.2)");
		expect(Math.signum(-1.2), "signum(0-1.2)");
		expect(Math.abs(-1.2), "abs(0-1.2)");
	}

	@Test
	public void testInvalidExprs() {
		expect(null, "1b");
		expect(null, "2x+x");
		expect(null, "b"); // unknown var
		expect(null, "-1"); // no prefix-operators
		expect(null, "1++1");
		expect(null, "1+1+1");
		expect(null, "foo(1)"); // unknown func
		expect(null, "sin()");
		expect(null, "x+");
		expect(null, "x%1");
		expect(null, "sin(1");
		expect(null, "1+()");
	}

}
