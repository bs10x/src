import java.util.Random;

/**
 * Ein künstlicher Spieler, welcher sich die Liste der möglichen Wörter merkt und in jeder Runde per
 * Zufalls eines davon als Tipp abgibt.
 */
public class ZufallsWortSpieler extends Spieler {

    protected Random random = new Random();
    protected String[] woerter;

    public void neuesSpiel(String[] verwendeteWoerter) {
        this.woerter = verwendeteWoerter;
    }

    public String gibTipp() {
        int index = zufallsWortIndex();
        return woerter[index];
    }

    protected int zufallsWortIndex() {
        return random.nextInt(woerter.length);
    }

    public String name() {
        return "Zufalls-Wort-Spieler";
    }
}
