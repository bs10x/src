import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Labyrinth {
	
	public static List<Room> exits(Room room, List<Room> prefix) {
		//TODO
		return null;
	}
	
	public static void sortRooms(List<Room> a) {
		//TODO
	}
	
	public static boolean pathsWithSameNames(Room room, int n) {
		// TODO
		return false;
	}
	
	public static boolean sameNames(List<Room> a, List<Room> b) {
		Map<String, Integer> ma = new HashMap<>();
		for (Room x : a) {
			String n = x.getName();
			ma.put(n, ma.getOrDefault(n, 0)+1);
		}
		
		Map<String, Integer> mb = new HashMap<>();
		for (Room x : b) {
			String n = x.getName();
			mb.put(n, mb.getOrDefault(n, 0)+1);
		}
		
		if (!ma.keySet().equals(mb.keySet())) {
			return false;
		}
		
		for (String n : ma.keySet()) {
			if (!ma.get(n).equals(mb.get(n))) {
				return false;
			}
		}
		
		return true;
	}
	
}
