import java.util.HashMap;
import java.util.Map;

public class U11Map {

	public static Map<String, Integer> arrayToMap(String[] A) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		for(String a : A) {
			result.put(a, 0);
		}
		
		return result;
	}
	
	public static Map<String, Integer> arrayToMapOne(String[] A) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		for(String a : A) {
			if(result.containsKey(a)) {
				result.put(a, 1);
			} else {
				result.put(a, 0);
			}
		}
		
		return result;
	}
}
