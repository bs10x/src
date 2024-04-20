import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class WahlenTest {
	
	@Test
	public void testRundungen() {
		RundungStrategy halfUpRunder = new RundungStrategyFactory().getHalfUpRunder();
		RundungStrategy downRunder = new RundungStrategyFactory().getDownRunder();
		
		assertEquals(2, round(1.5, halfUpRunder));
		assertEquals(2, round(1.6, halfUpRunder));
		assertEquals(1, round(1.4, halfUpRunder));
		
		assertEquals(1, round(1.6, downRunder));
		assertEquals(1, round(1.4, downRunder));
		
		assertEquals(-1, round(-1.5, halfUpRunder));
		assertEquals(-2, round(-1.6, halfUpRunder));
		assertEquals(-1, round(-1.4, halfUpRunder));
		
		assertEquals(-2, round(-1.6, downRunder));
		assertEquals(-2, round(-1.4, downRunder));
	}
	
	@Test
	public void testVonWikipedia() {
		
		int[] sitzeProWahlkreis = {4,5,6};
		int[][] stimmen = {
				{5100, 6000, 6300},
				{9800, 10000, 10200},
				{4500, 12000, 14400}
		};
		SitzzuteilungStrategy verteilung = new SitzzuteilungStrategyFactory().getSainteLague();
		RundungStrategy runder = new RundungStrategyFactory().getHalfUpRunder();
		
		int[][] sitze = Wahlen.wahlauswertung(stimmen, sitzeProWahlkreis, verteilung, runder);
		
		int[][] expectedSitze = {
				{1, 1, 2},
				{2, 2, 1},
				{1, 2, 3}
		};
		
		assertArrayEquals(expectedSitze, sitze);
	}
	
	@Test
	public void testOberZuteilungMitUnterschiedlicherStrategy() {
		
		int[] sitzeProWahlkreis = {4,10,12};
		int[][] stimmen = {
				{5100, 6000, 6300},
				{9800, 10000, 10200},
				{4500, 12000, 14400}
		};
		SitzzuteilungStrategy sainteVerteilung = new SitzzuteilungStrategyFactory().getSainteLague();
		
		int[] saintSitze = oberzuteilung(stimmen, sitzeProWahlkreis, sainteVerteilung);
		int[] expectedSaintSitze = { 7, 9, 10 };
		
		assertArrayEquals(expectedSaintSitze, saintSitze);
		
		SitzzuteilungStrategy dhondtVerteilung = new SitzzuteilungStrategyFactory().getDHondt();
		
		int[] dhondtSitze = oberzuteilung(stimmen, sitzeProWahlkreis, dhondtVerteilung);
		int[] expectedDhondtSitze = { 6, 9, 11 };
		
		assertArrayEquals(expectedDhondtSitze, dhondtSitze);
	}
	
	@Test
	public void testUnterzuteilungMitUnterschiedlicherRundung() {
		
		int[] sitzeProWahlkreis = {2,4,5};
		int[] sitzeProPartei = {3, 3, 5};
		int[][] stimmen = {
				{35, 33, 32},
				{50, 20, 80},
				{40, 80, 90}
		};
		RundungStrategy halfUpRunder = new RundungStrategyFactory().getHalfUpRunder();
		
		int[][] halfUpSitze = unterzuteilung(stimmen, sitzeProWahlkreis, sitzeProPartei, halfUpRunder);
		
		int[][] expectedhalfUpSitze = {
				{1, 0, 1},
				{1, 1, 2},
				{1, 2, 2}
		};
		
		assertArrayEquals(expectedhalfUpSitze, halfUpSitze);
		
		RundungStrategy downRunder = new RundungStrategyFactory().getDownRunder();
				
		int[][] downSitze = unterzuteilung(stimmen, sitzeProWahlkreis, sitzeProPartei, downRunder);
		
		int[][] expectedDownSitze = {
				{1, 1, 0},
				{1, 0, 3},
				{1, 2, 2}
		};
		
		assertArrayEquals(expectedDownSitze, downSitze);
	}
	
	static int[] oberzuteilung(int[][] stimmen, int[] sitzeProWahlkreis, SitzzuteilungStrategy verteilung) {
		// Veraendern Sie diesen Code nicht
		// Wir werden diesen Code verwenden, um Ihre Submission zu bewerten
		WahlDaten daten = new WahlDaten(stimmen, sitzeProWahlkreis);
		Oberzuteilung oberzuteilung = new Oberzuteilung(daten, verteilung);
		return oberzuteilung.getSitzeProPartei();
	}
	
	static int[][] unterzuteilung(int[][] stimmen, int[] sitzeProWahlkreis, int[] sitzeProPartei, RundungStrategy runder) {
		// Veraendern Sie diesen Code nicht
		// Wir werden diesen Code verwenden, um Ihre Submission zu bewerten
		WahlDaten daten = new WahlDaten(stimmen, sitzeProWahlkreis);
		Unterzuteilung unterzuteilung = new Unterzuteilung(daten, sitzeProPartei, runder);
		return unterzuteilung.getSitze();
	}
	
	static int round(double x, RundungStrategy runder) {
		// Veraendern Sie diesen Code nicht
		// Wir werden diesen Code verwenden, um Ihre Submission zu bewerten
		// Wenn es nicht compiliert, dann muessen Sie die noetigen Methoden implementieren
		return runder.round(x);
	}

}
