
import java.util.Set;

public class City {

	public int size;

	public Set<City> oneWayStreets;

	public City(int size, Set<City> next) {
		this.size = size;
		this.oneWayStreets = next;
	}
	
	@Override
	public String toString() {
		return super.toString()+" <City size=" + this.size + ">";
	}

}
