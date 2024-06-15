import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class AtLeastSubsequenceTest {

	@Rule
	public Timeout globalTimeout= Timeout.seconds(5);
	
	@Test
	public void testVomAufgabenBlatt() {
		assertEquals(true, AtLeastSubsequence.subsequence("abab", "ab", 2));
		assertEquals(true, AtLeastSubsequence.subsequence("abab", "ab", 3));
		assertEquals(false, AtLeastSubsequence.subsequence("abab", "ab", 4));
	}
	
	@Test
	public void einfacheTests() {
		assertEquals(true, AtLeastSubsequence.subsequence("abb", "ab", 0));
		assertEquals(true, AtLeastSubsequence.subsequence("abb", "ab", 1));
		assertEquals(true, AtLeastSubsequence.subsequence("abb", "ab", 2));
		assertEquals(false, AtLeastSubsequence.subsequence("abb", "ab", 3));
		
		assertEquals(true, AtLeastSubsequence.subsequence("acbb", "ab", 0));
		assertEquals(true, AtLeastSubsequence.subsequence("acbb", "ab", 1));
		assertEquals(true, AtLeastSubsequence.subsequence("acbb", "ab", 2));
		assertEquals(false, AtLeastSubsequence.subsequence("acbb", "ab", 3));
	}
	
	
	@Test
	public void SingleCharTests() {		
		assertEquals(true, AtLeastSubsequence.subsequence("qqqqq", "qqq", 10));
		assertEquals(false, AtLeastSubsequence.subsequence("qqqqq", "qqq", 11));
		
		assertEquals(true, AtLeastSubsequence.subsequence("qqqqq", "qqqq", 5));
		assertEquals(false, AtLeastSubsequence.subsequence("qqqqq", "qqqq", 6));
		
		assertEquals(true, AtLeastSubsequence.subsequence("qqqqq", "qqqqq", 1));
		assertEquals(false, AtLeastSubsequence.subsequence("qqqqq", "qqqqq", 2));
	}

	@Test
	public void bigTests() {
		assertEquals(true, AtLeastSubsequence.subsequence("aaaaaaaaaabbbbbbbbbb", "ab", 100));
		assertEquals(false, AtLeastSubsequence.subsequence("aaaaaaaaaabbbbbbbbbb", "ab", 101));
	}
}


