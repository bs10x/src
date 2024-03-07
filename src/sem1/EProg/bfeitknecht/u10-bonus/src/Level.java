import java.util.ArrayList;
import java.util.List;

public class Level {
	
	private List<Integer> points;
	
	public Level(List<Integer> points) {
		this.points = new ArrayList<>(points);
	}
	
	public List<Integer> getPoints() {
		return new ArrayList<>(points);
	}
}
