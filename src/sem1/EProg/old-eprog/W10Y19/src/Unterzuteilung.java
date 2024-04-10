
class Unterzuteilung implements Zuteilung {
	
	private WahlDaten daten;
	private int[] sitzeProPartei;
	private boolean istFertig;
	private boolean keineLoesungGefunden;
	
	private double[][] sitzeBruchteil;
	
	private RundungStrategy runder;
	
	public Unterzuteilung(WahlDaten daten, int[] sitzeProPartei, RundungStrategy runder) {
		this.daten = daten;
		this.sitzeProPartei = sitzeProPartei;
		this.runder = runder;
		
		this.sitzeBruchteil = null;
		this.istFertig = false;
		this.keineLoesungGefunden = false;
	}
	
	public Unterzuteilung(Oberzuteilung oberzuteilung, RundungStrategy runder) {
		this(oberzuteilung.getWahlDaten(), oberzuteilung.getSitzeProPartei(), runder);
	}
	
	public WahlDaten getWahlDaten() {
		return daten;
	}
	
	public int getSitzeProPartei(int parteiIdx) {
		return sitzeProPartei[parteiIdx];
	}
	
	public double getSitzeBruchteil(int wahlkreisIdx, int parteiIdx) {
		return sitzeBruchteil[wahlkreisIdx][parteiIdx];
	}
	
	public void setSitzeBruchteil(int wahlkreisIdx, int parteiIdx, double neuerBruchteil) {
		if (istKorrekt()) {
			throw new IllegalStateException("Bruchteile duerfen nicht modifiziert werden, nachdem die Zuteilung fertig ist.");
		}
		sitzeBruchteil[wahlkreisIdx][parteiIdx] = neuerBruchteil;
	}
	
	// Returned die Unterzuteilung, heisst die zugeteilten Sitze pro Wahlkreis und Partei
    public int[][] getSitze() {
    	
    	if (!istKorrekt()) {
			anpassen();
		}
    	
    	if (keineLoesungGefunden) {
    		return null;
    	}
    	
		int anzahlWahlkreise = daten.getAnzahlWahlkreise();
		int anzahlParteien = daten.getAnzahlParteien();
		
		int[][] sitze = new int[anzahlWahlkreise][anzahlParteien];
		
		for (int i = 0; i < anzahlWahlkreise; i += 1) {
			for (int j = 0; j < anzahlParteien; j += 1) {
				// TODO: Schreiben Sie int 'sitze[i][j]' den gerundeten Wert von 'sitzeBruchteil[i][j]'
				sitze[i][j] = 0;
			}
		}
		
		return sitze;
	}
    
    @Override
    public boolean istKorrekt() {
    	return istFertig;
    }
    
    @Override 
    public void anpassen() {
    	if (!istKorrekt()) {
    		int anzahlWahlkreise = daten.getAnzahlWahlkreise();
    		int anzahlParteien = daten.getAnzahlParteien();
    		
    		sitzeBruchteil = new double[anzahlWahlkreise][anzahlParteien];
    		
    		// Die Bruchteile der Sitze werden initialisiert mit den Stimmen 
    		for (int i = 0; i < anzahlWahlkreise; i += 1) {
    			for (int j = 0; j < anzahlParteien; j += 1) {
    				sitzeBruchteil[i][j] = daten.getStimmen(i, j);
    			}
    		}
    		
    		// Wir abstrahieren ueber die Reihen und Spalten des Bruchteile Arrays als Liste von Folgen, 
    		// wobei jede Folge seine eigene Zuteilung hat.
    		// Sobald alle diese Folgen korrekt zugeteilt wurden, wissen wir, 
    		// dass die gesamte Unterzuteilung doppel-proportional zu Parteien und Wahlkreisen ist
    		Zuteilung[] folgenUnterzuteilungen = new Zuteilung[anzahlWahlkreise + anzahlParteien];
    		for (int i = 0; i < anzahlWahlkreise; i += 1) {
    			BruchteilFolge folge = new WahlkreisBruchteilFolge(i, this);
    			folgenUnterzuteilungen[i] = new FolgeUnterzuteilung(folge, runder);
    		}
    		for (int j = 0; j < anzahlParteien; j += 1) {
    			BruchteilFolge folge = new ParteiBruchteilFolge(j, this);
    			folgenUnterzuteilungen[anzahlWahlkreise + j] = new FolgeUnterzuteilung(folge, runder);
    		}
    		
    		int anzahlIterationen = 0;
    		int maximaleAnzahlIterationen = 1000;
    		
    		// Solange noch nicht alle Folgen korrekt sind, heisst die Anzahl zugeteilter Sitze noch nicht der Zahl verfuegbarer Sitze entspricht,
    		// passen wir die Folgen weiter an.
    		boolean istAllesKorrekt;
    		do {
    			istAllesKorrekt = true;
    			
    			for (int i = 0; i < folgenUnterzuteilungen.length; i += 1) {
    				
    				if (!folgenUnterzuteilungen[i].istKorrekt()) {
    					istAllesKorrekt = false;
    					folgenUnterzuteilungen[i].anpassen();
    				}
    			}
    			
    			anzahlIterationen += 1;
    		} while(!istAllesKorrekt && anzahlIterationen < maximaleAnzahlIterationen);
    		istFertig = true;
    		
    		if (anzahlIterationen == maximaleAnzahlIterationen) {
    			keineLoesungGefunden = true;
    		}
    	}
    }
}



// Representiert die Unterzuteilung einer einzelnen Zeile oder Reihe der gesamten Unterzuteilung
class FolgeUnterzuteilung implements Zuteilung {
	
	BruchteilFolge folge;
	RundungStrategy runder;
	
	public FolgeUnterzuteilung(BruchteilFolge folge, RundungStrategy runder) {
		this.folge = folge;
		this.runder = runder;
	}
	
	// Returned ob die Zuteilung korrekt ist.
	public boolean istKorrekt() {
		int summe = summeDerSitze();
		// Eine Zuteilung ist korrekt, wenn die Summe der Sitze gleich der Anzahl verfuegbarer Sitze ist.
		return summe == folge.getVerfuegbareSitze();
	}
	
	// Falls die Zuteilung noch nicht Fertig ist, dann wird die Zuteilung so angepasst, 
	// dass danach Summe der Sitze gleich der Anzahl der verfuegbaren Sitze entspricht
	public void anpassen() {
		if (!istKorrekt()) {
			// TODO Berechnen Sie einen neuen Divisor, durch welchen die Summe der Sitze gleich der Anzahl der verfuegbaren Sitze entspricht
			// Tipp: Verwenden Sie 'DivisorSearch.search' 
			SearchCheck check = null;
			
			double divisor = DivisorSearch.search(check);
			dividiere(divisor);
		}
	}
	
	// Returned die Sitze, welcher dem gerundeten Bruchteil aus der Folge an Index 'idx' entspricht.
	int getSitze(int idx) {
		// TODO
		return 0;
	}
	
	// Berechnet die Summe der Sitze
	int summeDerSitze() {
		int summe = 0;
		int length = folge.getLength();
		for (int i = 0; i < length; i += 1) {
			summe = SafeMath.safeAdd(summe, getSitze(i));
		}
		return summe;
	}
	
	// Gibt eine unabhaengige Kopie der Folgen Unterzuteilung
	FolgeUnterzuteilung deepcopy() {
		BruchteilFolge kopierteFolge = new ArrayBruchteilFolge(folge);
        FolgeUnterzuteilung kopierteZuteilung = new FolgeUnterzuteilung(kopierteFolge, runder);
        return kopierteZuteilung;
	}
	
	// Gibt eine neue Folge zurueck, in welcher die Bruchteile der Sitze durch 'divisor' geteilt sind.
	void dividiere(double divisor) {
		int length = folge.getLength();
		for (int i = 0; i < length; i += 1) {
			folge.updateBruchteil(i, folge.getBruchteil(i) / divisor);
		}
	}
}