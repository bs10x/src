import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;

public class BienenTest {

	@Test
	public void testOutput() throws IOException {
		String dateiName = "bienen.txt";
		Scanner input = new Scanner(new File(dateiName));
		
		File testDatei = new File("bienen-output.txt");
		PrintStream output = new PrintStream(testDatei);
		
		Bienen.analyze(input, output);
		
		input.close();
		output.close();
		
		Scanner scanner = new Scanner(testDatei);
		
		assertTrue(scanner.hasNextLine());
		assertEquals("Schmid 2810", scanner.nextLine());
		
		assertTrue(scanner.hasNextLine());
		assertEquals("Meier 81", scanner.nextLine());
		
		assertTrue(scanner.hasNextLine());
		assertEquals("uk 1405", scanner.nextLine());
		
		assertFalse(scanner.hasNextLine());
	}
}
