
class Oberzuteilung implements Zuteilung {
	
	private WahlDaten daten;
	private int[] sitzeProPartei;
	private boolean istFertig;
	
	private SitzzuteilungStrategy verteilung;
	
	public Oberzuteilung(WahlDaten daten, SitzzuteilungStrategy verteilung) {
		this.daten = daten;
		this.verteilung = verteilung;
		this.sitzeProPartei = null;
		this.istFertig = false;
	}
	
	// Returned die Oberzuteilung, heisst die verfuegbaren Sitze pro Partei
	public int[] getSitzeProPartei() {
		if (!istKorrekt()) {
			anpassen();
		}
		return sitzeProPartei;
	}
	
	public WahlDaten getWahlDaten() {
		return daten;
	}

	@Override
	public boolean istKorrekt() {
		return istFertig;
	}
	
	@Override
	public void anpassen() {
		if (!istKorrekt()) {
			// TODO: Berechnen Sie die Sitze pro Partei
			// Tipp: Verwenden Sie 'Hoechstzahlverfahren.berechneParteiSitze' 
			sitzeProPartei = null;
		}
	}
}
