import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;

public class KombinationenGradingTest {

	@Test
	public void testGivenExample() {
		String result = Kombinationen.generate("123");
		assertNotNull(result);
		String[] split = result.split(" ");
		Arrays.sort(split);
		assertEquals("[\"\", \"1\", \"12\", \"123\", \"13\", \"2\", \"23\", \"3\"]", Arrays.toString(split));
	}

	@Test
	public void testEmptyString() {
		assertFuzzyEquals("\"\"", Kombinationen.generate(""), false, false);
	}

	@Test
	public void testOne() {
		assertFuzzyEquals("\"\" \"a\"", Kombinationen.generate("a"), false, false);
	}

	@Test
	public void testTwo() {
		assertFuzzyEquals("\"\" \"a\" \"ab\" \"b\"", Kombinationen.generate("ab"), false, false);
	}

	@Test
	public void testAll() {
		assertFuzzyEquals("\"\"", Kombinationen.generate(""), false, false);
		assertFuzzyEquals("\"\" \"a\"", Kombinationen.generate("a"), false, false);
		assertFuzzyEquals("\"\" \"a\" \"ab\" \"b\"", Kombinationen.generate("ab"), false, false);
		assertFuzzyEquals("\"\" \"a\" \"ab\" \"abc\" \"ac\" \"b\" \"bc\" \"c\"", Kombinationen.generate("abc"), false, false);
	}

	@Test
	public void testAllFuzzy() {
		assertFuzzyEquals("\"\"", Kombinationen.generate(""), true, false);
		assertFuzzyEquals("\"\" \"a\"", Kombinationen.generate("a"), true, false);
		assertFuzzyEquals("\"\" \"a\" \"ab\" \"b\"", Kombinationen.generate("ab"), true, false);
		assertFuzzyEquals("\"\" \"a\" \"ab\" \"abc\" \"ac\" \"b\" \"bc\" \"c\"", Kombinationen.generate("abc"), true, false);
	}

	@Test
	public void testAllFuzzier() {
		assertFuzzyEquals("\"\"", Kombinationen.generate(""), true, true);
		assertFuzzyEquals("\"\" \"a\"", Kombinationen.generate("a"), true, true);
		assertFuzzyEquals("\"\" \"a\" \"ab\" \"b\"", Kombinationen.generate("ab"), true, true);
		assertFuzzyEquals("\"\" \"a\" \"ab\" \"abc\" \"ac\" \"b\" \"bc\" \"c\"", Kombinationen.generate("abc"), true, true);
	}

	private String sortString(String s) {
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}

	/**
	 * use all non-characters to split the list; also ignoring if there is an empty element or not
	 */
	private void assertFuzzyEquals(String expected, String actual, 
			boolean fuzzy, boolean ignoreInWordOrder) {
		assertNotNull(actual);
		
		if (!fuzzy) {
			String[] reference = expected.split(" ");
			String[] result = actual.split(" ");
			Arrays.sort(reference);
			Arrays.sort(result);
			assertArrayEquals(reference, result);
			return;
		}
		
		Set<String> reference = Arrays.stream(expected.split("[^\\w]+"))
				.filter(s -> !s.isEmpty())
				.map(s -> ignoreInWordOrder ? sortString(s) : s)
				.collect(Collectors.toSet());
		
		Set<String> result = Arrays.stream(actual.split("[^\\w]+"))
				.filter(s -> !s.isEmpty())
				.map(s -> ignoreInWordOrder ? sortString(s) : s)
				.collect(Collectors.toSet());
		
		assertEquals(reference, result);
	}
}
