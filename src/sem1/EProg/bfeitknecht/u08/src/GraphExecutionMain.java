
public class GraphExecutionMain {
	
	public static void main(String[] args) {
		// Sie koennen die main Methode beliebig aendern.
		
		Node m4 = Node.newAddNode(1);
		Node m5 = Node.newAddNode(3);
		
		Node m2 = Node.newChoiceNode(new Node[] {m4,m5});
		Node m3 = Node.newAddNode(3);
		
		Node m1 = Node.newChoiceNode(new Node[] {m2,m3});
		
		LinkedProgramStateList results = GraphExecution.allResults(m1, new ProgramState(4,5));
		
		System.out.println(results.size);
	}


}
