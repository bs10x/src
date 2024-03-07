import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class MatcherTests {
	@Test
	public void testSuffix01() {
		Node start = new Node();
		
		Matcher m = new Matcher(start, Set.of(
			start.addWord("mnop"),
			start.addWord("mnqp"),
			start.addWord("mnsp")
		));
		
		Set<String> op_suffix = m.suffix("op");
		assertEquals(Set.of("mn"), op_suffix);
		
		Set<String> p_suffix = m.suffix("p");
		assertEquals(Set.of("mno", "mnq", "mns"), p_suffix);
	}
	
	@Test
	public void testSuffix02() {
		Node start = new Node();
		
		Matcher m = new Matcher(start, Set.of(
			start.addWord("mnop"),
			start.addWord("mnqp"),
			start.addWord("mnsp")
		));
		
		int rop_suffix_size = m.suffix("rop").size();
		assertEquals(0, rop_suffix_size);
	}
	
	@Test
	public void testSkipmatch01() {
		Node start = new Node();
		
		Matcher m = new Matcher(start, Set.of(
			start.addWord("mnop"),
			start.addWord("mnqr"),
			start.addWord("mnqp")
		));
		
		String skipmatch_mnp = m.skipmatch("mnp", 3);
		assertTrue(Set.of("mnop", "mnqp").contains(skipmatch_mnp));
		
		assertNull(m.skipmatch("mnp", 4));
		
		String skipmatch_mno = m.skipmatch("mno", 4);
		assertTrue(Set.of("mnop").contains(skipmatch_mno));
	}

	@Test
	public void testSkipmatch02() {
		Node start = new Node();
		
		Matcher m = new Matcher(start, Set.of(
			start.addWord("mnop"),
			start.addWord("mnqr"),
			start.addWord("mnqp")
		));
		
		assertNull(m.skipmatch("mg", 2));
		
		String skipmatch_mo = m.skipmatch("mo", 2);
		assertTrue(Set.of("mnop").contains(skipmatch_mo));
	}
}



