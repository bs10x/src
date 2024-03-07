
public class Node {
	
	String value;
	Node[] neighbors;
	
	
	
	Node(String value) {
		this.value = value;
	}
	
	
	
	
	Node[] getNeighbors() {
		
		Node[] neighbors  = new Node[value.length()];
		
		
		for(int i = 0; i < value.length(); i++) {
			
			neighbors[i] = new Node("0");
						
			//if(true) {
				//neighbors.add(currentNode);
			//}
			
		}
		
		

		return neighbors;
		//return null;

	}
}