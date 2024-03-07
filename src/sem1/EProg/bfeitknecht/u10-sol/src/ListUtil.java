
public class ListUtil {
	
	static void addMin(IntList x) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < x.getSize(); i += 1) {
			if (min > x.get(i)) {
				min = x.get(i);
			}
		}
		x.addLast(min);
	}
	
	static void addMinImpl(LinkedIntList x) {
		addMin(x);
	}
}
