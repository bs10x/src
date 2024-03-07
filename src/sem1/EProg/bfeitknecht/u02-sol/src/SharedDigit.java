
public class SharedDigit {
	public static void main(String[] args) {
		// Aendern Sie die Werte um verschiedene Ausfuehrungen zu testen.
		int a = 12;
		int b = 23;
		
		// TODO: Vervollstaendigen Sie den Code wie in der Aufgabenbeschreibung beschrieben
		
		int r = -1;
		if (a/10 == b/10 || a/10 == b%10) { // prueft ob die erste Ziffer von a mit einer der beiden Ziffern von b uebereinstimmt
			r = a/10;
		} else if (a%10 == b/10 || a%10 == b%10) {  // prueft ob die zweite Ziffer von a mit einer der beiden Ziffern von b uebereinstimmt
			r = a%10;
		}
		
		// Der finale Wert von r wird ausgegeben
		System.out.println("a: " + a + ", b: " + b + " --> r: " + r);
	}
}
