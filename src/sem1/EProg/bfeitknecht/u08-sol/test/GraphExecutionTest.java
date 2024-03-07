import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GraphExecutionTest {
	
	@Test
	public void testAllResults1() {
		Node m1 = graph1(true);
		
		LinkedProgramStateList results = GraphExecution.allResults(m1, new ProgramState(1,2));
		
		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(12,7)), results);
	}
	
	@Test
	public void testAllResults2() {
		Node m1 = graph1(false);
		
		LinkedProgramStateList results = GraphExecution.allResults(m1, new ProgramState(0,0));
		
		assertNotNull(results);
		assertResultsCorrect(LinkedProgramStateList.of(new ProgramState(13,4), new ProgramState(4,3)), results);
	}
	
	@Test
	public void testAllResults3() {
		Node m4 = Node.newAddNode(1);
		Node m5 = Node.newAddNode(3);
		
		Node m2 = Node.newChoiceNode(new Node[] {m4,m5});
		Node m3 = Node.newAddNode(3);
		
		Node m1 = Node.newChoiceNode(new Node[] {m2,m3});
		
		LinkedProgramStateList results = GraphExecution.allResults(m1, new ProgramState(4,5));
		
		assertNotNull(results);
		assertResultsCorrect(
				LinkedProgramStateList.of(new ProgramState(5,6), new ProgramState(7,6), new ProgramState(7,6)),
				results
			);
	}
	
	/** example graphs **/	
	public Node graph1(boolean determ) {
		Node m2 = Node.newAddNode(1);
				
		Node m7 = Node.newAddNode(4);
		Node m8 = Node.newAddNode(3);
		Node m5 = Node.newSeqNode(new Node[] {m7,m8});
		
		Node m6 = Node.newAddNode(-2);
		Node m3 = determ ? Node.newSeqNode(new Node[] {m5,m6}) : Node.newChoiceNode(new Node[] {m5,m6});
		
		Node m4 = Node.newAddNode(5);
		
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
