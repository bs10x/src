import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		// Sie koennen die main Methode beliebig aendern.
		
		/** a) **/
		Node m4 = Node.newAddNode(1);
		Node m5 = Node.newAddNode(3);
		
		Node m2 = Node.newChoiceNode(List.of(m4,m5));
		Node m3 = Node.newAddNode(3);
		
		Node m1 = Node.newChoiceNode(List.of(m2,m3));
		
		List<ProgramState> results = GraphExecution.allResults(m1, new ProgramState(4,5));
		
		System.out.println(results.size());
		
		/** b) **/
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
				
		System.out.println(Arrays.toString(input.toArray()));
		GraphExecution.sort(input);
		System.out.println(Arrays.toString(input.toArray()));
		
		
		/** c **/
		Node r3 = Node.newAddNode(1);
		Node r4 = Node.newAddNode(2);
		
		Node r2 = Node.newSeqNode(List.of(r3,r4));
		
		Node r1 = Node.newSeqNode(List.of(r2));
			
		Node r2New = Node.newSeqNode(List.of(Node.newAddNode(1), Node.newAddNode(2)));
		Node r2NewSwap = Node.newSeqNode(List.of(Node.newAddNode(2), Node.newAddNode(1)));			
		System.out.println(GraphExecution.isSubProgram(r1, r2New));
		System.out.println(GraphExecution.isSubProgram(r1, r2NewSwap));
		
		Node r2False = Node.newSeqNode(List.of(Node.newAddNode(3), Node.newAddNode(1)));
		System.out.println(GraphExecution.isSubProgram(r1, r2False));
	}


}
