
class WahlDaten {
	
	// Abgegebene Stimmen der Wahl
	private int[][] stimmen;
	// Verfuegbare Sitze pro Wahlkreis
	private int[] sitzeProWahlkreis;
	
	private int anzahlWahlkreise;
	// Wir nehmen an, dass jeder Wahlkreis die gleiche Anzahl an Parteien hat
	private int anzahlParteien;
	
	public WahlDaten(int[][] stimmen, int[] sitzeProWahlkreis) {
		this.stimmen = stimmen;
		this.sitzeProWahlkreis = sitzeProWahlkreis;
		this.anzahlWahlkreise = stimmen.length;
		this.anzahlParteien = stimmen[0].length; // Es gibt mindestens einen Wahlkreis
	}
	
	// Returned die Stimmen im Wahlkreis mit Index 'wahlkreisIdx' und der Partei mit Index 'parteiIdx'
	public int getStimmen(int wahlkreisIdx, int parteiIdx) {
		return stimmen[wahlkreisIdx][parteiIdx];
	}
	
	// Returned die Anzahl verfuegbarer Sitze im Wahlkreis mit Index 'wahlkreisIdx'
	public int getSitzeProWahlkreis(int wahlkreisIdx) {
		return sitzeProWahlkreis[wahlkreisIdx];
	}
	
	public int getAnzahlWahlkreise() {
		return anzahlWahlkreise;
	}
	
	public int getAnzahlParteien() {
		return anzahlParteien;
	}
}
