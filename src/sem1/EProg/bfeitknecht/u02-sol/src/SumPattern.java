
public class SumPattern {
	public static void main(String[] args) {
		// Aendern Sie die Werte um verschiedene Ausfuehrungen zu testen.
		int a = 4;
		int b = 10;
		int c = 6;
		
		// TODO: Vervollstaendigen Sie den Code wie in der Aufgabenbeschreibung beschrieben
		
		// Wir testen einfach alle Faelle
		if (a + b == c) {
			System.out.println("Moeglich. " + a + " + " + b + " == " + c);
		} else if (a + c == b) {
			System.out.println("Moeglich. " + a + " + " + c + " == " + b);
		} else if (b + c == a) {
			System.out.println("Moeglich. " + b + " + " + c + " == " + a);
		} else {
			System.out.println("Unmoeglich.");
		}
	}
}
