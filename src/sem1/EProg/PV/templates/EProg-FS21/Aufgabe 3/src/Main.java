
public class Main {

	public static void main(String[] args) {
	    BookingSystem booking = new BookingSystem();
 
	    // Zwei Reservierungen werden erstellt. Die Produkte sind noch nicht verfuegbar.
	    Ticket ticket1 = booking.reserve("Tisch", 500);
	    Ticket ticket2 = booking.reserve("Tisch", 1000);
	 
	    // Ein Produkt wird dem System zur Verfuegung gestellt.
	    // Danach ist 'ticket1' bereit und 'ticket2' noch nicht.
	    booking.createProduct("Tisch", "fifo");
	    System.out.println("Das erste Ticket ist bereit: " + ticket1.isReady());
	    System.out.println("Das zweite Ticket ist noch nicht bereit: " + !ticket2.isReady());
	 
	    // Ein Produkt kann gegen das bereite 'ticket1' eingeloest werden.
	    Product table = ticket1.getProduct();
	 
	    // Nachdem das Produkt zurueckgegeben wird, ist 'ticket2' bereit.
	    table.giveBack();
	    System.out.println("Das zweite Ticket ist bereit: " + ticket2.isReady());
	}

}
