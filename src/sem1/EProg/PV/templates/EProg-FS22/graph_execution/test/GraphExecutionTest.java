import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GraphExecutionTest {
	
	/** a) **/
	
	@Test
	public void testAllResults1() {
		Node m1 = graph1(true);
		
		List<ProgramState> results = GraphExecution.allResults(m1, new ProgramState(1,2));
		
		assertNotNull(results);
		assertResultsCorrect(List.of(new ProgramState(12,7)), results);
	}
	
	@Test
	public void testAllResults2() {
		Node m1 = graph1(false);
		
		List<ProgramState> results = GraphExecution.allResults(m1, new ProgramState(0,0));
		
		assertNotNull(results);
		assertResultsCorrect(List.of(new ProgramState(13,4), new ProgramState(4,3)), results);
	}
	
	@Test
	public void testAllResults3() {
		Node m4 = Node.newAddNode(1);
		Node m5 = Node.newAddNode(3);
		
		Node m2 = Node.newChoiceNode(List.of(m4,m5));
		Node m3 = Node.newAddNode(3);
		
		Node m1 = Node.newChoiceNode(List.of(m2,m3));
		
		List<ProgramState> results = GraphExecution.allResults(m1, new ProgramState(4,5));
		
		assertNotNull(results);
		assertResultsCorrect(
				List.of(new ProgramState(5,6), new ProgramState(7,6), new ProgramState(7,6)),
				results
			);
	}

	
	/** b) **/
	
	@Test
	public void testSort1() {
		List<List<ProgramState>> input = new ArrayList<List<ProgramState>>();
		
		List<ProgramState> stateList1 = new ArrayList<ProgramState>();
		stateList1.add(new ProgramState(2,3));
		stateList1.add(new ProgramState(2,4));
		stateList1.add(new ProgramState(2,2));
		
		input.add(stateList1);
		
		List<ProgramState> stateList2 = new ArrayList<ProgramState>();
		stateList2.add(new ProgramState(2,5));
		stateList2.add(new ProgramState(2,4));
		stateList2.add(new ProgramState(2,2));
		
		input.add(stateList2);
		
		
		GraphExecution.sort(input);
		
		assertEquals(2, input.size());
		
		assertSame(stateList2, input.get(0));
		assertSame(stateList1, input.get(1));		
	}
	
	@Test
	public void testSort2() {
		List<List<ProgramState>> input = new ArrayList<List<ProgramState>>();
		
			
		List<ProgramState> stateList1 = new ArrayList<ProgramState>();
		stateList1.add(new ProgramState(0,0));
		
		input.add(stateList1);
		
		List<ProgramState> stateList2 = new ArrayList<ProgramState>();
		stateList2.add(new ProgramState(3,1));
		
		input.add(stateList2);
		
		List<ProgramState> stateList3 = new ArrayList<ProgramState>();
		stateList3.add(new ProgramState(2,3));
		stateList3.add(new ProgramState(2,4));
		stateList3.add(new ProgramState(2,2));
		
		input.add(stateList3);

		
		GraphExecution.sort(input);
		
		assertEquals(3, input.size());
		
		assertSame(stateList2, input.get(0));
		assertSame(stateList3, input.get(1));
		assertSame(stateList1, input.get(2));
	}
	
	@Test
	public void testSort3() {
		List<List<ProgramState>> input = new ArrayList<List<ProgramState>>();
		
		List<ProgramState> stateList1 = new ArrayList<ProgramState>();
		stateList1.add(new ProgramState(1,2));
		stateList1.add(new ProgramState(10,20));
		stateList1.add(new ProgramState(2,100));
		stateList1.add(new ProgramState(2,200));
		stateList1.add(new ProgramState(2,300));
		stateList1.add(new ProgramState(10,41));
		stateList1.add(new ProgramState(10,30));
		
		input.add(stateList1);
		
		List<ProgramState> stateList2 = new ArrayList<ProgramState>();
		stateList2.add(new ProgramState(10,40));
		stateList2.add(new ProgramState(2,30));
		
		input.add(stateList2);

		
		List<ProgramState> stateList3 = new ArrayList<ProgramState>();			
		stateList3.add(new ProgramState(2,20));
		stateList3.add(new ProgramState(11,50));
		stateList3.add(new ProgramState(11,40));
		stateList3.add(new ProgramState(8,5));
		
		input.add(stateList3);
		
		GraphExecution.sort(input);
		
		assertEquals(3, input.size());
		
		assertSame(stateList3, input.get(0));
		assertSame(stateList1, input.get(1));
		assertSame(stateList2, input.get(2));
	}
	
	/** c) **/
	
	@Test
	public void testIsSubProgram1() {
		Node m1 = graph1(true);
		
		assertTrue(GraphExecution.isSubProgram(m1, Node.newAddNode(4)));
		assertFalse(GraphExecution.isSubProgram(m1, Node.newAddNode(100)));
	}
	
	@Test
	public void testIsSubProgram2() {
		Node m1 = graph2();
		
		Node m2 = m1.getSubnodes().get(0);
		
		assertTrue(GraphExecution.isSubProgram(m1, m2));

		Node m2New = Node.newSeqNode(List.of(Node.newAddNode(1), Node.newAddNode(2)));
		Node m2NewSwap = Node.newSeqNode(List.of(Node.newAddNode(2), Node.newAddNode(1)));			
		assertTrue(GraphExecution.isSubProgram(m1, m2New));
		assertTrue(GraphExecution.isSubProgram(m1, m2NewSwap));

		
		Node m2False = Node.newSeqNode(List.of(Node.newAddNode(3), Node.newAddNode(1)));

		
		assertFalse(GraphExecution.isSubProgram(m2, m2False));
	}
	
	@Test
	public void testIsSubProgram3() {
		Node m1 = graph1(true);
		
		assertTrue(GraphExecution.isSubProgram(m1, m1));
		assertTrue(GraphExecution.isSubProgram(m1, m1.getSubnodes().get(1)));
		
		assertFalse(GraphExecution.isSubProgram(m1, Node.newAddNode(100)));
	}
	
	@Test
	public void testIsSubProgram4() {
		Node m1 = graph1(true);
		
		Node m5True = Node.newSeqNode(List.of(Node.newAddNode(3), Node.newAddNode(4)));		
		assertTrue(GraphExecution.isSubProgram(m1, m5True));
		
		Node m5False = Node.newSeqNode(List.of(Node.newAddNode(3), Node.newAddNode(10)));
		assertFalse(GraphExecution.isSubProgram(m1, m5False));
	}
	
	
	/** example graphs **/	
	public Node graph1(boolean determ) {
		Node m2 = Node.newAddNode(1);
				
		Node m7 = Node.newAddNode(4);
		Node m8 = Node.newAddNode(3);
		Node m5 = Node.newSeqNode(List.of(m7,m8));
		
		Node m6 = Node.newAddNode(-2);
		Node m3 = determ ? Node.newSeqNode(List.of(m5,m6)) : Node.newChoiceNode(List.of(m5,m6));
		
		Node m4 = Node.newAddNode(5);
		
		Node m1 = Node.newSeqNode(List.of(m2,m3,m4));
		
		return m1;		
	}
	
	public Node graph2() {
		Node m3 = Node.newAddNode(1);
		Node m4 = Node.newAddNode(2);
		
		Node m2 = Node.newSeqNode(List.of(m3,m4));
		
		Node m1 = Node.newSeqNode(List.of(m2));
		
		return m1;
	}
	
	/** helper methods **/
	
	public static void assertResultsCorrect(List<ProgramState> expected, List<ProgramState> actual) {
		assertEquals(expected.size(), actual.size());
		
		List<ProgramState> expectedCopy  = new ArrayList<ProgramState>(expected);		
		
		for(ProgramState result : actual) {
			boolean couldRemove = removeFromList(expectedCopy, result);
			if(!couldRemove) {
				throw new AssertionError(String.format("Zustand (%d,%d) sollte nicht vorkommen oder kommt zu oft vor", result.getSum(), result.getCounter()));
			}
		}
		
	}
	
	public static boolean removeFromList(List<ProgramState> list, ProgramState elem) {
		Iterator<ProgramState> it = list.iterator();
		while(it.hasNext()) {
			ProgramState cur = it.next();
			if(equalsProgramResult(cur, elem)) {
				it.remove();
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean equalsProgramResult(ProgramState r1, ProgramState r2) {
		return r1.getSum() == r2.getSum() && r1.getCounter() == r2.getCounter();
	}
	
}
