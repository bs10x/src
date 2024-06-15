import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;


public class FourinarowTest {

	@Test
	public void testMoeglicheSpalten() {
		for(int i = 0; i < Spielzustand.spalten; i++) {
			Spielzustand brett = new Spielzustand();
			brett.setzeStein(true, i);
			brett.setzeStein(false, i);
			brett.setzeStein(true, i);
			brett.setzeStein(false, i);
			brett.setzeStein(true, i);
			Set<Integer> mSpalten = brett.moeglicheSpalten();
	        assertTrue(mSpalten.size() == Spielzustand.spalten);
			brett.setzeStein(false, i);
			mSpalten = brett.moeglicheSpalten();
			assertFalse(mSpalten.contains(i)); 
	        assertTrue(mSpalten.size() == (Spielzustand.spalten-1));
		}
	}
	
	@Test
	public void testKannGewinnenEinStein() {	
		Spielzustand leeresBrett = new Spielzustand();
		Spielzustand neuesBrett = new Spielzustand(leeresBrett);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(false, 2);
		neuesBrett.setzeStein(false, 2);
		neuesBrett.setzeStein(false, 2);
		Fourinarow fia = new Fourinarow();
		for(int z = 1; z <= 4; z++) {
			assertTrue(fia.kannGewinnen(true, neuesBrett, z));
			assertTrue(fia.kannGewinnen(false, neuesBrett, z));	
		}
	}
	
	@Test
	public void testKannGewinnenZweiSteine() {	
		Spielzustand leeresBrett = new Spielzustand();
		Spielzustand neuesBrett = new Spielzustand(leeresBrett);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(false, 2);
		neuesBrett.setzeStein(false, 2);
		Fourinarow fia = new Fourinarow();
		assertFalse(fia.kannGewinnen(true, neuesBrett, 1));
		assertFalse(fia.kannGewinnen(false, neuesBrett, 1));	
		for(int z = 2; z <= 4; z++) {
			assertTrue(fia.kannGewinnen(true, neuesBrett, z));
			assertTrue(fia.kannGewinnen(false, neuesBrett, z));	
		}
	}
	
	@Test
	public void testKannGewinnenDreiSteine() {	
		Spielzustand leeresBrett = new Spielzustand();
		Spielzustand neuesBrett = new Spielzustand(leeresBrett);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(false, 2);
		Fourinarow fia = new Fourinarow();
		for(int z = 1; z <= 2; z++) {
			assertFalse(fia.kannGewinnen(true, neuesBrett, z));
			assertFalse(fia.kannGewinnen(false, neuesBrett, z));
		}
		for(int z = 3; z <= 4; z++) {
			assertTrue(fia.kannGewinnen(true, neuesBrett, z));
			assertTrue(fia.kannGewinnen(false, neuesBrett, z));	
		}
	}
	
	@Test
	public void testHatGewonnen() {	
		Spielzustand leeresBrett = new Spielzustand();
		Spielzustand neuesBrett = new Spielzustand(leeresBrett);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(false, 2);
		neuesBrett.setzeStein(false, 2);
		neuesBrett.setzeStein(false, 2);
		assertFalse(neuesBrett.hatGewonnen(false));
		assertFalse(neuesBrett.hatGewonnen(true));
		
		Spielzustand brett3 = new Spielzustand(neuesBrett);
		brett3.setzeStein(false, 0);
		brett3.setzeStein(true, 3);
		assertFalse(brett3.hatGewonnen(false));
		assertTrue(brett3.hatGewonnen(true));
		
		Spielzustand brett4 = new Spielzustand(neuesBrett);
		brett4.setzeStein(true, 6);
		brett4.setzeStein(false, 2);
		assertFalse(brett4.hatGewonnen(true));
		assertTrue(brett4.hatGewonnen(false));
	}
	
	@Test
	public void testSetzeStein() {	
		Spielzustand brett = new Spielzustand();	
		brett.setzeStein(true, 0);
		isConsistent(brett);
		assertTopmost(brett, true, 0, 0);
		
		brett.setzeStein(false, 0);
		isConsistent(brett);
		assertTopmost(brett, false, 0, 1);
		
		brett.setzeStein(true, 0);
		isConsistent(brett);
		assertTopmost(brett, true, 0, 2);
		
		brett.setzeStein(false, 0);
		isConsistent(brett);
		assertTopmost(brett, false, 0, 3);
		
		brett.setzeStein(false, 1);
		brett.setzeStein(false, 2);
		brett.setzeStein(false, 0);
		
		isConsistent(brett);
		assertTopmost(brett, false, 1, 0);
		assertTopmost(brett, false, 2, 0);
		assertTopmost(brett, false, 0, 4);
	}
	
	private void assertTopmost(Spielzustand brett, boolean roterSpieler, int col, int row) {
		for (int r=0; r < Spielzustand.reihen; r++) {
			if (r < row) {
				assertTrue(brett.gelbeSteine[col][r] || brett.roteSteine[col][r]);
			} else if (r == row) {
				if(!roterSpieler) {
					assertTrue(brett.gelbeSteine[col][r] && !brett.roteSteine[col][r]);
				} else {
					assertTrue(!brett.gelbeSteine[col][r] && brett.roteSteine[col][r]);
				}
			} else {
				assertFalse(brett.gelbeSteine[col][r] && brett.roteSteine[col][r]);
			}
		}
	}
	
	private static void isConsistent(Spielzustand brett) {
		for(int col=0; col < Spielzustand.spalten; col++) {
			boolean stoneBelow = true;
			for (int row=0; row < Spielzustand.reihen; row++) {
				assertFalse(brett.gelbeSteine[col][row] && brett.roteSteine[col][row]);
				boolean occupied = brett.gelbeSteine[col][row] || brett.roteSteine[col][row];
				assertTrue(!occupied || stoneBelow); 
				stoneBelow = occupied;
			}
		}
	}
	
	@Test
	public void testUnentschieden() {
		
		{
			Spielzustand brett = new Spielzustand();
			
			brett.gelbeSteine = new boolean[][] {
				{true, false, true, true, true, false}, 
				{true, true, false, false, false, true}, 
				{false, false, true, true, false, false}, 
				{true, true, false, false, false, true}, 
				{true, false, true, true, true, false}, 
				{false, false, true, false, false, true}, 
				{true, true, false, true, false, false}
			};
			brett.roteSteine = new boolean[][] {
				{false, true, false, false, false, true}, 
				{false, false, true, true, true, false}, 
				{true, true, false, false, true, true}, 
				{false, false, true, true, true, false}, 
				{false, true, false, false, false, true}, 
				{true, true, false, true, true, false}, 
				{false, false, true, false, true, false}
			};
			Fourinarow fia = new Fourinarow();
			assertTrue(fia.unentschieden(true, brett));
			assertTrue(fia.unentschieden(false, brett));
		}
		
		{
			Spielzustand brett = new Spielzustand();
			
			brett.gelbeSteine = new boolean[][] {
				{true, true, false, true, false, false}, 
				{true, false, false, true, false, true}, 
				{false, false, true, true, true, false}, 
				{true, false, true, false, false, false}, 
				{false, true, false, true, false, true}, 
				{false, true, false, true, false, true}, 
				{true, true, true, false, false, false}
			};
			brett.roteSteine = new boolean[][] {
				{false, false, true, false, true, true}, 
				{false, true, true, false, true, false}, 
				{true, true, false, false, false, true}, 
				{false, true, false, true, true, true}, 
				{true, false, true, false, true, false}, 
				{true, false, true, false, true, false}, 
				{false, false, false, false, false, false}
			};
			Fourinarow fia = new Fourinarow();
			assertTrue(fia.unentschieden(true, brett));
			assertFalse(fia.unentschieden(false, brett));
		}
		
		{
			Spielzustand brett = new Spielzustand();
			
			brett.gelbeSteine = new boolean[][] {
				{true, false, true, true, true, false}, 
				{false, true, true, false, false, true}, 
				{false, false, false, true, false, false}, 
				{false, true, true, true, false, true}, 
				{true, false, false, false, true, false}, 
				{true, false, true, false, false, true}, 
				{true, true, false, true, false, false}
			};
			brett.roteSteine = new boolean[][] {
				{false, true, false, false, false, true}, 
				{true, false, false, true, true, false}, 
				{true, true, true, false, false, false}, 
				{true, false, false, false, true, false}, 
				{false, true, true, true, false, false}, 
				{false, true, false, true, true, false}, 
				{false, false, true, false, true, true}
			};
			Fourinarow fia = new Fourinarow();
			assertTrue(fia.unentschieden(true, brett));
		}
		
		{
			Spielzustand brett = new Spielzustand();
			
			brett.gelbeSteine = new boolean[][] {
				{false, true, false, true, true, true}, 
				{true, false, false, false, false, false}, 
				{true, false, true, true, true, false}, 
				{false, true, false, false, false, true}, 
				{false, false, true, true, false, false}, 
				{true, false, true, false, true, false}, 
				{true, false, false, true, true, true}
			};
			brett.roteSteine = new boolean[][] {
				{true, false, true, false, false, false}, 
				{false, true, true, true, false, false}, 
				{false, true, false, false, false, true}, 
				{true, false, true, true, true, false}, 
				{true, true, false, false, true, true}, 
				{false, true, false, true, false, true}, 
				{false, true, true, false, false, false}
			};
			Fourinarow fia = new Fourinarow();
			assertTrue(fia.unentschieden(false, brett));
			assertFalse(fia.unentschieden(true, brett));
		}
		
		{
			Spielzustand brett = new Spielzustand();
			
			brett.gelbeSteine = new boolean[][] {
				{true, false, true, false, true, false}, 
				{true, false, true, true, false, true}, 
				{true, false, false, true, false, true}, 
				{false, true, false, true, false, false}, 
				{false, true, false, false, true, false}, 
				{true, false, true, true, false, false}, 
				{false, true, true, false, false, true}
			};
			brett.roteSteine = new boolean[][] {
				{false, true, false, true, false, true}, 
				{false, true, false, false, true, false}, 
				{false, true, true, false, true, false}, 
				{true, false, true, false, true, true}, 
				{true, false, true, true, false, true}, 
				{false, true, false, false, false, false}, 
				{true, false, false, true, true, false}
			};
			Fourinarow fia = new Fourinarow();
			assertTrue(fia.unentschieden(false, brett));
			assertFalse(fia.unentschieden(true, brett));
		}
	
	}
}
