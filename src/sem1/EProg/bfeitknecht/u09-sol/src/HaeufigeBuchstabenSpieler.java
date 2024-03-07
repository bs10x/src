/**
 * Ein {@link TippsSpieler}, welcher alle Buchstaben von a bis z (inklusive ä, ö, ü) als Tipps
 * verwendet, aber nach der Häufigkeit sortiert, mit welcher diese in den im Spiel verwendeten
 * Wörtern vorkommen.
 */
public class HaeufigeBuchstabenSpieler extends BuchstabenSpieler {

    private int[] haeufigkeiten;

    public void neuesSpiel(String[] verwendeteWoerter) {
        super.neuesSpiel(verwendeteWoerter);

        haeufigkeiten = new int[buchstaben.length];
        for (int w = 0; w < verwendeteWoerter.length; w++)
            for (int i = 0; i < verwendeteWoerter[w].length(); i++) {
                String buchstabe = verwendeteWoerter[w].substring(i, i + 1);
                int index = buchstabenIndex(buchstabe);
                if (index > 0)
                    haeufigkeiten[index]++;
            }
    }

    private int buchstabenIndex(String buchstabe) {
        for (int i = 0; i < buchstaben.length; i++)
            if (buchstaben[i].equals(buchstabe))
                return i;
        return -1;
    }

    protected int tippIndex() {
        int max = -1;
        int maxIndex = -1;
        for (int i = 0; i < haeufigkeiten.length; i++) {
            if (!tippVerwendet[i] && haeufigkeiten[i] > max) {
                max = haeufigkeiten[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public String name() {
        return "Häufige-Buchstaben-Spieler";
    }
}
