import static org.junit.Assert.*;

import org.junit.Test;

public class SchoolTest {
	
	@Test
	public void testProvidedExample() {
		
	    School hogwarts = new School();

	    House hufflepuff = hogwarts.createHouse("Hufflepuff");
	    House ravenclaw  = hogwarts.createHouse("Ravenclaw");

	    Student hannah = new Student("Hannah", "Abbott");
	    Student newton = new Student("Newton", "Scamander");
	    Student luna   = new Student("Luna",   "Lovegood");
	    Student filius = new Student("Filius", "Flitwick");

	    hufflepuff.assign(hannah);
	    hufflepuff.assign(newton);
	    ravenclaw.assign(luna);
	    ravenclaw.assign(filius);

	    hannah.givePoints(10);
	    newton.givePoints(-5);
	    luna.givePoints(8);


	    assertEquals("Ravenclaw", hogwarts.winner().name());
	    assertEquals(8, hogwarts.winner().points());
	    assertEquals(13, hogwarts.points());
	}
	
	@Test
	public void testHouseNames() {
		School hogwarts = new School();
		
		boolean thrown = false;
		try {
			hogwarts.createHouse(null); // no null name
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		if (!thrown) {
			fail("Expected IllegalArgumentException");
		}
		
		hogwarts.createHouse("Hufflepuff");
		
		thrown = false;
		try {
			hogwarts.createHouse("Hufflepuff"); // no duplicates
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		if (!thrown) {
			fail("Expected IllegalArgumentException");
		}
	}
	
	@Test
	public void testStudentCreation() {
		Student hannah = new Student("Hannah", "Abbott");
		assertEquals("Hannah", hannah.firstName());
		assertEquals("Abbott", hannah.lastName());
	}
	
	@Test
	public void testHouseAssignments() {
		School hogwarts = new School();

	    House hufflepuff = hogwarts.createHouse("Hufflepuff");
	    House ravenclaw  = hogwarts.createHouse("Ravenclaw");

	    Student hannah = new Student("Hannah", "Abbott");
	    
	    hufflepuff.assign(hannah);
	    
	    boolean thrown = false;
		try {
			ravenclaw.assign(hannah);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		if (!thrown) {
			fail("Expected IllegalArgumentException");
		}
		
		thrown = false;
		try {
			hufflepuff.assign(hannah);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		if (!thrown) {
			fail("Expected IllegalArgumentException");
		}
	    
	    School durmstrang = new School();
	    House atticus = durmstrang.createHouse("Atticus");
	    atticus.assign(hannah);
	}
	
	@Test
	public void testZeroPointsWinner() {
		School hogwarts = new School();

	    House ravenclaw  = hogwarts.createHouse("Ravenclaw");
	    
	    assertEquals("Ravenclaw", hogwarts.winner().name());
	    assertEquals(0, ravenclaw.points());
	}
	
	@Test
	public void testNegativeHousePoints() {
		
		School hogwarts = new School();

	    House ravenclaw  = hogwarts.createHouse("Ravenclaw");

	    Student luna   = new Student("Luna",   "Lovegood");
	    Student filius = new Student("Filius", "Flitwick");

	    ravenclaw.assign(luna);
	    ravenclaw.assign(filius);

	    luna.givePoints(8);
	    filius.givePoints(-10);
	    
	    assertEquals(0, ravenclaw.points());
	}
}
