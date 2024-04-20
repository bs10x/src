import java.util.*;

public class SeqNumber {

	private int number;
	private Set<Integer> positions;
	public int min;

	public SeqNumber(int number, Set<Integer> positions) {
		this.number = number;
		this.positions = positions;
		this.min = computeMin();
	}

	public int getNumber() {
		return number;
	}

	public Set<Integer> getPositions() {
		return new HashSet<Integer>(positions);
	}

	@Override
	public String toString() {
		return number + ": " + positions.toString();
	}
	
	public int computeMin() {
		if (getPositions().size() == 1) return Integer.MAX_VALUE;
		
		int min = Integer.MAX_VALUE;
		
		int[] pos = getPositionsArray();
		for (int i = 0; i < pos.length - 1; i++) min = Math.min(min, pos[i + 1] - pos[i]);
		return min;
	}
	
	public int[] getPositionsArray() {
		int[] positionsArray = new int[positions.size()];
		Iterator<Integer> pos = positions.iterator();
		for (int i = 0; i < positions.size(); i++) {
			positionsArray[i] = pos.next();
		}
		return positionsArray;
	}
}
