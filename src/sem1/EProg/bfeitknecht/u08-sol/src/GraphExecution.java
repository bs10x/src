public class GraphExecution {
	
	/**
	 * This method calls allResultsGo to get the desired result and returns it.
	 */
	public static LinkedProgramStateList allResults(Node n, ProgramState initState) {
		LinkedProgramStateList states = new LinkedProgramStateList();
		states.addLast(initState);
		return allResultsGo(n, states);
	}
	
	/**
	 * Helper method to allResults which recursively moves through the tree and determines
     * all possible results
     * ADD-Node is the base case - adds new results 
     * SEQ-Node is a recursive case - for each child update the current results
     * CHOICE-Node is a recursive case - for each child add updated results separately (reflects the choice)
     */
	public static LinkedProgramStateList allResultsGo(Node n, LinkedProgramStateList states) {
		if (n.getType().equals("ADD")) { // 
			LinkedProgramStateList next = new LinkedProgramStateList();
			for (int i = 0; i < states.size; i += 1) { 
				ProgramState state = states.get(i);
				next.addLast(new ProgramState(state.getSum() + n.getValue(), state.getCounter() + 1));
			}
			return next;
		} else if (n.getType().equals("SEQ")) {
			LinkedProgramStateList next = states;
			for (Node ch : n.getSubnodes()) { //Recursively update the results
				next = allResultsGo(ch, next);
			}
			return next;
		} else if (n.getType().equals("CHOICE")) {
			LinkedProgramStateList next = new LinkedProgramStateList();
			for (Node ch : n.getSubnodes()) {
				LinkedProgramStateList results = allResultsGo(ch, states);
				for (int i = 0; i < results.size; i += 1) {
					next.addLast(results.get(i));
				}
			}
			return next;
		}
		return null;
	}
	
	/**
	 * Checks whether the tree rooted at n1 is or contains
     * a tree which is equivalent to the tree rooted at n2.
	 */
	static boolean isSubProgram(Node n1, Node n2) {
		if (isEquivalent(n1, n2)) {
			return true;
		} else if (n1.getType().equals("SEQ")) {
			for (Node next : n1.getSubnodes()) {
				if (isSubProgram(next, n2)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks whether two Node objects are equivalent 
	 * Two ADD-nodes are equivalent if they have the same value
	 * Two SEQ-nodes are equivalent if their subtrees are equivalent
	 */ 
	static boolean isEquivalent(Node n1, Node n2) {
		if (n1.getType().equals("ADD") && n2.getType().equals("ADD")) {
			return n1.getValue() == n2.getValue();
		} else if (n1.getType().equals("SEQ") && n2.getType().equals("SEQ") && n1.getSubnodes().length == n2.getSubnodes().length) {
			return isEquivalent(n1.getSubnodes(), n2.getSubnodes(), 0);
		}
		return false;
	}
	
	/**
	 * Checks whether two trees are equivalent
	 * Two trees are equivalent if they are exact copies of each other
	 * or if they are the same tree.
	 */
	static boolean isEquivalent(Node[] n1, Node[] n2, int n) {
		if (n1.length == 0) {
			return true;
		} else if (n >= n2.length) {
			return false;
		} else if (isEquivalent(n1[0], n2[n])) {
			Node[] n1Q = new Node[n1.length - 1];
			for (int i = 0, j = 0; i < n1.length; i += 1) {
				if (i != 0) { n1Q[j] = n1[i]; j += 1; }
			}
			Node[] n2Q = new Node[n2.length - 1];
			for (int i = 0, j = 0; i < n2.length; i += 1) {
				if (i != n) { n2Q[j] = n2[i]; j += 1; }
			}
			return isEquivalent(n1Q, n2Q, 0) || isEquivalent(n1, n2, n+1);
		} else {
			return isEquivalent(n1, n2, n+1);
		}
	}

}


