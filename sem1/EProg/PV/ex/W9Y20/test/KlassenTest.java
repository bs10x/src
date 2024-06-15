import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

public class KlassenTest {
	
	@Test
	public void testOutput() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream output = new PrintStream(baos);
		
		Klassen.klassen(output);
		String actual = baos.toString();
		
		output.close();
		
		String expected = String.join(System.lineSeparator()
				, "Bern"
				, "1 0"
				, "1 1"
				, "Unrelated"
				, "2 1"
				) + System.lineSeparator();
		
		assertEquals(expected, actual);		
	}
}
