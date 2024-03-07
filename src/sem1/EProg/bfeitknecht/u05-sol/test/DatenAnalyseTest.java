import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class DatenAnalyseTest {
    
	@Test
	public void testLiesGroessen() {
		Scanner scanner = new Scanner("3\n1\n2\n3");
        int[] groessen = DatenAnalyse.liesGroessen(scanner);
        assertEquals(3, groessen.length);
        assertEquals(1, groessen[0]);
        assertEquals(2, groessen[1]);
        assertEquals(3, groessen[2]);
	}

	@Test
	public void testErstelleHistogramm() {
		int[] hist;
		
		// keine Grössen, 1 Klasse
		hist = DatenAnalyse.erstelleHistogramm(new int[]{}, 0, 10, 1);
		assertArrayEquals(new int[]{0}, hist);
		
		// keine Grössen, 10 Klasse
		hist = DatenAnalyse.erstelleHistogramm(new int[]{}, 0, 10, 10);
		assertArrayEquals(new int[10], hist);
		
		// 1 Grösse, 1 Klasse
		hist = DatenAnalyse.erstelleHistogramm(new int[]{0}, 0, 10, 1);
		assertArrayEquals(new int[]{1}, hist);
		
		// 3 Grössen, 3 Klassen
		hist = DatenAnalyse.erstelleHistogramm(new int[]{0, 10, 20}, 0, 30, 3);
		assertArrayEquals(new int[]{1, 1, 1}, hist);
		
		hist = DatenAnalyse.erstelleHistogramm(new int[]{0, 9, 10}, 0, 30, 3);
		assertArrayEquals(new int[]{2, 1, 0}, hist);
		
		// 3 Grössen, 10 Klassen
		hist = DatenAnalyse.erstelleHistogramm(new int[]{0, 15, 29}, 0, 30, 10);
		assertArrayEquals(new int[]{1, 0, 0, 0, 0, 1, 0, 0, 0, 1}, hist);
		
		// 10 Grössen, 3 Klassen
		hist = DatenAnalyse.erstelleHistogramm(new int[]{0, 0, 0, 0, 9, 10, 29, 29, 29, 29}, 0, 30, 3);
		assertArrayEquals(new int[]{5, 1, 4}, hist);
		
	}

	@Test
	public void testKlassenBreite() {
		assertEquals(10, DatenAnalyse.klassenBreite(100, 200, 10));
		assertEquals(11 + 1, DatenAnalyse.klassenBreite(100, 200, 9));
		assertEquals(12 + 1, DatenAnalyse.klassenBreite(100, 200, 8));
		assertEquals(14 + 1, DatenAnalyse.klassenBreite(100, 200, 7));
		assertEquals(16 + 1, DatenAnalyse.klassenBreite(100, 200, 6));
		assertEquals(20, DatenAnalyse.klassenBreite(100, 200, 5));
	}
    
}
