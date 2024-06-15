public class Main {
	
	// Sie duerfen diese Datei nach Belieben aendern

	public static void main(String[] args) {
		
	    School hogwarts = new School();

	    // Haeuser werden erstellt (Anzahl Haeuser und Namen sind nicht eingeschraenkt).
	    House hufflepuff = hogwarts.createHouse("Hufflepuff");
	    House ravenclaw  = hogwarts.createHouse("Ravenclaw");

	    // Studenten haben Vor- und Nachnamen.
	    Student hannah = new Student("Hannah", "Abbott");
	    Student newton = new Student("Newton", "Scamander");
	    Student luna   = new Student("Luna",   "Lovegood");
	    Student filius = new Student("Filius", "Flitwick");

	    // Studenten werden den Haeusern zugeordnet.
	    hufflepuff.assign(hannah);
	    hufflepuff.assign(newton);
	    ravenclaw.assign(luna);
	    ravenclaw.assign(filius);

	    // Punkte werden an Studenten vergeben. Punkte koennen auch negativ sein.
	    hannah.givePoints(10);
	    newton.givePoints(-5);
	    luna.givePoints(8);

	    // Informationen zu der Summe an Punkten und dem aktuellen Siegerhaus 
	    // koennen immer abgefragt werden.
	    System.out.println("Siegerhaus: " + hogwarts.winner().name());
	    System.out.println("Siegerpunkte: " + hogwarts.winner().points());
	    System.out.println("Hogwarts Punkte Insgesammt" + hogwarts.points());
	}

}
