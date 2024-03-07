import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class GradingMatcherTest {

	// Aufgabenteil (a)
	
	@Test
	public void testASuffixGivenBasic01() {
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
	public void testASuffixGivenBasic02() {
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
	public void testASuffixLen1Basic() {
		Node start = new Node();
		
		Matcher m = new Matcher(start, Set.of(
			start.addWord("abcd")
		));
		
		int rop_suffix_size = m.suffix("d").size();
		// wir testen nur die Anzahl
		assertEquals(1, rop_suffix_size);
	}
	
	@Test
	public void testASuffixLen2Basic() {
		Node start = new Node();
		
		Matcher m = new Matcher(start, Set.of(
			start.addWord("asdf")
		));
		
		Set<String> suffixes = m.suffix("df");
		// wir testen den Inhalt des Prefix zum Suffix op
		assertTrue(suffixes.contains("as"));
	}
	
	@Test
	public void testASuffixOverlap() {
		Node start = new Node();
		
		Matcher m = new Matcher(start, Set.of(
			start.addWord("abcdefi"),
			start.addWord("abcghifi")
		));
		
		Set<String> suffixes = m.suffix("fi");
		// wir testen den Inhalt des Prefix zum Suffix op
		assertTrue(suffixes.contains("abcde"));
		assertTrue(suffixes.contains("abcghi"));
	}
	
	@Test
	public void testAPrefixMixedLenBasic() {
		Node start = new Node();
		
		Matcher m = new Matcher(start, Set.of(
			start.addWord("mnop"),
			start.addWord("mnfafasdkjfdqp")
		));
		
		Set<String> words= m.suffix("p");
		assertTrue(words.contains("mnfafasdkjfdq"));
	}
	
	@Test
	public void testABiggerGraphBasic() {
		Node start = new Node();
		
		Matcher m = new Matcher(start, Set.of(
			start.addWord("helloworld"),
			start.addWord("hellothere"),
			start.addWord("hellooutthere"),
			start.addWord("helloyou"),
			start.addWord("hihowareyou"),
			start.addWord("abcdef")
		));
		
		Set<String> words= m.suffix("e");
		assertTrue(words.contains("hellother"));
		assertTrue(words.contains("hellooutther"));
	}
	
	@Test
	public void testATrueDAG() {
		/**
		 * Testet Matching mit einem DAG:
		 * 
		 * hi -------- \
		 * hello --------- howareyou -> (final state)
		 * greetings - /
		 * 
		 */
		
		Node start = new Node();
		
		Node hi = start.addWord("hi");
		Node hello = start.addWord("hello");
		Node greetings = start.addWord("greetings");

		Node final_state = hi.addWord("howareyou");
		Node first_letter_of_tail = hi.transitions.get('h');
		
		hello.transitions.put('h', first_letter_of_tail);
		greetings.transitions.put('h', first_letter_of_tail);
		
		Matcher m = new Matcher(start, Set.of(	
			final_state
		));
		
		
		Set<String> words = m.suffix("howareyou");
		assertEquals(3, words.size());
	}
	
	@Test
	public void testATrueDAGWords() {
		/**
		 * Testet Matching mit einem DAG:
		 * 
		 * hi -------- \
		 * hello --------- howareyou -> (final state)
		 * greetings - /
		 * 
		 */
		
		Node start = new Node();
		
		Node hi = start.addWord("hi");
		Node hello = start.addWord("hello");
		Node greetings = start.addWord("greetings");

		Node final_state = hi.addWord("howareyou");
		Node first_letter_of_tail = hi.transitions.get('h');
		
		hello.transitions.put('h', first_letter_of_tail);
		greetings.transitions.put('h', first_letter_of_tail);
		
		Matcher m = new Matcher(start, Set.of(	
			final_state
		));
		
		Set<String> words = m.suffix("howareyou");
		assertTrue(words.contains("hi"));
		assertTrue(words.contains("hello"));
		assertTrue(words.contains("greetings"));
	}
	
	@Test
	public void testAFullAlphabetMatchBasic() {
		final String alphabet = "abcdefghijklmnopqrstuvwxyz";
		
		Node start = new Node();
		Set<Node> finals = new HashSet<Node>();
		for (char c : alphabet.toCharArray()) {
			finals.add(start.addWord(c + "a"));
		}
		
		Matcher m = new Matcher(start, finals);
		
		Set<String> words = m.suffix("a");
		assertEquals(26, words.size());
	}
	
	@Test
	public void testAFullAlphabetNoResult() {
		final String alphabet = "abcdefghijklmnopqrstuvwxyz";
		
		Node start = new Node();
		Set<Node> finals = new HashSet<Node>();
		for (char c : alphabet.toCharArray()) {
			finals.add(start.addWord(c + "a"));
		}
		
		Matcher m = new Matcher(start, finals);
		
		Set<String> words = m.suffix("b");
		assertEquals(0, words.size());
	}
	
	@Test
	public void testADeadStates() {
		/**
		 * Testet Matching mit einem DAG mit toten/unereichbaren Zuständen.
		 * 
		 * hi -------- \
		 * hello --------- howareyou -> (final state)
		 * greetings -  (unreachable/dead)
		 * 
		 */
		
		Node start = new Node();
		
		Node hi = start.addWord("hi");
		Node hello = start.addWord("hello");
		// Diese Transition führt ins Nichts		
		start.addWord("greetings");

		Node final_state = hi.addWord("howareyou");
		Node first_letter_of_tail = hi.transitions.get('h');
		
		hello.transitions.put('h', first_letter_of_tail);
		
		Matcher m = new Matcher(start, Set.of(	
			final_state
		));
		
		
		Set<String> words = m.suffix("howareyou");
		assertEquals(2, words.size());
	}
	
	@Test
	public void testAEmptyPrefix() {
		Node start = new Node();
		
		Matcher m = new Matcher(start, Set.of(
			start.addWord("abcd")
		));
		
		Set<String> wordSet = m.suffix("abcd");
		String[] words = wordSet.toArray(new String[wordSet.size()]);
		// hier muss "" enthalten sein, da "" + "abcd" von m akzeptiert wird
		assertEquals("", words[0]);
	}
	
	@Test
	public void testAEmptySuffix() {
		Node start = new Node();
		
		Matcher m = new Matcher(start, Set.of(
			start.addWord("abcd")
		));
		
		Set<String> wordSet = m.suffix("");
		String[] words = wordSet.toArray(new String[wordSet.size()]);
		// hier muss "" enthalten sein, da "" + "abcd" von m akzeptiert wird
		assertEquals("abcd", words[0]);
	}
	
	@Test
	public void testAMultiDAGNum() {
		/**
		 * Testet Matching mit einem vielfach verzweigten DAG.
		 * 
		 * hi -------- \
		 * hello --------- howareyou ------- \
		 * 									  ---- end -- (final state)
		 * hola-------- \ 					 /
		 * buenosdias --------- comoteva -- /
		 * 
		 */
		
		Matcher m = createMultiDAG(); 
		
		Set<String> words = m.suffix("end");
		assertEquals(4, words.size());		
	}
	
	@Test
	public void testAMultiDAGReachable() {
		/**
		 * Testet Matching mit einem vielfach verzweigten DAG.
		 * 
		 * hi -------- \
		 * hello --------- howareyou ------ \
		 * 									 ---- end -- (final state)
		 * hola-------- \					 /
		 * buenosdias --------- comoteva -- /
		 * 
		 */
		
		Matcher m = createMultiDAG(); 
		
		Set<String> words = m.suffix("end");
		assertTrue(words.contains("hihowareyou"));
		assertTrue(words.contains("hellohowareyou"));
		assertTrue(words.contains("holacomoteva"));
		assertTrue(words.contains("buenosdiascomoteva"));
	}
	
	// Utility Methoden 
	
	static Matcher createMultiDAG() {
		Node start = new Node();
		
		Node hi = start.addWord("hi");
		Node hello = start.addWord("hello");
		
		Node final_state = hi.addWord("howareyou");
		Node first_letter_of_tail = hi.transitions.get('h');
		
		hello.transitions.put('h', first_letter_of_tail);
		
		// lower part
		Node hola = start.addWord("hola");
		Node buenosdias = start.addWord("buenosdias");
		
		Node final_state2 = hola.addWord("comoteva");
		Node first_letter_of_tail2 = hola.transitions.get('c');
		
		buenosdias.transitions.put('c', first_letter_of_tail2);
		
		// connect both to last segment
		Node end = final_state.addWord("end");
		final_state2.transitions.put('e', final_state.transitions.get('e'));
		
		Matcher m = new Matcher(start, Set.of(end));
		
		return m;
	}
	
	// Aufgabenteil (b)
	
	@Test
	public void testBSkipmatchGiven01() {
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
	public void testBSkipmatchGiven02() {
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
	
	@Test
	public void testBSkipmatchLastHop() {
		Node start = new Node();
		
		Matcher m = new Matcher(start, Set.of(
			start.addWord("abcd")
		));
		
		// mo kann nicht gematched werden
		assertNull(m.skipmatch("mo", 2));
		
		// Letzter Hop ist skipped => dann im finalen Zustand 
		String skipmatch_ac = m.skipmatch("ac", 2);
		assertTrue(Set.of("abcd").contains(skipmatch_ac));
	}
	
	@Test
	public void testBSkipmatch01Basic() {
		Node start = new Node();


		Matcher m = new Matcher(start, Set.of(
			start.addWord("mnopqr")
		));

		assertNull(m.skipmatch("mnpq", 2));

		String skipmatch = m.skipmatch("mnpq", 3);

		assertNotNull(skipmatch);
		assertTrue(Set.of("mnopqr").contains(skipmatch));
		
	}

	@Test
	public void testBSkipmatch02Basic() {
		Node start = new Node();

		/*
			m - n - o - p (skip) - q - r
		*/

		Matcher m = new Matcher(start, Set.of(
				start.addWord("mnopqr")
		));

		// Kein Match für k = 3 
		assertNull(m.skipmatch("mnoqr", 3));

		// Ein Match für k = 4
		String skipmatch = m.skipmatch("mnoqr", 4);

		assertNotNull(skipmatch);
		assertTrue(Set.of("mnopqr").contains(skipmatch));

	}

	@Test
	public void testBSkipmatch03Basic() {
		Node start = new Node();

		Matcher m = new Matcher(start, Set.of(
				start.addWord("mnopqr")
		));

		String skipmatch = m.skipmatch("mnopqr", 7);

		// hier matchen wir ohne Skips, da das Wort kürzer als k = 7 ist
		assertNotNull(skipmatch);
		assertTrue(Set.of("mnopqr").contains(skipmatch));
	}

	@Test
	public void testBSkipmatch04Basic() {
		Node start = new Node();


		Matcher m = new Matcher(start, Set.of(
				// m - n (skip) - p - q (skip)
				start.addWord("mnpq"),
				// m - o (skip) - p - q (skip)
				start.addWord("mopq")
		));

		String skipmatch = m.skipmatch("mp", 2);

		// hier sind beide Optionen möglich
		assertNotNull(skipmatch);
		assertTrue(Set.of("mnpq", "mopq").contains(skipmatch));
	}


	@Test
	public void testBSkipmatch05Basic() {
		Node start = new Node();


		Matcher m = new Matcher(start, Set.of(
				// skip Matches für k = 2
				start.addWord("mnpqt"),
				start.addWord("mnprt"),
				start.addWord("mopqt"),
				start.addWord("moprt"),
				// kein skipmatch
				start.addWord("moprx")
				));

		String skipmatch = m.skipmatch("mpt", 2);

		assertNotNull(skipmatch);

		assertTrue(Set.of("mnpqt", "mnprt", "mopqt", "moprt").contains(skipmatch));

	}

	@Test
	public void testBSkipmatch07() {
		Node start = new Node();

		// Verzweigungen unterschiedlicher Länge
		Matcher m = new Matcher(start, Set.of(
				start.addWord("lmnpqt"),
				start.addWord("lmopq"),
				// kein Match, da kein 'q' existiert  
				start.addWord("lmnprs")
		));

		String skipmatch = m.skipmatch("lmpq", 3);

		assertNotNull(skipmatch);
		assertTrue(Set.of("lmnpqt", "lmopq").contains(skipmatch));
	}

	@Test
	public void testBSkipmatch08() {
		Node start = new Node();

		// Verzweigungen unterschiedlicher Länge
		Matcher m = new Matcher(start, Set.of(
				start.addWord("lmnpqt"),
				start.addWord("lmnprs"),
				start.addWord("lmopqts")
		));

		String skipmatch = m.skipmatch("lmpqs", 3);

		assertNotNull(skipmatch);

		assertTrue(Set.of("lmopqts").contains(skipmatch));

	}

	@Test
	public void testBSkipmatch09Haystack() {
		Node start = new Node();

		//m/n/o tree
		Matcher m = new Matcher(start, Set.of(
				start.addWord("mmmm"),
				start.addWord("mmmn"),
				start.addWord("mmmo"),
				start.addWord("mmnm"),
				start.addWord("mmnn"),
				start.addWord("mmno"),
				start.addWord("mmom"),
				start.addWord("mmon"),
				start.addWord("mmoo"),
				start.addWord("mnmm"),
				start.addWord("mnmn"),
				start.addWord("mnmo"),
				start.addWord("mnnm"),
				start.addWord("mnnn"),
				start.addWord("mnno"),
				start.addWord("mnom"),
				start.addWord("mnon"),
				start.addWord("mnoo"),
				start.addWord("momm"),
				start.addWord("momn"),
				start.addWord("momo"),
				start.addWord("monm"),
				start.addWord("monn"),
				start.addWord("mono"),
				start.addWord("moom"),
				start.addWord("moon"),
				start.addWord("mooo"),
				start.addWord("nmmm"),
				start.addWord("nmmn"),
				start.addWord("nmmo"),
				start.addWord("nmnm"),
				start.addWord("nmnn"),
				start.addWord("nmno"),
				start.addWord("nmom"),
				start.addWord("nmon"),
				start.addWord("nmoo"),
				start.addWord("nnmm"),
				start.addWord("nnmn"),
				start.addWord("nnmo"),
				start.addWord("nnnm"),
				start.addWord("nnnn"),
				start.addWord("nnno"),
				start.addWord("nnom"),
				start.addWord("nnon"),
				start.addWord("nnoo"),
				start.addWord("nomm"),
				start.addWord("nomn"),
				start.addWord("nomo"),
				start.addWord("nonm"),
				start.addWord("nonn"),
				start.addWord("nono"),
				start.addWord("noom"),
				start.addWord("noon"),
				start.addWord("nooo"),
				start.addWord("ommm"),
				start.addWord("ommn"),
				start.addWord("ommo"),
				start.addWord("omnm"),
				start.addWord("omnn"),
				start.addWord("omno"),
				start.addWord("omom"),
				start.addWord("omon"),
				start.addWord("omoo"),
				start.addWord("onmm"),
				start.addWord("onmn"),
				start.addWord("onmo"),
				start.addWord("onnm"),
				start.addWord("onnn"),
				start.addWord("onno"),
				start.addWord("onom"),
				start.addWord("onon"),
				start.addWord("onoo"),
				start.addWord("oomm"),
				start.addWord("oomn"),
				start.addWord("oomo"),
				start.addWord("oonm"),
				start.addWord("oonn"),
				start.addWord("oono"),
				start.addWord("ooom"),
				start.addWord("ooon"),
				start.addWord("oooo")
		));

		assertNull(m.skipmatch("ooom", 4));

		String skipmatch = m.skipmatch("oom", 3);

		assertNotNull(skipmatch);

		assertTrue(Set.of("oomm", "ooom", "oonm").contains(skipmatch));

	}

	@Test
	public void testBSkipmatch10() {
		Node start = new Node();


		Matcher m = new Matcher(start, Set.of(
				start.addWord("abmnqr"),
				start.addWord("abmnqs"),
				start.addWord("abmoqrst"),
				start.addWord("abmoqrsu"),
				// einziger match, da alle anderen Wörter nach dem q nicht enden
				start.addWord("abmpq")
		));

		String skipmatch = m.skipmatch("abmq", 4);

		assertNotNull(skipmatch);
		assertTrue(Set.of("abmpq").contains(skipmatch));
	}

	@Test
	public void testBSkipmatch11() {
		Node start = new Node();


		Matcher m = new Matcher(start, Set.of(
				// matches, da die letzten Buchstaben skips sind
				start.addWord("abmnqr"),
				start.addWord("abmnqs"),
				// keine matches
				start.addWord("abmoqrst"),
				start.addWord("abmoqrsu"),
				// match, da das Wort ohne Skip nach 'q' endet 
				start.addWord("abmpq")
		));

		String skipmatch = m.skipmatch("amq", 2);

		assertNotNull(skipmatch);

		assertTrue(Set.of("abmnqr", "abmnqs", "abmpq").contains(skipmatch));

	}

	@Test
	public void testBSkipmatch12() {
		Node start = new Node();


		Matcher m = new Matcher(start, Set.of(
				start.addWord("abmnqr"),
				start.addWord("abmnqs"),
				start.addWord("abmoqrst"),
				start.addWord("abmoqrsu"),
				start.addWord("abmpq")
		));

		// exact match
		String skipmatch = m.skipmatch("abmpq", 8);
		assertTrue(Set.of("abmpq").contains(skipmatch));
		
		// keine matches
		assertNull(m.skipmatch("abmoqrs", 9));
	}

	@Test
	public void testBSkipmatch13() {
		Node start = new Node();


		Matcher m = new Matcher(start, Set.of(
				start.addWord("zzzzaz"),
				start.addWord("zzzzbz"),
				start.addWord("zzzzcz"),
				start.addWord("zzzzdz")
		));

		String skipmatch = m.skipmatch("zzzzz", 5);

		assertNotNull(skipmatch);
		// Alle Wörter matchen
		assertTrue(Set.of("zzzzaz", "zzzzbz", "zzzzcz", "zzzzdz").contains(skipmatch));

	}

	@Test
	public void testBSkipmatch14() {
		Node start = new Node();

		Matcher m = new Matcher(start, Set.of(
				start.addWord("mnoqrsumnoqrsu"),
				start.addWord("mnoqrsumnoqrtu"),
				start.addWord("mnoqrsumnpqrsu"),
				start.addWord("mnoqrsumnpqrtu"),
				start.addWord("mnoqrtumnoqrsu"),
				start.addWord("mnoqrtumnoqrtu"),
				start.addWord("mnoqrtumnpqrsu"),
				start.addWord("mnoqrtumnpqrtu"),
				start.addWord("mnpqrsumnoqrsu"),
				start.addWord("mnpqrsumnoqrtu"),
				start.addWord("mnpqrsumnpqrsu"),
				start.addWord("mnpqrsumnpqrtu"),
				start.addWord("mnpqrtumnoqrsu"),
				start.addWord("mnpqrtumnoqrtu"),
				start.addWord("mnpqrtumnpqrsu"),
				start.addWord("mnpqrtumnpqrtu")
		));

		String skipmatch = m.skipmatch("mnqrumoqsu", 3);

		assertNotNull(skipmatch);
		
		assertTrue(Set.of("mnoqrtumnoqrsu", "mnpqrtumnoqrsu", "mnoqrsumnoqrsu", "mnpqrsumnoqrsu").contains(skipmatch));

	}

	@Test
	public void testBSkipmatch15() {
		Node start = new Node();

		Matcher m = new Matcher(start, Set.of(
				start.addWord("mnoqrsumnoqrsu"),
				start.addWord("mnoqrsumnoqrtu"),
				start.addWord("mnoqrsumnpqrsu"),
				start.addWord("mnoqrsumnpqrtu"),
				start.addWord("mnoqrtumnoqrsu"),
				start.addWord("mnoqrtumnoqrtu"),
				start.addWord("mnoqrtumnpqrsu"),
				start.addWord("mnoqrtumnpqrtu"),
				start.addWord("mnpqrsumnoqrsu"),
				start.addWord("mnpqrsumnoqrtu"),
				start.addWord("mnpqrsumnpqrsu"),
				start.addWord("mnpqrsumnpqrtu"),
				start.addWord("mnpqrtumnoqrsu"),
				start.addWord("mnpqrtumnoqrtu"),
				start.addWord("mnpqrtumnpqrsu"),
				start.addWord("mnpqrtumnpqrtu")
		));

		String skipmatch = m.skipmatch("mnoqrumnpqtu", 6);

		assertNotNull(skipmatch);
		assertTrue(Set.of("mnoqrtumnpqrtu", "mnoqrsumnpqrtu").contains(skipmatch));

	}

	@Test
	public void testBSkipmatchDAG() {
		/**
		 * Testet Matching mit einem vielfach verzweigten DAG.
		 * 
		 * hi -------- \
		 * hello --------- howareyou ------ \
		 * 									 ---- end -- (final state)
		 * hola-------- \
		 * buenosdias --------- comoteva -- / 
		 * 
		 */
		
		Matcher m = createMultiDAG();
		
		String skipmatch = m.skipmatch("hlooaeoed", 2);
		// simple match		
		assertTrue(Set.of("hellohowareyouend").contains(skipmatch));
		
		// no match
		String skipmatch_none = m.skipmatch("hello", 2);
		assertNull(skipmatch_none);
	}
	
	@Test
	public void testBSkipmatchDAGLargeK() {
		/**
		 * Testet Matching mit einem vielfach verzweigten DAG.
		 * 
		 * hi -------- \
		 * hello --------- howareyou ------ \
		 * 									 ---- end -- (final state)
		 * hola-------- \
		 * buenosdias --------- comoteva -- / 
		 * 
		 */
		
		Matcher m = createMultiDAG();
		
		String skipmatch = m.skipmatch("holacomotvaend", 10);	
		assertTrue(Set.of("holacomotevaend").contains(skipmatch));
	}
}