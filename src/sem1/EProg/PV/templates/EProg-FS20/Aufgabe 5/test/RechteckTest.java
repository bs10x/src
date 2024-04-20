import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RechteckTest {
	
	@Test
	public void testSchneiden1() {		
	    Segment segment1 = new Segment(2,2,2,7);
	    Segment segment2 = new Segment(5,0,5,8);
	    Segment segment3 = new Segment(1,3,9,3);

	    assertFalse(segment1.schneidetSich(segment2));
	    assertFalse(segment2.schneidetSich(segment1));
	    
	    assertTrue(segment1.schneidetSich(segment3));
	    assertTrue(segment3.schneidetSich(segment1));
	    
	    assertTrue(segment2.schneidetSich(segment3));
	    assertTrue(segment3.schneidetSich(segment2));
 	}
	
	@Test
	public void testSchneiden2() {
		Segment segment1 = new Segment(1,2,5,2);
		Segment segment2 = new Segment(2,1,2,4);
		
		assertTrue(segment1.schneidetSich(segment2));
	}
		
	
	@Test
	public void testRechteck1() {		
	    Segment [] linien = {
              new Segment(2,2,2,7),
              new Segment(2,8,2,11),
              new Segment(0,6,4,6),
              new Segment(5,6,9,6)
	    };

	    int anzahl = Rechteck.findeRechtecke(linien); 
	    assertEquals(0, anzahl);
	}
	
	@Test
	public void testRechteck2() {		
	    Segment [] linien = {
	      new Segment(2,2,2,7),
	      new Segment(1,3,9,3),
	      new Segment(0,6,8,6),
	      new Segment(5,0,5,8)
	    };

	    int anzahl = Rechteck.findeRechtecke(linien); 

	    assertEquals(1, anzahl);
	}
	
	@Test
	public void testRechteck3() {		
		Segment [] linien = {
			//Rechteck 1
			new Segment(0,1,3,1),
			new Segment(0,2,3,2),
			new Segment(1,0,1,3),
			new Segment(2,0,2,3),
			//Rechteck 2
			new Segment(4,2,7,2),
			new Segment(4,3,7,3),
			new Segment(5,1,5,4),
			new Segment(6,1,6,4)			
		};
		
		int anzahl = Rechteck.findeRechtecke(linien);		
		assertEquals(2, anzahl);
	}
	
	@Test
	public void testRechteck4() {
		
		Segment [] linien = {
			new Segment(0,1,5,1),
			new Segment(0,3,6,3),
			new Segment(1,0,1,4),
			new Segment(2,0,2,4),
			new Segment(3,0,3,4),
			new Segment(4,0,4,4)
		};
		
		int anzahl = Rechteck.findeRechtecke(linien);
		assertEquals(0, anzahl);
	}
	
}
