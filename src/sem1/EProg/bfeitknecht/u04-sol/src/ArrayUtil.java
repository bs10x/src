import java.util.Arrays;

public class ArrayUtil {
	
	public static int[] zeroInsert(int[] x) {
		// TODO: Vervollstaendigen Sie den Code wie in der Aufgabenbeschreibung beschrieben
		
		
		if (x.length <= 1) { // Base case: Falls die Laenge des Arrays kleiner gleich 1 ist.
			return Arrays.copyOf(x, x.length);
		} else { // Rekursiver Fall.
			
			// Wir rufen zeroInsert mit dem Tail des Arrays auf.
			int[] tail = Arrays.copyOf(x, x.length-1);
			int[] tailRes = zeroInsert(tail);
			
			int a = x[x.length-1];
			int b = x[x.length-2];
			
			int[] res;
			if ((a < 0 && b > 0) || (a > 0 && b < 0)) {
				// Falls die letzten beiden Zahlen unterschiedlich positiv oder negativ sind, dann wird eine 0 hinzugefuegt.
				res = Arrays.copyOf(tailRes, tailRes.length + 2);
				res[res.length-1] = a;
				res[res.length-2] = 0;
				res[res.length-3] = b;
			} else {
				// Ansonsten wird keine 0 hinzugefuegt.
				res = Arrays.copyOf(tailRes, tailRes.length + 1);
				res[res.length-1] = a;
			}
			return res;
		}
	}
	
	public static boolean tenFollows(int[] x, int index) {
		// TODO: Vervollstaendigen Sie den Code wie in der Aufgabenbeschreibung beschrieben
		
		if (x.length <= index) {
			return false;
		} else {
			// Wir speichern den Wert vom letzten Element in `last` um zu pruefen, ob das aktuelle Element gleich das zehnfache des letzten Elements ist.
			// Wir fangen beim Index `index` an.
			int idx = index+1;
			int last = x[index];
			
			while(idx < x.length) {
				if (x[idx] == last * 10) {
					return true;
				}
				last = x[idx];
				idx += 1;
			}
			
			return false;
		}
	}

	public static void main(String[] args) {
		// zeroInsert
		System.out.println("Teilaufgabe 1:");
		System.out.println("zeroInsert([3, 4, 5]): " + Arrays.toString(zeroInsert(new int[]{3, 4, 5})));
		System.out.println("zeroInsert([3, 0, -5]): " + Arrays.toString(zeroInsert(new int[]{3, 0, -5})));
		System.out.println("zeroInsert([-3, 4, 6, 9, -8]): " + Arrays.toString(zeroInsert(new int[]{-3, 4, 6, 9, -8})));

		// tenFollows
		System.out.println("\nTeilaufgabe 2:");
		System.out.println("tenFollows([1, 2, 20], 0): " + tenFollows(new int[] {1, 2, 20}, 0));
		System.out.println("tenFollows([1, 2, 7, 20], 0): " + tenFollows(new int[] {1, 2, 7, 20}, 0));
		System.out.println("tenFollows([3, 30], 0): " + tenFollows(new int[] {3, 30}, 0));
		System.out.println("tenFollows([3], 0): " + tenFollows(new int[] {3}, 0));
		System.out.println("tenFollows([1, 2, 20, 5], 1): " + tenFollows(new int[] {1, 2, 20, 5}, 1));
		System.out.println("tenFollows([1, 2, 20, 5], 2): " + tenFollows(new int[] {1, 2, 20, 5}, 2));
	}
}
