import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

public class GradingGraphExecutionTest {

	@Test
	public void testA01() {
		Node m1 = graph1(true);

		LinkedProgramStateList results = GraphExecution.allResults(m1, new ProgramState(4, 5));

		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(11, 10)), results);
	}

	@Test
	public void testA02() {
		Node m1 = graph1(false);

		LinkedProgramStateList results = GraphExecution.allResults(m1, new ProgramState(1, 1));

		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(5, 5), new ProgramState(6, 4)), results);
	}

	@Test
	public void testA03() {
		Node m4 = Node.newAddNode(-31);
		Node m5 = Node.newAddNode(1);

		Node m2 = Node.newChoiceNode(new Node[] {m4, m5});
		Node m3 = Node.newAddNode(10);

		Node m1 = Node.newChoiceNode(new Node[] {m2, m3});

		LinkedProgramStateList results = GraphExecution.allResults(m1, new ProgramState(1, 1));

		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(-30, 2), new ProgramState(2, 2), new ProgramState(11, 2)),
				results);
	}
	
	@Test
	public void testA04() {
		Node m1 = Node.newAddNode(2);

		LinkedProgramStateList results = GraphExecution.allResults(m1, new ProgramState(0, 5));

		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(2, 6)), results);
	}

	@Test
	public void testA05() {
		Node m4 = Node.newAddNode(4);
		Node m5 = Node.newAddNode(-9);

		Node m1 = Node.newChoiceNode(new Node[] {m4, m5});

		LinkedProgramStateList results = GraphExecution.allResults(m1, new ProgramState(2, 3));

		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(6, 4), new ProgramState(-7, 4)), results);
	}

	@Test
	public void testA06() {

		// testing a SEQ-node

		Node m4 = Node.newAddNode(3);
		Node m5 = Node.newAddNode(7);

		Node m1 = Node.newSeqNode(new Node[] {m4, m5});

		LinkedProgramStateList results = GraphExecution.allResults(m1, new ProgramState(0, 0));

		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(10, 2)), results);
	}

	@Test
	public void testA07() {
		Node m1 = Node.newAddNode(3);
		Node m2 = Node.newAddNode(2);
		Node m3 = Node.newAddNode(1);
		Node m4 = Node.newAddNode(1);
		Node m5 = Node.newAddNode(-2);

		Node m6 = Node.newSeqNode(new Node[] {m1, m2});
		Node m7 = Node.newChoiceNode(new Node[] {m4, m5});
		Node m8 = Node.newSeqNode(new Node[] {m3, m7});
		Node m9 = Node.newChoiceNode(new Node[] {m6, m8});

		LinkedProgramStateList results = GraphExecution.allResults(m9, new ProgramState(4, 5));

		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(9, 7), new ProgramState(6, 7), new ProgramState(3, 7)), results);
	}

	@Test
	public void testA08() {
		Node m1 = Node.newAddNode(-2);
		Node m2 = Node.newAddNode(4);
		Node m3 = Node.newAddNode(1);
		Node m4 = Node.newAddNode(6);
		Node m5 = Node.newAddNode(7);

		Node m6 = Node.newChoiceNode(new Node[] {m1, m2});
		Node m7 = Node.newChoiceNode(new Node[] {m3, m6});
		Node m8 = Node.newChoiceNode(new Node[] {m4, m7});
		Node m9 = Node.newChoiceNode(new Node[] {m5, m8});

		LinkedProgramStateList results = GraphExecution.allResults(m9, new ProgramState(-3, 3));

		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(4, 4), new ProgramState(3, 4), new ProgramState(-2, 4),
				new ProgramState(-5, 4), new ProgramState(1, 4)), results);
	}

	@Test
	public void testA09() {
		Node m1 = Node.newAddNode(9);
		Node m2 = Node.newAddNode(12);
		Node m3 = Node.newAddNode(-11);
		Node m4 = Node.newAddNode(-6);
		Node m5 = Node.newAddNode(-2);

		Node m6 = Node.newSeqNode(new Node[] {m1, m2});
		Node m7 = Node.newSeqNode(new Node[] {m3, m6});
		Node m8 = Node.newSeqNode(new Node[] {m4, m7});
		Node m9 = Node.newSeqNode(new Node[] {m5, m8});

		LinkedProgramStateList results = GraphExecution.allResults(m9, new ProgramState(2, 2));

		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(4, 7)), results);
	}

	@Test
	public void testA10() {
		Node m1 = Node.newAddNode(1);
		Node m2 = Node.newAddNode(2);
		Node m3 = Node.newAddNode(3);
		Node m4 = Node.newAddNode(-5);
		Node m5 = Node.newAddNode(-4);
		Node m6 = Node.newAddNode(-3);
		Node m7 = Node.newAddNode(10);
		Node m8 = Node.newAddNode(11);
		Node m9 = Node.newAddNode(12);
		Node m10 = Node.newAddNode(-10);

		Node m11 = Node.newSeqNode(new Node[] {m1, m2, m3});
		Node m12 = Node.newSeqNode(new Node[] {m4, m5});
		Node m13 = Node.newSeqNode(new Node[] {m6});
		Node m14 = Node.newSeqNode(new Node[] {m7, m8});
		Node m15 = Node.newChoiceNode(new Node[] {m10, m12, m13, m14});
		Node m16 = Node.newChoiceNode(new Node[] {m15});
		Node m17 = Node.newChoiceNode(new Node[] {m16, m11});
		Node m18 = Node.newChoiceNode(new Node[] {m17, m9});

		LinkedProgramStateList results = GraphExecution.allResults(m18, new ProgramState(0, 1));

		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(-10, 2), new ProgramState(-9, 3), new ProgramState(-3, 2),
				new ProgramState(21, 3), new ProgramState(6, 4), new ProgramState(12, 2)), results);
	}

	@Test
	public void testA11() {
		Node m1 = Node.newAddNode(1);
		Node m2 = Node.newAddNode(2);
		Node m3 = Node.newAddNode(3);
		Node m4 = Node.newAddNode(-5);
		Node m5 = Node.newAddNode(-4);
		Node m6 = Node.newAddNode(-3);
		Node m7 = Node.newAddNode(10);
		Node m8 = Node.newAddNode(11);
		Node m9 = Node.newAddNode(12);
		Node m10 = Node.newAddNode(-10);

		Node m11 = Node.newChoiceNode(new Node[] {m1, m2, m3});
		Node m12 = Node.newChoiceNode(new Node[] {m4, m5});
		Node m13 = Node.newChoiceNode(new Node[] {m6});
		Node m14 = Node.newChoiceNode(new Node[] {m7, m8});
		Node m15 = Node.newChoiceNode(new Node[] {m10, m12, m13, m14});
		Node m16 = Node.newChoiceNode(new Node[] {m15});
		Node m17 = Node.newChoiceNode(new Node[] {m16, m11});
		Node m18 = Node.newChoiceNode(new Node[] {m17, m9});

		LinkedProgramStateList results = GraphExecution.allResults(m18, new ProgramState(0, 1));

		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(-10, 2), new ProgramState(-5, 2), new ProgramState(-4, 2),
				new ProgramState(-3, 2), new ProgramState(10, 2), new ProgramState(11, 2), new ProgramState(1, 2),
				new ProgramState(2, 2), new ProgramState(3, 2), new ProgramState(12, 2)), results);
	}

	@Test
	public void testA12() {
		Node m1 = Node.newAddNode(1);
		Node m2 = Node.newAddNode(2);
		Node m3 = Node.newAddNode(3);
		Node m4 = Node.newAddNode(-5);
		Node m5 = Node.newAddNode(-4);
		Node m6 = Node.newAddNode(-3);
		Node m7 = Node.newAddNode(10);
		Node m8 = Node.newAddNode(11);
		Node m9 = Node.newAddNode(12);
		Node m10 = Node.newAddNode(-10);

		Node m11 = Node.newSeqNode(new Node[] {m1, m2, m3});
		Node m12 = Node.newSeqNode(new Node[] {m4, m5});
		Node m13 = Node.newSeqNode(new Node[] {m6});
		Node m14 = Node.newSeqNode(new Node[] {m7, m8});
		Node m15 = Node.newSeqNode(new Node[] {m10, m12, m13, m14});
		Node m16 = Node.newSeqNode(new Node[] {m15});
		Node m17 = Node.newSeqNode(new Node[] {m16, m11});
		Node m18 = Node.newSeqNode(new Node[] {m17, m9});

		LinkedProgramStateList results = GraphExecution.allResults(m18, new ProgramState(0, 1));

		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(17, 11)), results);
	}

	@Test
	public void testA13() {
		Node m4 = Node.newAddNode(1000);
		Node m5 = Node.newAddNode(2000);

		Node m1 = new Node(3, "ADD", new Node[] {m4, m5});

		LinkedProgramStateList results = GraphExecution.allResults(m1, new ProgramState(2, 3));

		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(5, 4)), results);
	}

	@Test
	public void testA14() {
		Node m1 = Node.newAddNode(2);
		Node m2 = Node.newChoiceNode(new Node[] {m1});

		LinkedProgramStateList results = GraphExecution.allResults(m2, new ProgramState(0, 0));

		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(2, 1)), results);
	}

	@Test
	public void testA15() {
		Node m1 = Node.newAddNode(2);
		Node m2 = Node.newSeqNode(new Node[] {m1});

		LinkedProgramStateList results = GraphExecution.allResults(m2, new ProgramState(0, 0));

		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(2, 1)), results);
	}

	@Test
	public void testA16() {
		Node m1 = Node.newAddNode(-5);
		Node m2 = Node.newAddNode(-4);
		Node m3 = Node.newAddNode(-3);
		Node m4 = Node.newAddNode(-2);
		Node m5 = Node.newAddNode(-1);
		Node m6 = Node.newAddNode(0);
		Node m7 = Node.newAddNode(1);
		Node m8 = Node.newAddNode(2);
		Node m9 = Node.newChoiceNode(new Node[] {m1, m2, m3, m4, m5, m6, m7, m8});

		LinkedProgramStateList results = GraphExecution.allResults(m9, new ProgramState(0, 0));

		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(-5, 1), new ProgramState(-4, 1), new ProgramState(-3, 1),
				new ProgramState(-2, 1), new ProgramState(-1, 1), new ProgramState(0, 1), new ProgramState(1, 1),
				new ProgramState(2, 1)), results);
	}

	@Test
	public void testA17() {
		Node m1 = Node.newAddNode(-5);
		Node m2 = Node.newAddNode(-4);
		Node m3 = Node.newAddNode(-3);
		Node m4 = Node.newAddNode(-2);
		Node m5 = Node.newAddNode(-1);
		Node m6 = Node.newAddNode(0);
		Node m7 = Node.newAddNode(1);
		Node m8 = Node.newAddNode(2);
		Node m9 = Node.newSeqNode(new Node[] {m1, m2, m3, m4, m5, m6, m7, m8});

		LinkedProgramStateList results = GraphExecution.allResults(m9, new ProgramState(0, 0));

		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(-12, 8)), results);
	}
	

	/** example graphs **/
	public Node graph1(boolean determ) {
		Node m2 = Node.newAddNode(1);
		
		Node m7 = Node.newAddNode(7);
		Node m8 = Node.newAddNode(-5);
		Node m5 = Node.newSeqNode(new Node[] {m7,m8});
		
		Node m6 = Node.newAddNode(3);
		Node m3 = determ ? Node.newSeqNode(new Node[] {m5,m6}) : Node.newChoiceNode(new Node[] {m5,m6});
		
		Node m4 = Node.newAddNode(1);
		
		Node m1 = Node.newSeqNode(new Node[] {m2,m3,m4});

		return m1;
	}

	/** helper methods **/

	public static void assertResultsCorrect(LinkedProgramStateList expected, LinkedProgramStateList actual) {
		assertEquals(expected.size, actual.size);
		
		
		LinkedProgramStateList expectedCopy = new LinkedProgramStateList();
		for (int i = 0; i < expected.size; i += 1) {
			expectedCopy.addLast(expected.get(i));
		}
		
		for(int i = 0; i < actual.size; i += 1) {
			ProgramState result = actual.get(i);
			boolean couldRemove = removeFromList(expectedCopy, result);
			if(!couldRemove) {
				throw new AssertionError(String.format("Zustand (%d,%d) sollte nicht vorkommen oder kommt zu oft vor", result.getSum(), result.getCounter()));
			}
		}	
	}
	
	public static boolean removeFromList(LinkedProgramStateList list, ProgramState elem) {
		for (int i = 0; i < list.size; i += 1) {
			ProgramState cur = list.get(i);
			if (equalsProgramResult(cur, elem)) {
				list.remove(i);
				return true;
			}
		}
		return false;
	}

	public static boolean equalsProgramResult(ProgramState r1, ProgramState r2) {
		return r1.getSum() == r2.getSum() && r1.getCounter() == r2.getCounter();
	}

}
