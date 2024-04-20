

public class EnthaltenMitAbstand {

	public static void main(String[] args) {
		
		// Sie koennen die main Methode beliebig aendern
		
		String s = "cbbbbba";
		String t = "acb";
		int abstand = 1;
		
		boolean istEnthalten = enthalten(s, t, abstand);

		System.out.println(istEnthalten);
	}

	public static boolean enthalten(String s, String t, int abstand) {
		// Hier kommt Ihre Loesung hin
		
		// Returned true genau dann wenn die Characters aus 't' 
		// als Subsequence in 's' mit Abstand 'abstand' enthalten sind
			
		return true;
	}
	
}

