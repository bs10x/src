/**
 * Ein künstlicher Spieler, welcher zuerst einzelne vordefinierte Zeichenketten als Tipps abgibt und
 * mit den resultierenden Hinweisen unmöglich gewordene Wörter ausschliesst. Wenn nur noch wenige
 * Wörter übrig sind (oder keine Tipps mehr), gibt er diese nacheinander als Tipp ab.
 * Zu beachten: Diese Klasse sollte (mittels Vererbung) erweitert und die tipps()- und die
 * tippIndex()-Methoden überschrieben werden.
 */
public class TippsSpieler extends Spieler {

    protected String[] woerter;
    protected boolean[] aussortiert;

    protected String[] tipps;
    protected boolean[] tippVerwendet;

    public void neuesSpiel(String[] verwendeteWoerter) {
        woerter = verwendeteWoerter;
        aussortiert = new boolean[woerter.length];
        tipps = tipps(verwendeteWoerter);
        tippVerwendet = new boolean[tipps.length];
    }

    public String gibTipp() {
        int uebrigeWoerter = zaehleFalse(aussortiert);
        int uebrigeTipps = zaehleFalse(tippVerwendet);

        if (uebrigeWoerter > 3 && uebrigeTipps > 0) {
            int index = tippIndex();
            tippVerwendet[index] = true;
            return tipps[index];
        } else {
            int wortIndex = 0;
            while (aussortiert[wortIndex])
                wortIndex++;

            aussortiert[wortIndex] = true;
            return woerter[wortIndex];
        }
    }

    /**
     * Definiert die Tipps, die der Spieler durchprobiert. Sollte überschrieben werden.
     */
    protected String[] tipps(String[] verwendeteWoerter) {
        return new String[]{"Dies ist ein dummer Tipp, weil tipps() nicht überschrieben wurde (oder die Klasse TippsSpieler instanziert wurde)"};
    }

    /**
     * Gibt den Index des nächsten Tipps (einer von den von tipps() zurückgegebenen) zurück. Sollte
     * überschrieben werden.
     */
    protected int tippIndex() {
        return 0;
    }

    /**
     * Gibt die Anzahl "false"-Werte im gegebenen Array zurück.
     */
    protected int zaehleFalse(boolean[] array) {
        int anzahl = 0;
        for (int i = 0; i < array.length; i++)
            if (!array[i])
                anzahl++;
        return anzahl;
    }

    public void bekommeHinweis(String tipp, String hinweis) {
        verarbeiteHinweis(tipp, hinweis, aussortiert);
    }

    protected void verarbeiteHinweis(String tipp, String hinweis, boolean[] dieAussortierten) {
        for (int i = 0; i < woerter.length; i++) {
            if (!dieAussortierten[i]) {
                String wort = woerter[i];
                if (hinweis.equals("beginnt mit und endet mit")) {
                    if (!(wort.startsWith(tipp) && wort.endsWith(tipp)))
                        dieAussortierten[i] = true;
                } else if (hinweis.equals("beginnt mit")) {
                    if (!wort.startsWith(tipp) || wort.endsWith(tipp))
                        dieAussortierten[i] = true;
                } else if (hinweis.equals("endet mit")) {
                    if (!wort.endsWith(tipp) || wort.startsWith(tipp))
                        dieAussortierten[i] = true;
                } else if (hinweis.equals("enthält")) {
                    if (!(wort.contains(tipp) && !wort.startsWith(tipp) && !wort.endsWith(tipp)))
                        dieAussortierten[i] = true;
                } else if (hinweis.equals("enthält nicht")) {
                    if (wort.contains(tipp))
                        dieAussortierten[i] = true;
                }
            }
        }
    }

    public String name() {
        return "Tipps-Spieler (welcher eigentlich nicht instanziert werden sollte...)";
    }
}
