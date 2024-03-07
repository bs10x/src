/**
 * Ein {@link TippsSpieler}, welcher in jeder Runde berechnet, welcher der Tipps wahrscheinlich am
 * wertvollsten ist, d.h., die Anzahl noch möglicher Wörter wahrscheinlich am meisten reduziert.
 * Dazu simuliert er für jeden möglichen Tipp das Verhalten des Computers, indem er ihn mehrmals ein
 * Spiel starten lässt (bei dem der Computer ein Wort auswählt) und den aus dem Tipp resultierenden
 * Hinweis registriert. Damit berechnet der Spieler die Wahrscheinlichkeit für jeden möglichen
 * Hinweis (für diesen Tipp) und kann dadurch den Erwartungswert der übrig gebliebenen Wörter (für
 * diesen Tipp) berechnen. Schliesslich wählt er den Tipp, welcher diesen Erwartungswert minimiert.
 * Zu beachten:
 * Diese Klasse sollte (mittels Vererbung) erweitert und die tipps()-Methode überschrieben werden.
 * Die tippIndex()-Methode wird von dieser Klasse implementiert und braucht nicht überschrieben
 * werden.
 */
public class SimulierenderTippsSpieler extends TippsSpieler {

    private static String[] hinweise = {"beginnt mit und endet mit", "beginnt mit",
        "endet mit", "enthält", "enthält nicht"};

    private Computer computer;

    public SimulierenderTippsSpieler() {
        this.computer = new Computer();
    }

    protected int tippIndex() {
        double minDurchschUebrigeWoerter = Double.POSITIVE_INFINITY;
        int indexIdealerTipp = -1;
        for (int t = 0; t < tipps.length; t++) {
            if (!tippVerwendet[t]) {
                double durchschnUebrigeWoerter = simuliereTipp(tipps[t]);
                if (durchschnUebrigeWoerter < minDurchschUebrigeWoerter) {
                    minDurchschUebrigeWoerter = durchschnUebrigeWoerter;
                    indexIdealerTipp = t;
                }
            }
        }

        if (indexIdealerTipp == -1) {
            System.out.println("Keinen Tipp-Index gefunden");
            System.exit(-1);
        }
        return indexIdealerTipp;
    }

    private double simuliereTipp(String tipp) {
        // Berechne Wahrscheinlichkeit für jeden Hinweis
        double[] hinweisWahrschkeiten = hinweisWahrschkeiten(tipp);

        // Berechne durchschnittliche Anzahl Wörter, die nach diesem Tipp übrig bleiben.
        // Durchschnitt ist nach Wahrscheinlichkeiten der Hinweise gewichtet
        double durchschUebrigeWoerter = 0;
        for (int h = 0; h < hinweise.length; h++) {
            boolean[] tempAussortiert = aussortiert.clone();
            verarbeiteHinweis(tipp, hinweise[h], tempAussortiert);
            durchschUebrigeWoerter += zaehleFalse(tempAussortiert) * hinweisWahrschkeiten[h];
        }

        return durchschUebrigeWoerter;
    }

    private double[] hinweisWahrschkeiten(String tipp) {
        double[] wahrschkeiten = new double[hinweise.length];
        int samples = Math.max(2 * zaehleFalse(aussortiert), 10);
        for (int i = 0; i < samples; i++) {
            // Simuliere Computer, und verwerfe Resultat, falls Wort schon aussortiert ist
            int wortIndex;
            do {
                computer.neuesSpiel(woerter);
                wortIndex = computer.verrateWortIndex();
            } while (aussortiert[wortIndex]);

            String hinweis = computer.gibHinweis(tipp);
            for (int h = 0; h < hinweise.length; h++)
                if (hinweise[h].equals(hinweis))
                    wahrschkeiten[h]++;
        }

        for (int h = 0; h < hinweise.length; h++)
            wahrschkeiten[h] /= samples;

        return wahrschkeiten;
    }

    public String name() {
        return "Simulierender " + super.name();
    }
}
