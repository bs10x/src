
interface Zuteilung {
	// Returned ob die Zuteilung korrekt ist
	boolean istKorrekt();
	// Passt die Zuteilung an, falls die Zuteilung noch nicht korrekt ist
	void anpassen();
}
