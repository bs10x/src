
public class AbsoluteMax {
	
	public static void main(String[] args) {
		// Aendern Sie die Werte um verschiedene Ausfuehrungen zu testen.
		int a = 4;
		int b = -5;
		int c = 3;
		
		// TODO: Vervollstaendigen Sie den Code wie in der Aufgabenbeschreibung beschrieben
		
		// zuerst berechnen wir die absoluten Werte von a, b, und c
		int absoluteA = a;
		if (absoluteA < -a) {
			absoluteA = -a;
		}
		
		int absoluteB = b;
		if (absoluteB < -b) {
			absoluteB = -b;
		}
		
		int absoluteC = c;
		if (absoluteC < -c) {
			absoluteC = -c;
		}
		
		// dann speichern wir das maximum der absoluten Werte in r
		int r = absoluteA;
		if (absoluteB >=  absoluteA && absoluteB >= absoluteC) {
			r = absoluteB;
		} else if (absoluteC >=  absoluteA && absoluteC >= absoluteB) {
			r = absoluteC;
		} 		
		
		// Der finale Wert von r wird ausgegeben
		System.out.println("a: " + a + ", b: " + b + ", c: " + c + " --> r: " + r);
	}
}
