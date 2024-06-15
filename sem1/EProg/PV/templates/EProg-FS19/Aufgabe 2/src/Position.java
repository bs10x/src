/**
 * Eine Position in einer 2D-Matrix, bestehend aus i- und j-Wert.
 * Beide Werte sind 0-basiert, d.h. das Element ganz links oben
 * in einer Matrix hat die Position (0, 0).
 * 
 * Diese Klasse darf nicht verändert werden.
 */
public class Position {
	public int i;
	public int j;

	public Position(int i, int j) {
		this.i = i;
		this.j = j;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Position)) {
			return false;
		}
		Position other = (Position) obj;
		return i == other.i && j == other.j;
	}
	
	@Override
	public String toString() {
		return "(" + i + "," + j + ")";
	}
}
