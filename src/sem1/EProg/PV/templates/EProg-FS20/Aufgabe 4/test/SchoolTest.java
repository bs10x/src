import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
		
		assertThrows(IllegalArgumentException.class, () -> {
			hogwarts.createHouse(null); // no null name
		});
		
		hogwarts.createHouse("Hufflepuff");
		assertThrows(IllegalArgumentException.class, () -> {
			hogwarts.createHouse("Hufflepuff"); // no duplicates
		});
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
	    assertThrows(IllegalArgumentException.class, () -> {
	    	ravenclaw.assign(hannah);
		});
	    assertThrows(IllegalArgumentException.class, () -> {
	    	hufflepuff.assign(hannah);
		});
	    
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
