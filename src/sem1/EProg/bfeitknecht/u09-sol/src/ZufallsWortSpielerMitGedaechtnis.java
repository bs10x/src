/**
 * Gleich wie {@link ZufallsWortSpieler}, ausser dass sich dieser Spieler die Wörter merkt, welche er
 * schon getippt hat, und diese nicht nochmals als Tipp abgibt.
 */
public class ZufallsWortSpielerMitGedaechtnis extends ZufallsWortSpieler {

    private boolean[] schonGetippt;

    public void neuesSpiel(String[] verwendeteWoerter) {
        super.neuesSpiel(verwendeteWoerter);
        schonGetippt = new boolean[verwendeteWoerter.length];
    }

    protected int zufallsWortIndex() {
        int index;
        do {
            index = super.zufallsWortIndex();
        } while (schonGetippt[index]);
        schonGetippt[index] = true;
        return index;
    }

    public String name() {
        return super.name() + " mit Gedächtnis";
    }
}