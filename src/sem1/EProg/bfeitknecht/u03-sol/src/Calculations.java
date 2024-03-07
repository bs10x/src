
public class Calculations {
	
	public static int checksum(int x) {
		// TODO: Vervollstaendigen Sie den Code wie in der Aufgabenbeschreibung beschrieben
		
		if (x < 10) { // Recursion Base Case falls das Argument aus nur einer Ziffer besteht.
			return x;
		} else { // Ansonsten ist die Quersumme gleich die Summe der letzten Ziffer plus die Quersumme der restlichen Ziffern.
			return x%10 + checksum(x/10);
		}
	}
}
