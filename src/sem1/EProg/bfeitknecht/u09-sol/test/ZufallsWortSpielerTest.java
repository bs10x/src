import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ZufallsWortSpielerTest {

    @Test
    public void testTippIndex() {
        for (int n = 1; n < 100; n++) {
            ZufallsWortSpieler spieler = new ZufallsWortSpieler();
            spieler.neuesSpiel(new String[n]);

            for (int i = 0; i < 100; i++) {
                int index = spieler.zufallsWortIndex();
                assertTrue(0 <= index && index < n);
            }
        }
    }

    @Test
    public void testGibTipp() {
        String[] woerter = {"hallo", "eprog", "welt"};

        ZufallsWortSpieler spieler = new ZufallsWortSpieler();
        spieler.neuesSpiel(woerter);

        for (int i = 0; i < 100; i++) {
            String tipp = spieler.gibTipp();
            assertTrue(tipp.equals(woerter[0]) || tipp.equals(woerter[1])
                || tipp.equals(woerter[2]));
        }
    }
}
