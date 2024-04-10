import static org.junit.Assert.*;

import org.junit.Test;

public class SplitTest {
	
	// Wir empfehlen weitere Tests hinzuzufuegen
	// Versuchen Sie zuerst, dass die Sanity Checks erfolgreich sind, bevor Sie mit 'testInput' testen.
	
	@Test
	public void aufgabenBlattLaengenTest() {
		sizeSanityCheck(new int[] {3, 7, 2, 4, 5}, 4);
	}
	
	@Test
	public void aufgabenBlattLastFeldTest() {
		lastSanityCheck(new int[] {3, 7, 2, 4, 5}, 4);
	}
	
	@Test
	public void aufgabenBlattAlteSequenzTest() {
		oldSequenceSanityCheck(new int[] {3, 7, 2, 4, 5}, 4);
	}
	
	@Test
	public void aufgabenBlattNeueSequenzTest() {
		newSequenceSanityCheck(new int[] {3, 7, 2, 4, 5}, 4);
	}
	
	@Test
	public void aufgabenBlattTest() {
		testInput(new int[] {3, 7, 2, 4, 5}, 4);
	}

	@Test
	public void doppelterTest() {
		testInput(new int[] {1, 5, 4, 7, 2, 5}, 4);
		testInput(new int[] {1, 5, 4, 7, 2, 5}, 4);
		testInput(new int[] {1, 5, 4, 7, 2, 5, 1, 5, 4, 7, 2, 5, 1, 5, 4, 7, 2, 5, 1, 5, 4, 7, 2, 5, 1, 5, 4, 7, 2, 5}, 4);
	}
	
	@Test
	public void emptyTest() {
		testInput(new int[] {}, 4);
		testInput(new int[] {1, 5, 4, 7, 2, 5}, 0);
		testInput(new int[] {1, 5, 4, 7, 2, 5}, 9);
	}
	
	/** Verwenden Sie diese Method um eine Ausfuehrung von split() zu testen */
	/** Die Methode prueft, dass die Summe der Laengen der resultierenden Listen gleich der Laenge der urspruenglichen Liste ist */
	static void sizeSanityCheck(int[] values, int zahl) {
		
		SpecialLinkedIntList lower = new SpecialLinkedIntList();
		
		for (int i = 0; i < values.length; i += 1) {
			lower.addLast(values[i]);
		}
		
		SpecialLinkedIntList higher = lower.split(zahl);
		
		int lowerLength = 0;
		for (SpecialIntNode n = lower.first; n != null; n = n.next) {
			lowerLength += 1;
		}
		
		int higherLength = 0;
		for (SpecialIntNode n = higher.first; n != null; n = n.next) {
			higherLength += 1;
		}
		
		assertEquals(values.length, lowerLength + higherLength);
	}
	
	/** Verwenden Sie diese Method um eine Ausfuehrung von split() zu testen */
	/** Die Methode prueft, dass beide resultierende Listen korrekt das last Feld gesetzt haben */
	static void lastSanityCheck(int[] values, int zahl) {
		
		SpecialLinkedIntList lower = new SpecialLinkedIntList();
		
		for (int i = 0; i < values.length; i += 1) {
			lower.addLast(values[i]);
		}
		
		SpecialLinkedIntList higher = lower.split(zahl);
		
		
		SpecialIntNode lastLower = null;
		for (SpecialIntNode n = lower.first; n != null; n = n.next) {
			lastLower = n;
		}
		assertEquals(lower.last, lastLower);
		
		SpecialIntNode lastHigher = null;
		for (SpecialIntNode n = higher.first; n != null; n = n.next) {
			lastHigher = n;
		}
		assertEquals(higher.last, lastHigher);
	}
	
	/** Verwenden Sie diese Method um eine Ausfuehrung von split() zu testen */
	/** Die Methode prueft, dass die urspruengliche Liste in den beiden neuen Listen korrekt vorkommt */
	static void oldSequenceSanityCheck(int[] values, int zahl) {
		
		SpecialLinkedIntList lower = new SpecialLinkedIntList();
		
		for (int i = 0; i < values.length; i += 1) {
			lower.addLast(values[i]);
		}
		
		SpecialLinkedIntList higher = lower.split(zahl);
		
		SpecialIntNode oldFirst = null;
		if (values.length > 0 && values[0] <= zahl) {
			oldFirst = lower.first;
		} else {
			oldFirst = higher.first;
		}
		
		int idx = 0;
		for (SpecialIntNode n = oldFirst; n != null; n = n.oldNext, idx += 1) {
			assertTrue(idx < values.length);
			assertEquals(values[idx], n.value);
		}
		assertEquals(values.length, idx);
	}
	
	/** Verwenden Sie diese Method um eine Ausfuehrung von split() zu testen */
	/** Die Methode prueft, dass beide resultierende Listen die korrekte Reihenfolge haben */
	static void newSequenceSanityCheck(int[] values, int zahl) {
		
		SpecialLinkedIntList lower = new SpecialLinkedIntList();
		
		for (int i = 0; i < values.length; i += 1) {
			lower.addLast(values[i]);
		}
		
		SpecialLinkedIntList higher = lower.split(zahl);
		
		SpecialIntNode currentLower = lower.first;
		SpecialIntNode currentHigher = higher.first;
		for (int i = 0; i < values.length; i += 1) {
			if (values[i] <= zahl) {
				assertTrue(currentLower != null);
				assertEquals(values[i], currentLower.value);
				currentLower = currentLower.next;
			} else {
				assertTrue(currentHigher != null);
				assertEquals(values[i], currentHigher.value);
				currentHigher = currentHigher.next;
			}
		}
	}
	
	/** Verwenden Sie diese Method um eine Ausfuehrung von split() zu testen */
	/** Wir empfehlen zuerst die Sanity Checks zu verwenden um Fehler zu finden */
	static void testInput(int[] values, int zahl) {
		TestTask task = new TestTask(values, zahl);
		task.test();
	}
}

/** Klasse zum testen der split Methode. Sie muessen diese Klasse noch nicht verstehen. */
class TestTask {
	
	int[] values;
	int zahl;
	SpecialLinkedIntList lower, higher;
	
	public TestTask(int[] values, int zahl) {
		this.values = values;
		this.zahl = zahl;
	}
	
	/** Fuehrt split() aus und testet das Ergebnis */
	void test() {
		lower = new SpecialLinkedIntList();
		
		for (int i = 0; i < values.length; i += 1) {
			lower.addLast(values[i]);
		}
		
		higher = lower.split(zahl);
		
		testLowNode(lower.first, firstLow(0));
		testHighNode(higher.first, firstHigh(0));
		testDegeneracy();
	}
	
	/** Testet Node 'current' am Index 'idx' des Inputarrays */
	void testNode(SpecialIntNode current, int idx) {
		if (idx >= values.length) {
			assertEquals(null, current);
		} else if (values[idx] <= zahl) {
			testLowNode(current, idx);
		} else {
			testHighNode(current, idx);
		}
	}
	
	/** Testet ob Node 'current' am Index 'idx' des Inputarrays ein korrektes Element von 'lower' ist */
	void testLowNode(SpecialIntNode current, int idx) {
		if (current == null) {
			assertEquals(true, idx >= values.length);
		} else {
			assertEquals(values[idx], current.value);
			assertEquals(true, current.value <= zahl);
			
			if (idx + 1 >= values.length) {
				assertEquals(null, current.oldNext);
			} else {
				testNode(current.oldNext, idx + 1);
			}
			
			int nextLowIdx = firstLow(idx + 1);
			if (nextLowIdx >= values.length) {
				assertEquals(null, current.next);
				assertEquals(lower.last, current);
			} else {
				testLowNode(current.next, nextLowIdx);
			}
		}
	}
	
	/** Testet ob Node 'current' am Index 'idx' des Inputarrays ein korrektes Element von 'higher' ist */
	void testHighNode(SpecialIntNode current, int idx) {
		if (current == null) {
			assertEquals(true, idx >= values.length);
		} else {
			assertEquals(values[idx], current.value);
			assertEquals(true, current.value > zahl);
			
			if (idx + 1 >= values.length) {
				assertEquals(null, current.oldNext);
			} else {
				testNode(current.oldNext, idx + 1);
			}
			
			int nextHighIdx = firstHigh(idx + 1);
			if (nextHighIdx >= values.length) {
				assertEquals(null, current.next);
				assertEquals(higher.last, current);
			} else {
				testHighNode(current.next, nextHighIdx);
			}
		}
	}
	
	/** Testet dass 'lower' und 'higher' Listen sind */
	void testDegeneracy() {
		SpecialLinkedIntList outer, inner;
		boolean leq;
		if (values.length > 0 && values[0] <= zahl) {
			outer = lower;
			inner = higher;
			leq = true;
		} else {
			outer = higher;
			inner = lower;
			leq = false;
		}
	
		SpecialIntNode currentOuter = outer.first, currentInner = null;
		int idx = 0;
		while(idx < values.length) {
			assertTrue(currentOuter != null);
			assertEquals(values[idx], currentOuter.value);
			assertPivot(currentOuter, leq);
			
			SpecialIntNode next = currentOuter.oldNext;
			idx += 1;
			
			while(next != currentOuter.next) {
				
				assertTrue(next != null);
				assertTrue(idx < values.length);
				assertEquals(values[idx], next.value);
				assertPivot(next, !leq);
				
				SpecialIntNode expected = currentInner == null ? inner.first : currentInner.next;
				currentInner = next;
				assertEquals(expected, next);
				
				if (next.next == null) {
					assertEquals(inner.last, next);
				}
				
				next = next.oldNext;
				idx += 1;
			}

			if (next == null) {
				assertEquals(outer.last, currentOuter);
			}
			currentOuter = next;
		}
	}
	
	/** Gibt den Index des ersten Wertes kleiner oder gleich zu 'zahl' in 'values' ab 'idx' zurueck */
	int firstLow(int idx) {
		int firstLowIdx = idx;
		while (firstLowIdx < values.length && !(values[firstLowIdx] <= zahl)) {
			firstLowIdx += 1;
		}
		return firstLowIdx;
	}
	
	/** Gibt den Index des ersten Wertes groesser als 'zahl' in 'values' ab 'idx' zurueck */
	int firstHigh(int idx) {
		int firstHighIdx = idx;
		while (firstHighIdx < values.length && !(values[firstHighIdx] > zahl)) {
			firstHighIdx += 1;
		}
		return firstHighIdx;
	}
	
	/** Testet ob der Wert von 'current' kleiner gleich 'zahl' ist falls 'leq' true ist, 
	 *  oder anonsten ob er groesser 'zahl' ist 
	 **/
	void assertPivot(SpecialIntNode current, boolean leq) {
		if (leq) {
			assertTrue(current.value <= zahl);
		} else {
			assertTrue(current.value > zahl);
		}
	}
}

