
// Representiert eine Folge von Bruchteilen zusammen mit einer Anzahl an verfuegbaren Sitzen.
interface BruchteilFolge {
	int getVerfuegbareSitze();
	int getLength();
	double getBruchteil(int idx);
	void updateBruchteil(int idx, double neuerBruchteil);
}

class WahlkreisBruchteilFolge implements BruchteilFolge {
	int wahlkreisIdx;
	Unterzuteilung unterzuteilung;
	
	public WahlkreisBruchteilFolge(int wahlkreisIdx, Unterzuteilung unterzuteilung) {
		this.wahlkreisIdx = wahlkreisIdx;
		this.unterzuteilung = unterzuteilung;
	}

	@Override
	public int getVerfuegbareSitze() {
		return unterzuteilung.getWahlDaten().getSitzeProWahlkreis(wahlkreisIdx);
	}

	@Override
	public int getLength() {
		return unterzuteilung.getWahlDaten().getAnzahlParteien();
	}

	@Override
	public double getBruchteil(int parteiIdx) {
		return unterzuteilung.getSitzeBruchteil(wahlkreisIdx, parteiIdx);
	}

	@Override
	public void updateBruchteil(int parteiIdx, double neuerBruchteil) {
		unterzuteilung.setSitzeBruchteil(wahlkreisIdx, parteiIdx, neuerBruchteil);
	}
}

class ParteiBruchteilFolge implements BruchteilFolge {
	int parteiIdx;
	Unterzuteilung unterzuteilung;
	
	public ParteiBruchteilFolge(int parteiIdx, Unterzuteilung unterzuteilung) {
		this.parteiIdx = parteiIdx;
		this.unterzuteilung = unterzuteilung;
	}

	@Override
	public int getVerfuegbareSitze() {
		return unterzuteilung.getSitzeProPartei(parteiIdx);
	}

	@Override
	public int getLength() {
		return unterzuteilung.getWahlDaten().getAnzahlWahlkreise();
	}

	@Override
	public double getBruchteil(int wahlkreisIdx) {
		return unterzuteilung.getSitzeBruchteil(wahlkreisIdx, parteiIdx);
	}

	@Override
	public void updateBruchteil(int wahlkreisIdx, double neuerBruchteil) {
		unterzuteilung.setSitzeBruchteil(wahlkreisIdx, parteiIdx, neuerBruchteil);
	}
}

class ArrayBruchteilFolge implements BruchteilFolge {
	int verfuegbareSitze;
	double[] bruchteile;
	
	public ArrayBruchteilFolge(int verfuegbareSitze, double[] bruchteile) {
		this.verfuegbareSitze = verfuegbareSitze;
		this.bruchteile = bruchteile;
	}
	
	public ArrayBruchteilFolge(BruchteilFolge folge) {
		this.verfuegbareSitze = folge.getVerfuegbareSitze();
		
		int length = folge.getLength();
		this.bruchteile = new double[length];
		for (int i = 0; i < length; i += 1) {
			this.bruchteile[i] = folge.getBruchteil(i);
		}
	}

	@Override
	public int getVerfuegbareSitze() {
		return verfuegbareSitze;
	}

	@Override
	public int getLength() {
		return bruchteile.length;
	}

	@Override
	public double getBruchteil(int idx) {
		return bruchteile[idx];
	}

	@Override
	public void updateBruchteil(int idx, double neuerBruchteil) {
		bruchteile[idx] = neuerBruchteil;
	}
	
	
}

