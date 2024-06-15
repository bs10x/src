import java.util.*;

public class NestedCycles {
	
	public static void main(String[] args) {
		Node n1 = new Node(null, null);
		Node n2 = new Node(null, null);
		n1.setOuter(n2);
		n2.setOuter(n1);
		
		System.out.println(isNestedCycle(n1)); //expected: true
		Map<Node, Integer> dist = cycleDistance(n1);
		System.out.println("Distance size: " + dist.size()); //expected: 2
		System.out.println("Distance from n1 to n1: " + dist.get(n1)); //expected: 0
		System.out.println("Distance from n1 to n2: " + dist.get(n2)); //expected: 1
	}
	
	public static Map<Node, Integer> cycleDistance(Node n0) {
		//TODO
		return null;
	}
	
	public static boolean isNestedCycle(Node n0) {
		//TODO
		return false;
	}
}
