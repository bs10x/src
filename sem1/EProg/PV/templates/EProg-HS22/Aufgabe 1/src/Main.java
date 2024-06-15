
public class Main {
	
	public static void main(String[] args) {
		// Sie koennen die main Methode beliebig aendern.

		int[][] origin = { 
				{ 1, 2, 1, 3, 1},
				{ 3, 1, 2, 1, 2},
				{ 1, 3, 1, 3, 1},
				{ 3, 1, 3, 1, 3},
				{ 1, 3, 1, 3, 1}
		};

		int[][] muster = { 
			{2, 1},
			{1, 2}
		};

		MatchRecord r; 

		r = Pattern.match(origin, muster);
		
		System.out.println("Ursprungsposition: (" + r.x + "," + r.y + ")");
		System.out.println("Anzahl Korrekturschritte: " + r.count);
	}

}
