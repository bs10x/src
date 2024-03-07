import java.util.Random;

/**
 * Der Computer im {@link RateSpiel}. Der Computer wählt zu Beginn eines Spiels ein Wort aus,
 * welches ein {@link Spieler} erraten muss. Der Computer gibt mit gibHinweis() einen Hinweis zu
 * einem Tipp ab.
 */
public class Computer {

    private Random random = new Random();
    private String[] woerter;
    private int wortIndex;

    /**
     * Wird aufgerufen, wenn ein neues Spiel beginnt. Die Liste der Wörter, welche in diesem Spiel
     * verwendet wird, wird als Array übergeben. Der Computer wählt von dieser Liste ein Wort aus
     * und merkt sich dieses bis zum Ende des Spiels.
     */
    public void neuesSpiel(String[] verwendeteWoerter) {
        woerter = verwendeteWoerter;
        wortIndex = random.nextInt(verwendeteWoerter.length);
    }

    /**
     * Vergleicht das gewählte Wort und die "tipp"-Zeichenkette und gibt einen Hinweis zurück.
     * Folgende Hinweise sind möglich: "ist", "beginnt mit", "endet mit", "beginnt mit und endet
     * mit", "enthält" oder "enthält nicht".
     */
    public String gibHinweis(String tipp) {
        String wort = woerter[wortIndex];
        if (wort.equals(tipp))
            return "ist";
        else if (wort.startsWith(tipp) || wort.endsWith(tipp)) {
            String hinweis = "";
            if (wort.startsWith(tipp))
                hinweis += "beginnt mit";
            if (wort.endsWith(tipp)) {
                if (!hinweis.equals(""))
                    hinweis += " und ";
                hinweis += "endet mit";
            }
            return hinweis;
        } else if (wort.contains(tipp))
            return "enthält";
        else
            return "enthält nicht";
    }

    /**
     * Gibt das gewählte Wort (bzw. den Index darauf) zurück. Diese Methode kann von einem Spieler
     * verwendet werden, welcher den Computer simuliert.
     */
    public int verrateWortIndex() {
        return wortIndex;
    }
}
