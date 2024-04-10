import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class SchoolTestExam {
	
	/* a) */

	@Test
	public void testCreateHouse0() {
		School hogwarts = new School();		
		assertThrows(IllegalArgumentException.class, () -> {
			hogwarts.createHouse(null);
		});
		
		hogwarts.createHouse("Test");
	}
	
	@Test
	public void testCreateHouse1() {
		School hogwarts = new School();
		House gryffindor = hogwarts.createHouse("Gryffindor");
		
		assertEquals("Gryffindor", gryffindor.name());
		
		School durmstrang = new School();
		House h = durmstrang.createHouse("H");
		assertEquals("H", h.name());
	}
	
	@Test
	public void testCreateHouse2() {
		School hogwarts = new School();
		assertNotNull(hogwarts);
		House gryffindor = hogwarts.createHouse("Gryffindor");
		assertNotNull(gryffindor);
		
		assertThrows(IllegalArgumentException.class, () -> {
			hogwarts.createHouse("Gryffindor");
		});
				
		House ravenclaw = hogwarts.createHouse("Ravenclaw");
		assertNotNull(ravenclaw);
		
		House secretHouse = hogwarts.createHouse("Secret House");
		assertNotNull(secretHouse);
		
		School uagadou = new School();
		assertNotNull(uagadou);
		House uGryffindor = uagadou.createHouse("Gryffindor");
		assertNotNull(uGryffindor);

		House uRavenclaw = uagadou.createHouse("Ravenclaw");
		assertNotNull(uRavenclaw);
		
		assertThrows(IllegalArgumentException.class, () -> {
			uagadou.createHouse("Ravenclaw"); 
		});
	}
	
	/* b) */
	@Test
	public void testStudent0() {
		School school = new School();
		House house = school.createHouse("House");
		assertThrows(IllegalArgumentException.class, () -> {
			house.assign(null);
		});
		house.assign(new Student("A","B"));
	}
	
	@Test
	public void testStudent1() {
		Student hermione = new Student("Hermione", "Granger");
		assertNotNull(hermione);
		assertEquals("Hermione", hermione.firstName());
		assertEquals("Granger", hermione.lastName());			

		
		Student neville = new Student("Neville", "Longbottom");
		assertNotNull(neville);
		assertEquals("Neville", neville.firstName());
		assertEquals("Longbottom", neville.lastName());			
	}
	
	@Test
	public void testStudent2() {
		School hogwarts = new School();
		House gryffindor = hogwarts.createHouse("Gryffindor");
		Student hermione = new Student("Ron", "Weasley");

		gryffindor.assign(hermione);
		
		School beauxbatons = new School();
		House maison = beauxbatons.createHouse("Maison");
		maison.assign(hermione);
	}

	
	@Test
	public void testStudent3() {
		School hogwarts = new School();
		assertNotNull(hogwarts);
		House gryffindor = hogwarts.createHouse("Gryffindor");
		assertNotNull(gryffindor);
		
		Student hermione = new Student("Hermione", "Granger");
		gryffindor.assign(hermione);
		
		for(int i = 0; i < 4; i++) {
			assertThrows(IllegalArgumentException.class, () -> {
				gryffindor.assign(hermione); 
			});
		}
		
		House slytherin = hogwarts.createHouse("Slytherin");
		assertNotNull(slytherin);
		assertThrows(IllegalArgumentException.class, () -> {
			slytherin.assign(hermione); 
		});
		
		Student tom = new Student("Tom", "Riddle");
		slytherin.assign(tom);
		
		assertThrows(IllegalArgumentException.class, () -> {
			gryffindor.assign(tom); 
		});
	}
	
	/* c) */
	
	//basic test 0
	@Test
	public void testPoints0() {
		School hogwarts = new School();
		House ravenclaw = hogwarts.createHouse("Ravenclaw");
	    Student luna   = new Student("Luna",   "Lovegood");
	    ravenclaw.assign(luna);
	    luna.givePoints(10);
	    
	    assertEquals(10, ravenclaw.points());
	}
	
	//basic test 1
	@Test
	public void testPoints1() {
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
	    
	    hannah.givePoints(11);
	    newton.givePoints(-5);
	    
	    luna.givePoints(5);
	    filius.givePoints(6);
	    
	    assertEquals(6, hufflepuff.points());
	    assertEquals(11, ravenclaw.points());
	}
	
	
	//zero points
	@Test
	public void testPoints2() {
	    School hogwarts = new School();

	    House hufflepuff = hogwarts.createHouse("Hufflepuff");

	    Student hannah = new Student("Hannah", "Abbott");
	    Student newton = new Student("Newton", "Scamander");
	    
	    hufflepuff.assign(hannah);
	    hufflepuff.assign(newton);
	     
	    hannah.givePoints(23);
	    newton.givePoints(-12);
	    
	    assertTrue(hufflepuff.points() > 0);
	    
	    newton.givePoints(-100);
	    
	    assertEquals(0, hufflepuff.points());
	    
	    hannah.givePoints(1);
	    assertTrue(hufflepuff.points() > 0);   
	}
	
	//student not assigned yet
	@Test
	public void testPoints3() {
		School hogwarts = new School();
	    House hufflepuff = hogwarts.createHouse("Hufflepuff");
	    Student hannah = new Student("Hannah", "Abbott");
	    
	    hannah.givePoints(8);
	    
	    hufflepuff.assign(hannah);
	    
	    assertEquals(0, hufflepuff.points());
	    
	    hannah.givePoints(8);
	    
	    assertEquals(8, hufflepuff.points());
	}
	
	//multiple schools
	@Test
	public void testPoints4() {	
		School hogwarts = new School();
		School durmstrang = new School();

		House hufflepuff = hogwarts.createHouse("Hufflepuff");
	    House terpsichora = durmstrang.createHouse("Terpsichora");

	    Student hannah = new Student("Hannah", "Abbott");
	    Student newton = new Student("Newton", "Scamander");
	    Student luna   = new Student("Luna",   "Lovegood");

	    hufflepuff.assign(hannah);
	    hufflepuff.assign(newton);
	    terpsichora.assign(luna);
	    terpsichora.assign(newton);

	    hannah.givePoints(18);
	    luna.givePoints(12);
	    newton.givePoints(-2);
	    
	    assertEquals(16, hufflepuff.points());
	    assertEquals(10, terpsichora.points());
	}
	
	//no houses no winner
	@Test
	public void testPoints5() {
		School hogwarts = new School();
		assertThrows(IllegalArgumentException.class, () -> {
	    	hogwarts.winner();
		});
		
		House hufflepuff = hogwarts.createHouse("Hufflepuff");
	    Student hannah = new Student("Hannah", "Abbott");
	    hufflepuff.assign(hannah);
	    hannah.givePoints(2);
	    
	    House winner = hogwarts.winner();
	}
	
	//clear winner
	@Test
	public void testPoints6() {
		School hogwarts = new School();
		House gryffindor = hogwarts.createHouse("Gryffindor");
		House slytherin = hogwarts.createHouse("Slytherin");
		
		Student harry = new Student("Harry", "Potter");
		Student ron = new Student("Ron", "Weasley");
		Student hermione = new Student("Hermione", "Granger");
		gryffindor.assign(harry);
		gryffindor.assign(ron);
		gryffindor.assign(hermione);
		
		
		Student draco = new Student("Draco", "Malfoy");
		Student tom = new Student("Tom", "Riddle");
		slytherin.assign(draco);
		slytherin.assign(tom);
		
		harry.givePoints(10);
		ron.givePoints(12);
		hermione.givePoints(14);
		
		draco.givePoints(20);
		tom.givePoints(10);
		
		House winner = hogwarts.winner();
		
		assertEquals("Gryffindor", winner.name());
	}
	
	
	//ambiguous winner
	@Test
	public void testPoints7() {
		School hogwarts = new School();
		House gryffindor = hogwarts.createHouse("Gryffindor");
		House hufflepuff = hogwarts.createHouse("Hufflepuff");
		House slytherin = hogwarts.createHouse("Slytherin");
		
		Student harry = new Student("Harry", "Potter");
		Student ron = new Student("Ron", "Weasley");
		Student hermione = new Student("Hermione", "Granger");
		gryffindor.assign(harry);
		gryffindor.assign(ron);
		gryffindor.assign(hermione);
		
	    Student hannah = new Student("Hannah", "Abbott");
	    hufflepuff.assign(hannah);
		
		
		Student draco = new Student("Draco", "Malfoy");
		Student tom = new Student("Tom", "Riddle");
		slytherin.assign(draco);
		slytherin.assign(tom);
		
		harry.givePoints(10);
		ron.givePoints(12);
		hermione.givePoints(14);
		
		hannah.givePoints(36);
		
		draco.givePoints(20);
		tom.givePoints(10);
		
		House winner = hogwarts.winner();
		
		assertTrue(winner.equals(gryffindor) || winner.equals(hufflepuff));
	}
	
	//cumulative points
	@Test
	public void testPoints8() {
		School hogwarts = new School();
		House gryffindor = hogwarts.createHouse("Gryffindor");
		House slytherin = hogwarts.createHouse("Slytherin");
		
		Student harry = new Student("Harry", "Potter");
		Student hermione = new Student("Hermione", "Granger");
		gryffindor.assign(harry);
		gryffindor.assign(hermione);
		
		
		Student draco = new Student("Draco", "Malfoy");
		Student tom = new Student("Tom", "Riddle");
		slytherin.assign(draco);
		slytherin.assign(tom);
		
		harry.givePoints(10);
		hermione.givePoints(14);
		
		draco.givePoints(20);
		tom.givePoints(10);
		
		assertEquals(54, hogwarts.points());
	}
	
	static <T extends Throwable> void assertThrows(Class<T> expectedType, Runnable executable) {
		boolean thrown = false;
		try {
			executable.run();
		} catch (Throwable actualException) {
			if (expectedType.isInstance(actualException)) {
				thrown = true;
			}
		}
		if (!thrown) {
			fail("Expected " + expectedType.getCanonicalName() + ", but it was not thrown.");
		}
		
	}

}