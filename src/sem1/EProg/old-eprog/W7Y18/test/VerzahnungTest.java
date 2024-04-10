import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Test;

public class VerzahnungTest {

	@Test
	public void testOutputFormat() throws FileNotFoundException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (PrintStream output = new PrintStream(baos)) {
			String s = "12";
			String t = "ab";
			Verzahnung.verzahnungen(s, t, output);
		}

		String actual = baos.toString();

		assertTrue(actual.contains("12ab\n"));
		assertTrue(actual.contains("1a2b\n"));
		assertTrue(actual.contains("1ab2\n"));
		assertTrue(actual.contains("a12b\n"));
		assertTrue(actual.contains("a1b2\n"));
		assertTrue(actual.contains("ab12\n"));
	}
}
