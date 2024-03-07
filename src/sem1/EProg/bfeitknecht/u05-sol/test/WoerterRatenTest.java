import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class WoerterRatenTest {
    
    @Test
    public void testLiesWoerter() {
        Scanner scanner = new Scanner("3\nhallo\neprog\nwelt");
        String[] woerter = WoerterRaten.liesWoerter(scanner);
        assertEquals(3, woerter.length);
        assertEquals("hallo", woerter[0]);
        assertEquals("eprog", woerter[1]);
        assertEquals("welt", woerter[2]);
    }
    
    @Test
    public void testZufallsWort() {
        String[] woerter = new String[]{"hallo", "eprog", "welt"};
        for(int i = 0; i < 100; i++) {
            String wort = WoerterRaten.zufallsWort(woerter);
            assertTrue(wort.equals(woerter[0]) || wort.equals(woerter[1]) || wort.equals(
                    woerter[2]));
        }
    }
    
    @Test
    public void testHinweis() {
        assertEquals("ist", WoerterRaten.hinweis("hallo", new String("hallo")));
        assertEquals("beginnt mit und endet mit", WoerterRaten.hinweis("halloh", "h"));
        assertEquals("beginnt mit und endet mit", WoerterRaten.hinweis("hallohoh", "h"));
        assertEquals("beginnt mit", WoerterRaten.hinweis("hallo", "h"));
        assertEquals("endet mit", WoerterRaten.hinweis("alloh", "h"));
        assertEquals("enthält", WoerterRaten.hinweis("oha!", "h"));
        assertEquals("enthält nicht", WoerterRaten.hinweis("bla", "h"));
        
        assertEquals("beginnt mit und endet mit", WoerterRaten.hinweis("susosu", "su"));
        assertEquals("beginnt mit und endet mit", WoerterRaten.hinweis("sususu", "su"));
        assertEquals("beginnt mit", WoerterRaten.hinweis("hallo", "ha"));
        assertEquals("endet mit", WoerterRaten.hinweis("alloh", "oh"));
        assertEquals("enthält", WoerterRaten.hinweis("ohaha!", "hah"));
        assertEquals("enthält nicht", WoerterRaten.hinweis("ja", "nein"));
    }
}
