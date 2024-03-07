import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KlassenTest {
	
	@Test
	public void testOutput() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream output = new PrintStream(baos);
		
		Klassen.klassen(output);
		String actual = baos.toString();
		
		output.close();
		
		String expected = String.join(System.lineSeparator()
				, "Hello"
				, "Bingo"
				, "++"
				, "Hello"
				, "++"
				, "C.foo():"
				, "Here"
				, "--"
				, "Found"
				) + System.lineSeparator();
		
		assertEquals(expected, actual);

	}
	
}
