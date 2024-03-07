/**
 * Ein {@link TippsSpieler}, welcher alle Buchstaben von a bis z (inklusive ä, ö, ü) als Tipps
 * verwendet und alphabetisch sortiert ausprobiert.
 */
public class BuchstabenSpieler extends TippsSpieler {

    protected String[] buchstaben = {"a", "ä", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
        "l", "m", "n", "o", "ö", "p", "q", "r", "s", "t", "u", "ü", "v", "w", "x", "y", "z"};

    protected String[] tipps(String[] verwendeteWoerter) {
        return buchstaben;
    }

    protected int tippIndex() {
        // Gib einfach den ersten Tipp zurück, der noch nicht verwendet wurde
        for (int i = 0; i < tipps.length; i++)
            if (!tippVerwendet[i])
                return i;

        // Sollte nicht passieren:
        System.out.println("Keinen Tipp-Index gefunden");
        System.exit(-1);
        return 0;
    }

    public String name() {
        return "Buchstaben-Spieler";
    }
}