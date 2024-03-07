public class ArrayUtil {
	
	public static int[] zeroInsert(int[] x) {
		// TODO: Vervollstaendigen Sie den Code wie in der Aufgabenbeschreibung beschrieben
		return null;
	}
	
	public static boolean tenFollows(int[] x, int index) {
		// TODO: Vervollstaendigen Sie den Code wie in der Aufgabenbeschreibung beschrieben
		return false;
		
	}

	
	public static void main(String[] args) {
		// zeroInsert
		System.out.println("Teilaufgabe 1:");
		System.out.println("zeroInsert([3, 4, 5]): " + zeroInsert(new int[] {3, 4, 5}));
		System.out.println("zeroInsert([3, 0, -5]): " + zeroInsert(new int[] {3, 0, -5}));
		System.out.println("zeroInsert([-3, 4, 6, 9, -8]): " + zeroInsert(new int[] {-3, 4, 6, 9, -8}));
		
		// tenFollows
		System.out.println("\nTeilaufgabe 2");
		System.out.println("tenFollows([1, 2, 20], 0): " + tenFollows(new int[] {1, 2, 20}, 0));
		System.out.println("tenFollows([1, 2, 7, 20], 0): " + tenFollows(new int[] {1, 2, 7, 20}, 0));
		System.out.println("tenFollows([3, 30], 0): " + tenFollows(new int[] {3, 30}, 0));
		System.out.println("tenFollows([3], 0): " + tenFollows(new int[] {3}, 0));
		System.out.println("tenFollows([1, 2, 20, 5], 1): " + tenFollows(new int[] {1, 2, 20, 5}, 1));
		System.out.println("tenFollows([1, 2, 20, 5], 2): " + tenFollows(new int[] {1, 2, 20, 5}, 2));
	}
}
