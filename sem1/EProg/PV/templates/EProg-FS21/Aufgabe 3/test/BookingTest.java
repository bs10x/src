import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class BookingTest {
	
	@Test
	public void simpleFifo1() {
		BookingSystem booking = createBookingSystem();
		 
	    // Zwei Reservierungen werden erstellt. Die Produkte sind noch nicht verfuegbar.
	    Ticket ticket1 = booking.reserve("Tisch", 500);
	    Ticket ticket2 = booking.reserve("Tisch", 1000);
	    assertNotNull(ticket1);
	    assertNotNull(ticket2);
	    assertFalse(ticket1.isReady());
	    assertFalse(ticket2.isReady());
	 
	    // Ein Produkt wird dem System zur Verfuegung gestellt.
	    // Danach ist 'ticket1' bereit und 'ticket2' noch nicht.
	    booking.createProduct("Tisch", "fifo");
	    assertTrue(ticket1.isReady());
	    assertFalse(ticket2.isReady());
	 
	    // Ein Produkt kann gegen das bereite 'ticket1' eingeloest werden.
	    Product table = ticket1.getProduct();
	    assertNotNull(table);
	    assertEquals("Tisch", table.name());
	    assertEquals("fifo", table.kind());
	 
	    // Nachdem das Produkt zurueckgegeben wird, ist 'ticket2' bereit.
	    table.giveBack();
	    assertTrue(ticket1.isReady());
	    assertTrue(ticket2.isReady());
	    
	    Product table2 = ticket2.getProduct();
	    assertSame(table, table2); // Es muss der gleiche Tisch wie zuvor sein, weil es nur ein Produkt gibt.
	}
	
	@Test
	public void simpleFifo2() {
		BookingSystem booking = createBookingSystem();
		
		booking.createProduct("Tisch", "fifo");
		 
	    Ticket ticket1 = booking.reserve("Tisch", 500);
	    Ticket ticket2 = booking.reserve("Tisch", 1000);
	    assertNotNull(ticket1);
	    assertNotNull(ticket2);
	    assertTrue(ticket1.isReady());
	    assertFalse(ticket2.isReady());
	 
	    // Ein Produkt kann gegen das bereite 'ticket1' eingeloest werden.
	    Product table = ticket1.getProduct();
	    assertNotNull(table);
	    assertEquals("Tisch", table.name());
	    assertEquals("fifo", table.kind());
	 
	    // Nachdem das Produkt zurueckgegeben wird, ist 'ticket2' bereit.
	    table.giveBack();
	    assertTrue(ticket1.isReady());
	    assertTrue(ticket2.isReady());
	    
	    Product table2 = ticket2.getProduct();	    
	    assertSame(table, table2); // Es muss der gleiche Tisch wie zuvor sein, weil es nur ein Produkt gibt.
	}
	
	@Test
	public void simpleFifo3() {
		BookingSystem booking = createBookingSystem();
		
		booking.createProduct("Tisch", "fifo");
		booking.createProduct("Tisch", "fifo");
		 
	    Ticket ticket1 = booking.reserve("Tisch", 500);
	    Ticket ticket2 = booking.reserve("Tisch", 1000);
	    assertNotNull(ticket1);
	    assertNotNull(ticket2);
	    assertTrue(ticket1.isReady());
	    assertTrue(ticket2.isReady());
	 
	    // Beide Produkte muessen verschieden sein:
	    assertNotSame(ticket1.getProduct(), ticket2.getProduct());
	}
	
	@Test
	public void simpleFifo4() {
		BookingSystem booking = createBookingSystem();
		
		booking.createProduct("Stuhl", "fifo"); 
		
		// Tickets fuer Produkte mit underschiedlichen Namen behindern sich nicht.
	    Ticket ticket1 = booking.reserve("Tisch", 500);
	    Ticket ticket2 = booking.reserve("Stuhl", 1000);
	    assertNotNull(ticket1);
	    assertNotNull(ticket2);
	    assertFalse(ticket1.isReady());
	    assertTrue(ticket2.isReady());
	}
	
	@Test
	public void simplePriority1() {
		BookingSystem booking = createBookingSystem();
		 
	    // Zwei Reservierungen werden erstellt. Die Produkte sind noch nicht verfuegbar.
	    Ticket ticket1 = booking.reserve("Tisch", 500);
	    Ticket ticket2 = booking.reserve("Tisch", 1000);
	    assertNotNull(ticket1);
	    assertNotNull(ticket2);
	    assertFalse(ticket1.isReady());
	    assertFalse(ticket2.isReady());
	 
	    // Ein Produkt wird dem System zur Verfuegung gestellt.
	    // Danach ist 'ticket2' bereit und 'ticket1' noch nicht, wegen der Verwendungsart "priority".
	    booking.createProduct("Tisch", "priority");
	    assertFalse(ticket1.isReady());
	    assertTrue(ticket2.isReady());
	 
	    // Ein Produkt kann gegen das bereite 'ticket2' eingeloest werden.
	    Product table = ticket2.getProduct();
	    assertNotNull(table);
	    assertEquals("Tisch", table.name());
	    assertEquals("priority", table.kind());
	 
	    // Nachdem das Produkt zurueckgegeben wird, ist 'ticket1' bereit.
	    table.giveBack();
	    assertTrue(ticket1.isReady());
	    assertTrue(ticket2.isReady());
	    
	    Product table2 = ticket1.getProduct();	    
	    assertSame(table, table2); // Es muss der gleiche Tisch wie zuvor sein, weil es nur ein Produkt gibt.
	}
	
	@Test
	public void simplePriority2() {
		BookingSystem booking = createBookingSystem();
		 
	    // Zwei Reservierungen werden erstellt. Die Produkte sind noch nicht verfuegbar.
	    Ticket ticket1 = booking.reserve("Tisch", 500);
	    Ticket ticket2 = booking.reserve("Tisch", 500);
	    assertNotNull(ticket1);
	    assertNotNull(ticket2);
	    assertFalse(ticket1.isReady());
	    assertFalse(ticket2.isReady());
	 
	    // Ein Produkt wird dem System zur Verfuegung gestellt.
	    // Danach ist 'ticket1' bereit und 'ticket2' noch nicht.
	    booking.createProduct("Tisch", "priority");
	    assertTrue(ticket1.isReady());
	    assertFalse(ticket2.isReady());
	 
	    // Ein Produkt kann gegen das bereite 'ticket1' eingeloest werden.
	    Product table = ticket1.getProduct();
	    assertNotNull(table);
	    assertEquals("Tisch", table.name());
	    assertEquals("priority", table.kind());
	 
	    // Nachdem das Produkt zurueckgegeben wird, ist 'ticket2' bereit.
	    table.giveBack();
	    assertTrue(ticket1.isReady());
	    assertTrue(ticket2.isReady());
	    
	    Product table2 = ticket2.getProduct();	    
	    assertSame(table, table2); // Es muss der gleiche Tisch wie zuvor sein, weil es nur ein Produkt gibt.
	}
	
	@Test
	public void simplePriority3() {
		BookingSystem booking = createBookingSystem();
		
		booking.createProduct("Tisch", "priority");
		 
	    Ticket ticket1 = booking.reserve("Tisch", 500);
	    Ticket ticket2 = booking.reserve("Tisch", 1000);
	    assertNotNull(ticket1);
	    assertNotNull(ticket2);
	    assertTrue(ticket1.isReady());
	    assertFalse(ticket2.isReady());
	 
	    // Ein Produkt kann gegen das bereite 'ticket1' eingeloest werden.
	    Product table = ticket1.getProduct();
	    assertNotNull(table);
	    assertEquals("Tisch", table.name());
	    assertEquals("priority", table.kind());
	 
	    // Nachdem das Produkt zurueckgegeben wird, ist 'ticket2' bereit.
	    table.giveBack();
	    assertTrue(ticket1.isReady());
	    assertTrue(ticket2.isReady());
	    
	    Product table2 = ticket2.getProduct();
	    assertNotNull(table2);
	    assertSame(table, table2); // Es muss der gleiche Tisch wie zuvor sein, weil es nur ein Produkt gibt.
	}
	
	@Test 
	public void simpleMulti1() {
		BookingSystem booking = createBookingSystem();
		
		Ticket ticket1 = booking.multiReserve(new String[] {"Tisch", "Stuhl"}, 500);
		Ticket ticket2 = booking.reserve("Tisch", 500);
		assertNotNull(ticket1);
		assertNotNull(ticket2);
		
		// 'ticket2' ueberholt nicht 'ticket1'
		booking.createProduct("Tisch", "fifo");
		assertFalse(ticket1.isReady());
	    assertFalse(ticket2.isReady());
	    
	    booking.createProduct("Stuhl", "fifo");
	    assertTrue(ticket1.isReady());
	    assertFalse(ticket2.isReady());
		
	    // Das Ticket gibt die Produkte in der richtigen Reihenfolge aus
	    Product table1 = ticket1.getProduct();
	    Product chair1 = ticket1.getProduct();
	    Product table2 = ticket1.getProduct();
	    Product chair2 = ticket1.getProduct();
	    
	    assertNotNull(table1);
	    assertNotNull(chair1);
	    
	    assertEquals("Tisch", table1.name());
	    assertEquals("Stuhl", chair1.name());
	    assertSame(table1, table2);
	    assertSame(chair1,chair2);
	    
	    chair1.giveBack();
	    assertTrue(ticket1.isReady());
	    assertFalse(ticket2.isReady());
	    
	    table1.giveBack();
	    assertTrue(ticket1.isReady());
	    assertTrue(ticket2.isReady());
	}
	
	@Test
	public void simpleMulti2() {		
		BookingSystem booking = createBookingSystem();
		
		Ticket ticket1 = booking.reserve("Tisch", 500);
		Ticket ticket2 = booking.multiReserve(new String[] {"Tisch", "Stuhl"}, 1000);
		assertNotNull(ticket1);
		assertNotNull(ticket2);
		
		booking.createProduct("Stuhl", "fifo");
		booking.createProduct("Tisch", "priority");
		
		assertFalse(ticket1.isReady());
	    assertTrue(ticket2.isReady());
	}
	
	@Test
	public void simpleMulti3() {		
		BookingSystem booking = createBookingSystem();
		
		booking.createProduct("Stuhl", "fifo");
		booking.createProduct("Tisch", "fifo");
		booking.createProduct("Tisch", "priority");
		
		// Eine Auswahl an Produkten reicht, es muss nicht fuer alle Produkte mit den gewuenschten Namen gelten.
		Ticket ticket1 = booking.multiReserve(new String[] {"Tisch", "Lampe"}, 500);
		Ticket ticket2 = booking.multiReserve(new String[] {"Tisch", "Stuhl"}, 1000);
		assertNotNull(ticket1);
		assertNotNull(ticket2);
		
		assertFalse(ticket1.isReady());
	    assertTrue(ticket2.isReady());
	}
	
	@Test
	public void simpleMulti4() {
		BookingSystem booking = createBookingSystem();
		
		Ticket ticket1 = booking.multiReserve(new String[] {"Tisch", "Stuhl"}, 500);
		Ticket ticket2 = booking.multiReserve(new String[] {"Tisch", "Stuhl"}, 1000);
		assertNotNull(ticket1);
		assertNotNull(ticket2);
		
		// Die beiden Tickets behindern sich gegenseitig.
		booking.createProduct("Stuhl", "fifo");
		booking.createProduct("Tisch", "priority");
		assertFalse(ticket1.isReady());
	    assertFalse(ticket2.isReady());
	}
	
	@Test
	public void simpleMulti5() {		
		BookingSystem booking = createBookingSystem();
		
		Ticket ticket1 = booking.multiReserve(new String[] {"Tisch", "Stuhl"}, 500);
		Ticket ticket2 = booking.multiReserve(new String[] {"Tisch", "Stuhl"}, 1000);
		assertNotNull(ticket1);
		assertNotNull(ticket2);
		
		// Das zweite Ticket kann erfuellt werden.
		booking.createProduct("Stuhl", "fifo");
		booking.createProduct("Stuhl", "fifo");
		booking.createProduct("Tisch", "priority");
		assertFalse(ticket1.isReady());
	    assertTrue(ticket2.isReady());
	    
	    booking.createProduct("Tisch", "fifo");
	    assertTrue(ticket1.isReady());
	    assertTrue(ticket2.isReady());
	}
	
	public static BookingSystem createBookingSystem() {
		return new BookingSystem();
	}
}
