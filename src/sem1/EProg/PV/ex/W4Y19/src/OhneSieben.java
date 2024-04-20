
public class OhneSieben {

	public static void main(String[] args) {
		int zahl; int[] resultat;
		
		// Sie koennen den Inhalt dieser Methode ergaenzen
		
		// Test 1
		zahl = 173;
		resultat = ohneSieben(zahl);
		if (richtigesResultat(resultat, zahl)) {
			System.out.println("Richtiges Ergebnis (Es wird nicht geprueft, dass die Summanden keine 7 enthalten)");
		} else {
			System.out.println("Falsches Ergebnis");
		}
		
		// Test 2
		zahl = 986;
		resultat = ohneSieben(zahl);
		if (richtigesResultat(resultat, zahl)) {
			System.out.println("Richtiges Ergebnis (Es wird nicht geprueft, dass die Summanden keine 7 enthalten)");
		} else {
			System.out.println("Falsches Ergebnis");
		}
		
		// Test 3
		zahl = 73974;
		resultat = ohneSieben(zahl);
		if (richtigesResultat(resultat, zahl)) {
			System.out.println("Richtiges Ergebnis (Es wird nicht geprueft, dass die Summanden keine 7 enthalten)");
		} else {
			System.out.println("Falsches Ergebnis");
		}
	}
	
	public static int[] ohneSieben(int zahl) {
		// Schreiben Sie die Summanden s1 und s2 mit s1 >= s2 jeweilig in resultat[0] und resultat[1]
		// Beachten Sie, dass die Summanden keine 7 enthalten duerfen
		int[] resultat = new int[2];
		
		// Ihr Code kommt hier hin	
		
		return resultat;
	}
	
	/** Ueberprueft ob resultat eine korrekte Zerlegung von zahl enthaelt, wobei es nicht prueft, dass die Summanden keine 7 enthalten. */
	public static boolean richtigesResultat(int[] resultat, int zahl) {
		// Sie koennen den Inhalt dieser Methode ergaenzen
		boolean richtigesResultatFormat = resultat != null && resultat.length == 2 && resultat[0] >= resultat[1];
		return  richtigesResultatFormat && resultat[0] + resultat[1] == zahl;
	}

}

