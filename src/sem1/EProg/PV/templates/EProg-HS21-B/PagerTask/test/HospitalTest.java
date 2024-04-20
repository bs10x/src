import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HospitalTest {
	
	@Test 
	public void testNormal1() {
		Hospital hospital = new Hospital();
		
		Pager anna = hospital.createPager("normal");
		assertNotNull(anna);
		anna.register("Anna");
		
		Pager bernd = hospital.createPager("normal");
		assertNotNull(bernd);
		bernd.register("Bernd");
		
		// Am Anfang geben anna.inbox() und bernd.inbox() eine leere Liste zurueck.
		assertEquals(List.of(), anna.inbox());
		assertEquals(List.of(), bernd.inbox());
		
		anna.command("Bernd", new TextMessage("Hello World"));
		
		// bernd.inbox() gibt jetzt eine Liste mit einem Element zurueck.
		assertEquals(List.of(), anna.inbox());
		assertEquals(List.of(new TextMessage("Hello World")), bernd.inbox());
		
		anna.command("Bernd", new TextMessage("Bye"));
		
		// bernd.inbox() gibt jetzt eine Liste mit zwei Elementen zurueck.
		assertEquals(List.of(), anna.inbox());
		assertEquals(List.of(new TextMessage("Hello World"), new TextMessage("Bye")), bernd.inbox());
	}
	
	@Test 
	public void testNormal2() {
		Hospital hospital = new Hospital();
		
		Pager anna = hospital.createPager("normal");
		assertNotNull(anna);
		anna.register("Anna");
		
		Pager bernd = hospital.createPager("normal");
		assertNotNull(bernd);
		bernd.register("Bernd");
		
		anna.command("Clausius", new TextMessage("Hello World"));
		
		// anna.inbox() gibt jetzt eine Liste mit einer PagerNotRegisteredMessage zurueck.
		assertEquals(List.of(new PagerNotRegisteredMessage("Clausius")), anna.inbox());
		assertEquals(List.of(), bernd.inbox());
		
		Pager clausius = hospital.createPager("normal");
		clausius.register("Clausius");
		
		// Auch wenn register("Clausius") spaeter aufgerufen wird, aendert sich nichts.
		assertEquals(List.of(new PagerNotRegisteredMessage("Clausius")), anna.inbox());
		assertEquals(List.of(), bernd.inbox());
	}
	
	@Test 
	public void testNormal3() {
		Hospital hospital = new Hospital();
		
		Pager anna = hospital.createPager("normal");
		assertNotNull(anna);
		anna.register("Anna");
		
		Pager bernd = hospital.createPager("normal");
		assertNotNull(bernd);
		bernd.register("Bernd");
		bernd.register("Clausius");
		
		anna.command("Bernd", new TextMessage("Hello World"));
		
		// Wegen bernd.register("Clausius"), anna.inbox() gibt jetzt eine Liste mit einer PagerNotRegisteredMessage zurueck.
		assertEquals(List.of(new PagerNotRegisteredMessage("Bernd")), anna.inbox());
		assertEquals(List.of(), bernd.inbox());
	}
	
	@Test 
	public void testSlow1() {
		Hospital hospital = new Hospital();
		
		Pager anna = hospital.createPager("normal");
		assertNotNull(anna);
		anna.register("Anna");
		
		Pager bernd = hospital.createPager("slow");
		assertNotNull(bernd);
		bernd.register("Bernd");
		
		anna.command("Bernd", new TextMessage("Hello"));
		
		// bernd.inbox() gibt direkt eine Liste mit einem Element zurueck trotz 'slow'.
		assertEquals(List.of(), anna.inbox());
		assertEquals(List.of(new TextMessage("Hello")), bernd.inbox());
		
		bernd.command("Anna", new TextMessage("Bye1")); 
		
		// anna.inbox() gibt noch eine leere Liste zurueck.
		assertEquals(List.of(), anna.inbox());
		
		bernd.command("Anna", new TextMessage("Bye2")); 

		assertEquals(List.of(), anna.inbox());
		
		bernd.command("Anna", new TextMessage("Bye3")); 
		
		// Nach 3 mal bernd.command(...), anna.inbox() gibt eine Liste mit 3 Elementen zurueck.
		assertEquals(List.of(new TextMessage("Bye1"), new TextMessage("Bye2"), new TextMessage("Bye3")), anna.inbox());

		// Danach braucht es wieder 3 mal bernd.command(...)
		
		bernd.command("Anna", new TextMessage("Bye4"));
		
		assertEquals(List.of(new TextMessage("Bye1"), new TextMessage("Bye2"), new TextMessage("Bye3")), anna.inbox());
	}
	
	@Test 
	public void testSlow2() {
		Hospital hospital = new Hospital();
		Pager anna = hospital.createPager("normal");
		assertNotNull(anna);		
		anna.register("Anna");
		
		Pager bernd = hospital.createPager("slow");
		assertNotNull(bernd);		
		bernd.register("Bernd");
		
		bernd.command("Anna", new TextMessage("Bye1")); 		
		bernd.command("Clausius", new TextMessage("Bye2")); 

		assertEquals(List.of(), anna.inbox());
		
		bernd.command("Clausius", new TextMessage("Bye3")); 
		
		assertEquals(List.of(new TextMessage("Bye1")), anna.inbox());
		assertEquals(List.of(new PagerNotRegisteredMessage("Clausius"), new PagerNotRegisteredMessage("Clausius")), bernd.inbox());
	}
	
	@Test 
	public void testAdmin1() {
		Hospital hospital = new Hospital();
		
		Pager anna = hospital.createPager("normal");
		assertNotNull(anna);		
		anna.register("Anna");
		
		Pager bernd = hospital.createPager("normal");
		assertNotNull(bernd);		
		bernd.register("Bernd");
		
		Pager claudia = hospital.createPager("admin");
		assertNotNull(claudia);
		claudia.register("Claudia");
		
		claudia.command("Dirk", new TextMessage("Hey"));

		// anna.inbox(), bernd.inbox(), claudia.inbox() geben jetzt eine Liste mit einem Element zurueck.
		assertEquals(List.of(new TextMessage("Hey")), anna.inbox());
		assertEquals(List.of(new TextMessage("Hey")), bernd.inbox());
		assertEquals(List.of(new TextMessage("Hey")), claudia.inbox());
	}
	
	@Test 
	public void testAdmin2() {
		Hospital hospital = new Hospital();
		Pager anna = hospital.createPager("normal");
		assertNotNull(anna);
		anna.register("Anna");
		
		Pager bernd = hospital.createPager("normal");
		assertNotNull(bernd);
		bernd.register("Bernd");
		
		Pager claudia = hospital.createPager("admin");
		assertNotNull(claudia);
		claudia.register("Claudia");
		
		claudia.command("Anna", new PauseMessage());
		anna.command("Bernd", new TextMessage("Hello1"));
		
		/* Wegen claudia.command("Anna", new PauseMessage()), 
		 * gibt bernd.inbox() eine leere Liste zurueck trotz anna.command("Bernd", new TextMessage("Hello1")) */
		assertEquals(List.of(), bernd.inbox());
		
		claudia.command("Anna", new PauseMessage());
		anna.command("Bernd", new TextMessage("Hello2"));
		
		/* Wegen erneutem claudia.command("Anna", new PauseMessage()), 
		 * anna.command("Bernd", new TextMessage("Hello2")) sorgt dafuer, dass bernd.inbox() eine Liste mit einem Element zurueckgibt. */
		assertEquals(List.of(new TextMessage("Hello2")), bernd.inbox());
	}
	
	@Test 
	public void testAdmin3() {
		Hospital hospital = new Hospital();
		
		Pager anna = hospital.createPager("normal");
		assertNotNull(anna);
		anna.register("Anna");
		
		Pager bernd = hospital.createPager("normal");
		assertNotNull(bernd);
		bernd.register("Bernd");
		
		Pager claudia = hospital.createPager("admin");
		assertNotNull(claudia);
		claudia.register("Claudia");
		
		claudia.command("Anna", new PauseMessage());
		anna.register("Annette");
		anna.command("Bernd", new TextMessage("Hello1"));
		
		// Wegen anna.register("Annette"), claudia.command("Anna", new PauseMessage()) hat keinen Einfluss.
		assertEquals(List.of(new TextMessage("Hello1")), bernd.inbox());
	}
	
	@Test 
	public void testAdmin4() {
		Hospital hospital = new Hospital();
		
		Pager anna = hospital.createPager("slow");
		assertNotNull(anna);
		anna.register("Anna");
		
		Pager bernd = hospital.createPager("normal");
		assertNotNull(bernd);
		bernd.register("Bernd");
		
		Pager claudia = hospital.createPager("admin");
		assertNotNull(claudia);
		claudia.register("Claudia");
		
		claudia.command("Anna", new PauseMessage());
		anna.command("Bernd", new TextMessage("1"));
		claudia.command("Anna", new PauseMessage());
		anna.command("Bernd", new TextMessage("2"));
		anna.command("Bernd", new TextMessage("3"));
		
		assertEquals(List.of(new TextMessage("1"), new TextMessage("2"), new TextMessage("3")), bernd.inbox());
	}
	
	@Test 
	public void testQuery1() {
		Hospital hospital = new Hospital();
		
		Pager anna = hospital.createPager("normal");
		assertNotNull(anna);
		anna.register("Anna");
		
		Pager bernd = hospital.createPager("normal");
		assertNotNull(bernd);
		bernd.register("Bernd");
		
		anna.command("Bernd", new QueryMessage());
		
		assertEquals(List.of(new QueryAnswerMessage("Bernd", 0)), anna.inbox());
		
		bernd.command("Anna", new TextMessage("Hey"));
		anna.command("Bernd", new QueryMessage());
		
		assertEquals(List.of(new QueryAnswerMessage("Bernd", 0), new TextMessage("Hey"), new QueryAnswerMessage("Bernd", 1)), anna.inbox());
	}
	
	@Test 
	public void testQuery2() {
		Hospital hospital = new Hospital();
		
		Pager anna = hospital.createPager("slow");
		assertNotNull(anna);
		anna.register("Anna");
		
		Pager bernd = hospital.createPager("normal");
		assertNotNull(bernd);
		bernd.register("Bernd");
		
		anna.command("Bernd", new QueryMessage());
		bernd.command("Anna", new TextMessage("Hey1"));
		bernd.command("Anna", new TextMessage("Hey2"));
		
		assertEquals(List.of(new TextMessage("Hey1"), new TextMessage("Hey2")), anna.inbox());
		
		anna.command("Bernd", new TextMessage("Hey1"));
		anna.command("Bernd", new TextMessage("Hey2"));
		
		assertEquals(List.of(new TextMessage("Hey1"), new TextMessage("Hey2"), new QueryAnswerMessage("Bernd", 2)), anna.inbox());
	}
	
	@Test 
	public void testQuery3() {
		Hospital hospital = new Hospital();
		
		Pager anna = hospital.createPager("normal");
		assertNotNull(anna);
		anna.register("Anna");
		
		Pager bernd = hospital.createPager("slow");
		assertNotNull(bernd);
		bernd.register("Bernd");
		
		bernd.command("Anna", new TextMessage("Hey1"));
		bernd.command("Anna", new TextMessage("Hey2"));
		anna.command("Bernd", new QueryMessage());
		
		assertEquals(List.of(new QueryAnswerMessage("Bernd", 0)), anna.inbox());
	}
}
