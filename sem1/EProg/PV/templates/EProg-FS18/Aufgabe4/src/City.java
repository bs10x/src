import java.util.HashSet;
import java.util.Set;

public class City {
	
	Set<Street> streets = new HashSet<>();
	Set<Intersection> intersections = new HashSet<>();
	
	City() {}
	
	Set<Street> deadEnds() {
		// TODO
		return null;
	}
	
	Set<Square> reachableSquares(String squareName, int distance) {
		// TODO
		return null;
	}
}
