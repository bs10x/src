public class Pattern {


    public static MatchRecord match(int[][] origin, int[][] muster) {
        int n = origin.length;
        int k = muster.length;
        int minCorrections = Integer.MAX_VALUE;
        int startX = 0;
        int startY = 0;

        for (int x = 0; x <= n - k; x++) {
            for (int y = 0; y <= n - k; y++) {
                int corrections = countCorrections(origin, muster, x, y);
                if (corrections < minCorrections) {
                    minCorrections = corrections;
                    startX = x;
                    startY = y;
                }
            }
        }

        return new MatchRecord(startX, startY, minCorrections);
    }

    // Hilfsfunktion, um die Anzahl der Korrekturen zu zählen
    private static int countCorrections(int[][] origin, int[][] muster, int x, int y) {
        int corrections = 0;

        for (int i = 0; i < muster.length; i++) {
            for (int j = 0; j < muster[i].length; j++) {
                if (muster[i][j] != origin[x + i][y + j]) {
                    corrections++;
                }
            }
        }

        return corrections;
    }
	
	public static void main(String[] args) {

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


