import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ZufallsWortSpielerMitGedaechtnisTest {

    @Test
    public void testTippIndex() {
        for (int n = 1; n < 100; n++) {
            ZufallsWortSpieler spieler = new ZufallsWortSpielerMitGedaechtnis();
            spieler.neuesSpiel(new String[n]);

            boolean[] getippt = new boolean[n];
            for (int j = 0; j < n; j++) {
                int index = spieler.zufallsWortIndex();
                assertFalse(getippt[index]);
                getippt[index] = true;
            }
        }
    }

    @Test
    public void testGibTipp() {
        String[] woerter = {"hallo", "eprog", "welt"};

        ZufallsWortSpieler spieler = new ZufallsWortSpielerMitGedaechtnis();
        spieler.neuesSpiel(woerter);

        LinkedStringList getippt = new LinkedStringList();
        for (int i = 0; i < 3; i++)
            getippt.addLast(spieler.gibTipp());

        assertTrue(!getippt.get(0).equals(getippt.get(1)) && !getippt.get(0).equals(getippt.get(2))
            && !getippt.get(1).equals(getippt.get(2)));
        for (StringNode n = getippt.first; n != null; n = n.next)
            assertTrue(n.value.equals(woerter[0]) || n.value.equals(woerter[1])
                || n.value.equals(woerter[2]));
    }
}