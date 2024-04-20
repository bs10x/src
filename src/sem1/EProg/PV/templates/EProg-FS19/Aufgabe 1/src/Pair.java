/**
 * Ein Paar von int-Werten.
 * 
 * Diese Klasse darf nicht verändert werden.
 */
public class Pair {
	public int first;
	public int second;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Pair)) {
			return false;
		}
		Pair other = (Pair) obj;
		return first == other.first && second == other.second;
	}

	@Override
	public String toString() {
		return "(" + first + "," + second + ")";
	}
}
