public class Coordinate implements Comparable<Coordinate> {
	
	private int x;
	private int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public int compareTo(Coordinate other) {
		if(x != other.x) {
			return Integer.compare(x, other.x);
		} else {
			//x == other.x
			return Integer.compare(y, other.y);
		}
	}
	
	/** Falls Sie equals aendern (was nicht noetig ist, um die Aufgabe zu loesen), dann
	 *  sollten Sie potenziell hashCode() auch aendern (siehe Kommentar unten).
	 */
	@Override
	public boolean equals(Object other) {
		if (other != null && other instanceof Coordinate) {
			Coordinate n = (Coordinate) other;
			return n.x == x && n.y == y;
		} else {
			return false;
		}
	}
	
	/** Gemaess der Java-Spezifikation muss folgende Bedingung immer gelten: 
	 *  Wenn a.equals(b) true zurueckgibt, dann muss a.hashCode() == b.hashCode() gelten.
	 *  Das heisst, wenn Sie Coordinate.equals oder Coordinate.hashCode aendern (was nicht noetig ist, 
	 *  um die Aufgabe zu loesen), dann sollten Sie sicherstellen, dass die Bedingung immer noch gilt. 
	 */
	@Override
	public int hashCode() {
		return x * 10000 + y;
	}

}
