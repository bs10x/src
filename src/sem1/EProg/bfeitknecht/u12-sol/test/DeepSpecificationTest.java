

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;


public class DeepSpecificationTest {

	/**
	 * Test if 'prog' sets the variable 'x' to 'expectedOrNull'. If
	 * 'expectedOrNull' is null, we check that the interpreter and the compiler
	 * throw an exception (detect an error in the program). Furthermore, the
	 * interpreter is compared against the compiler, that is, the variable
	 * mappings produced by each of them must be equal after executing 'prog'.
	 */
	private void expect(Double expectedOrNull, String prog) {
		Map<String, Double> interpVars = null;
		HashMap<String, Double> compVars = null;
		
		// run interpreter
		try {
			HashMap<String, Double> vars = new HashMap<String, Double>();
			vars.put("x", 1.0);

			Interpreter interp = new Interpreter(vars);

			interp.interpret(prog);
			interpVars = interp.getVars();

			double result = interpVars.get("x");
			if (expectedOrNull == null)
				fail("Expected exception, but interp(" + prog + ") yielded " + result);
			assertEquals(expectedOrNull, result, 0.01);
		} catch (ProgramException e) {
			interpVars = null;
			if (expectedOrNull != null)
				fail("Expected interp(" + prog + ") = " + expectedOrNull + ", but got Exception: " + e.getMessage());
		}

		// run compiler
		try {
			compVars = new HashMap<String, Double>();
			compVars.put("x", 1.0);

			Compiler comp = new Compiler();
			Program program = comp.compile(prog);

			program.execute(compVars);

			double result = compVars.get("x");
			if (expectedOrNull == null)
				fail("Expected exception, but comp(" + prog + ") yielded " + result);
			assertEquals(expectedOrNull, result, 0.01);
		} catch (ProgramException | ExecutionException e) {
			compVars = null;
			if (expectedOrNull != null)
				fail("Expected comp(" + prog + ") = " + expectedOrNull + ", but got Exception: " + e.getMessage());
		}

		// compare produced variable mappings
		assertEquals(compVars, interpVars);
	}

	@Test
	public void testEvalSimple() {
		expect(1.0, "x = x;");
		expect(null, "x x");
		expect(1.0, "x = 1;");
		expect(1.0, "x = 1.0;");
		expect(2.0, "x = 1+1;");
		expect(2.0, "x = (1+1);");
		expect(null, "x = 1+1+1;");
		expect(3.0, "x = 1+(1+1);");
		expect(3.0, "x = (1+1)+1;");
		expect(6.0, "x = (1.0+1.0)+(1.0+(1.0+(1.0+x)));");
	}

	@Test
	public void testEvalOps() {
		expect(1.0 + 1.0, "x = 1+1;");
		expect(2.0 * 2.0, "x = 2*2;");
		expect(3.0 / 2.0, "x = 3/2;");
		expect(Math.pow(3.0, 0.5), "x = 3^0.5;");
	}

	@Test
	public void testEvalFuncs() {
		expect(Math.sin(1.2), "x = sin(1.2);");
		expect(Math.cos(1.2), "x = cos(1.2);");
		expect(Math.tan(1.2), "x = tan(1.2);");
		expect(Math.signum(-1.2), "x = signum(0-1.2);");
		expect(Math.abs(-1.2), "x = abs(0-1.2);");
	}

	@Test
	public void testVars() {
		expect(2.0, "y = 1; x = y + y;");
		expect(2.0, "y = 1; y = 2; x = y;");
	}

	@Test
	public void testInvalidExprs() {
		expect(null, "x = 1b;");
		expect(null, "x = 2x+x;");
		expect(null, "x = b;"); // unknown var
		expect(null, "x = -1;"); // no prefix-operators
		expect(null, "x = 1++1;");
		expect(null, "x = 1+1+1;");
		expect(null, "x = foo(1);"); // unknown func
		expect(null, "x = sin();");
		expect(null, "x = x+;");
		expect(null, "x = x%1;");
		expect(null, "x = sin(1;");
		expect(null, "x = 1+();");
	}

}
