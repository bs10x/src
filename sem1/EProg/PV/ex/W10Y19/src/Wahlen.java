


public class Wahlen {

	public static void main(String[] args) {
		int[] sitzeProWahlkreis = {2,4,5};
		int[][] stimmen = {
				{35, 33, 32},
				{50, 20, 80},
				{40, 80, 90}
		};
		SitzzuteilungStrategy verteilung = new SitzzuteilungStrategyFactory().getDHondt();
		RundungStrategy runder = new RundungStrategyFactory().getHalfUpRunder();
		
		int[][] sitze = wahlauswertung(stimmen, sitzeProWahlkreis, verteilung, runder);
		
		for(int i = 0; i < sitze.length; i += 1) {
			for(int j = 0; j < sitze[i].length; j += 1) {
				System.out.print(sitze[i][j] + " ");
			}
			System.out.println();
		}
	}	

	// Berechnet die Wahlauswertung der Wahldaten. 
	// 'stimmen[wahlkreisIdx][parteiIdx]' enthaelt die Stimmen, welche im Wahlkreis mit dem Index 'wahlkreisIdx' 
	// fuer die Partei mit dem Index 'parteiIdx' gezaehlt wurden.
	// 'sitzeProWahlkreis[wahlkreisIdx]' enthaelt die verfuegbaren Sitze fuer den Wahlkreis mit dem Index 'wahlkreisIdx'
	// Die ausgewerteten Sitze werden im gleichen Format wie die Stimmen zurueck gegeben.
	public static int[][] wahlauswertung(int[][] stimmen, int[] sitzeProWahlkreis, SitzzuteilungStrategy verteilung, RundungStrategy runder) {
		// Veraendern Sie diesen Code nicht
		// Wir werden diesen Code verwenden, um Ihre Submission zu bewerten
		WahlDaten daten = new WahlDaten(stimmen, sitzeProWahlkreis);
		Oberzuteilung oberzuteilung = new Oberzuteilung(daten, verteilung);
		Unterzuteilung unterzuteilung = new Unterzuteilung(oberzuteilung, runder);
		return unterzuteilung.getSitze();
	}
}





























