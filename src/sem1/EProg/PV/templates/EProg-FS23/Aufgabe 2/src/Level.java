import java.util.HashSet;
import java.util.Set;

public class Level {
	
	private Set<Integer> points;
	
	public Level(Set<Integer> points) {
		this.points = new HashSet<>(points);
	}
	
	public Set<Integer> getPoints() {
		return new HashSet<>(points);
	}
}
