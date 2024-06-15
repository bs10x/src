public class Labyrinth {

	public static void main(String[] args) {
		Room a1 = new Room(1);
		Room a2 = new Room(3);
		Room a3 = new Room(4);
		Room a4 = new Room(2);
		Room a5 = new Room(1);
		Room a6 = new Room(5);
		a1.doorsTo.add(a2);
		a1.doorsTo.add(a4);
		a2.doorsTo.add(a3);
		a3.doorsTo.add(a1);
		a4.doorsTo.add(a5);
		a4.doorsTo.add(a6);

		System.out.println(Labyrinth.colorNotSuccessively(a1));
	}

	public static boolean colorExactlyOnce(Room room) {
		// TODO: Geben Sie true zurueck genau dann wenn
		// das Labyrint vom Raum room verlassen werden kann,
		// sodass ein Raum jeder Farbe exakt einmal verwendet wird
		return false;
	}

	public static boolean colorNotSuccessively(Room room) {
		// TODO: Geben Sie true zurueck genau dann wenn
		// das Labyrint vom Raum room verlassen werden kann,
		// sodass nicht zwei Raeume der gleichen Farbe
		// hintereinander verwendet werden.
		return false;
	}

	public static void removeCycle(Room room) {
		// TODO: Falls Sie von dem Raum room eine Schleife
		// erreichen koennen, dann entfernen Sie
		// genau alle diese Verbindungen, welche für
		// die Schleife benoetigt werden.
	}
}
