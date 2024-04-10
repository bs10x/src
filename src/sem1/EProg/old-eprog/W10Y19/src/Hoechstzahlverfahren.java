
// Veraendern Sie diese Datei nicht!

interface HoechstzahlStrategy {
	// Gibt den i-ten Divisor der Hoechstzahl Strategy zurueck
	// Zum Beispiel, fuer die Divisorreihe 4,5,6,7,8,9, ... 
	// ergibt Index 0 gleich 4, Index 1 gleich 5, Index 2 gleich 6 usw.
	double getDivisor(int ith);
}

class Hoechstzahlverfahren {
	
	// returned die Sitze der Parteien fuer die Hoechstzahlstrategy 'strategy'
	// Es benoetigt die totale Anzahl an verfuegbaren Sitzen 'anzahlSitze' und die totalen Stimmen fuer eine Partei 'stimmenPartei'
	static int[] berechneParteiSitze(int anzahlSitze, int[] stimmenParteien, HoechstzahlStrategy strategy) {
		
		int anzahlParteien = stimmenParteien.length;
		int[] sitze = new int[anzahlParteien];
		int[] currentPosition = new int[anzahlParteien];
		double[] currentStimmenBruchteil = new double[anzahlParteien];
		
		for (int i = 0; i < anzahlParteien; i += 1) {
			currentPosition[i] = 0;
			currentStimmenBruchteil[i] = stimmenParteien[i] / strategy.getDivisor(currentPosition[i]);
		}
		
		for (int i = 0; i < anzahlSitze; i += 1) {
			int maxPartei = -1;
			double maxValue = -1;
			
			for (int j = 0; j < anzahlParteien; j += 1) { 
				double parteiJStimmenBruchteil = currentStimmenBruchteil[j];
				if (parteiJStimmenBruchteil >= maxValue) {
					maxValue = parteiJStimmenBruchteil;
					maxPartei = j;
				}
			}
			
			if (maxPartei >= 0) {
				sitze[maxPartei] += 1;
				currentPosition[maxPartei] += 1;
				currentStimmenBruchteil[maxPartei] = stimmenParteien[maxPartei] / strategy.getDivisor(currentPosition[maxPartei]);
			}
		}
		return sitze;
	}
}
