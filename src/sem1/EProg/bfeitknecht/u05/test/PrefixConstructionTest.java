import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class PrefixConstructionTest {


	@Test
	public void test1() {
		assertTrue(PrefixConstruction.isPrefixConstruction("aaa", "a", 4));
		assertFalse(PrefixConstruction.isPrefixConstruction("aaa", "a", 2));
		assertTrue(PrefixConstruction.isPrefixConstruction("aaaaaaa", "aaa", 3));
		
		assertTrue(PrefixConstruction.isPrefixConstruction("abcababc", "abc", 4));
		assertFalse(PrefixConstruction.isPrefixConstruction("abcbcabc", "abc", 4));
		assertTrue(PrefixConstruction.isPrefixConstruction("abab", "abac", 2));
	}
	
}