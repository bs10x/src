/**
 * Ein Ratespiel, wo ein {@link Spieler} ein vom {@link Computer} zufällig gewähltes Wort erraten
 * muss.
 */
public class RateSpiel {

    private String[] woerter;
    private Computer computer;
    private Spieler[] spieler;

    private int outputNtesSpiel;
    private boolean outputHinweiseUndTipps;

    public RateSpiel(String[] woerter, Computer computer, Spieler[] spieler,
                     int outputNtesSpiel, boolean outputHinweiseUndTipps) {
        this.woerter = woerter;
        this.computer = computer;
        this.spieler = spieler;
        this.outputNtesSpiel = outputNtesSpiel;
        this.outputHinweiseUndTipps = outputHinweiseUndTipps;
    }

    /**
     * Führt das Ratespiel einmal durch. Jeder Spieler tritt einzeln gegen den Computer an. Gibt die
     * Anzahl benötigter Versuche für jeden Spieler zurück.
     */
    public int[] einSpiel() {
        computer.neuesSpiel(woerter);
        int[] versuche = new int[spieler.length];

        for (int s = 0; s < spieler.length; s++) {
            Spieler derSpieler = spieler[s];
            derSpieler.neuesSpiel(woerter.clone());

            String hinweis;
            do {
                String tipp = derSpieler.gibTipp();
                hinweis = computer.gibHinweis(tipp);
                if (outputHinweiseUndTipps)
                    System.out.println("Tipp: \"" + tipp + "\", Hinweis: \"" + hinweis + "\"");
                derSpieler.bekommeHinweis(tipp, hinweis);
                versuche[s]++;
            } while (!hinweis.equals("ist"));
        }
        return versuche;
    }

    /**
     * Führt das Ratespiel n-mal durch. Gibt die durchschnittliche Anzahl benötigter Versuche für
     * jeden Spieler zurück.
     */
    public double[] nSpiele(int n) {
        double[] sum = new double[spieler.length];
        for (int i = 1; i <= n; i++) {
            if (i % outputNtesSpiel == 0)
                System.out.println("Spiel " + i);

            int[] versuche = einSpiel();

            for (int s = 0; s < spieler.length; s++)
                sum[s] += versuche[s];
        }

        for (int s = 0; s < spieler.length; s++)
            sum[s] /= n;
        return sum;
    }
}
