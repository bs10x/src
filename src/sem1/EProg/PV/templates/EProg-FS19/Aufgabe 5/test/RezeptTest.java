import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class RezeptTest {

	@Test
	public void testLinearisiereDoener() {
		Rezept rezept = Rezepte.doener();

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		rezept.linearisiere(new PrintStream(stream));
		String output = stream.toString();
		String[] lines = output.split("\r?\n");

		String[] sol1 = {
				"1",
				"Kollegen auftreiben",
				"2",
				"Geld auftreiben",
				"3",
				"Dönerbude aufsuchen",
				"Döner bestellen",
				"\"Mit allem\"",
				"\"Und scharf\"",
				"Bezahlen"};
		String[] sol2 = {
				"1",
				"Geld auftreiben",
				"2",
				"Kollegen auftreiben",
				"3",
				"Dönerbude aufsuchen",
				"Döner bestellen",
				"\"Mit allem\"",
				"\"Und scharf\"",
				"Bezahlen"};
		assertThat(lines, anyOf(is(sol1), is(sol2)));
	}
}
