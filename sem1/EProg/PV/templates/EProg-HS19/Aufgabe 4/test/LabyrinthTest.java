
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LabyrinthTest {

	@Test
	public void testColorExactlyOnceTrue1() {
		Room a0 = new Room(1);
		Room a1 = new Room(3);
		Room a2 = new Room(5);
		Room a3 = new Room(4);
		Room a4 = new Room(2);
		Room a4E = new Room(6);
		Room a5 = new Room(7);
		Room a6 = new Room(8);
		Room a7 = new Room(6);
		Room a8 = new Room(9);
		Room a9 = new Room(0);
		a0.doorsTo.add(a1);
		a1.doorsTo.add(a2);
		a2.doorsTo.add(a3);
		a3.doorsTo.add(a4);
		a4.doorsTo.add(a5);
		a4.doorsTo.add(a4E);
		a5.doorsTo.add(a6);
		a6.doorsTo.add(a7);
		a7.doorsTo.add(a8);
		a8.doorsTo.add(a9);
		a2.doorsTo.add(a5);
		a3.doorsTo.add(a6);
		a7.doorsTo.add(a2);

		assertTrue(Labyrinth.colorExactlyOnce(a0));
	}

	@Test
	public void testColorExactlyOnceTrue2() {
		Room a0 = new Room(1);
		Room a1 = new Room(3);
		Room a2 = new Room(5);
		Room a3 = new Room(4);
		Room a4 = new Room(2);
		Room a5 = new Room(7);
		Room a6 = new Room(8);
		Room a7 = new Room(6);
		Room a8 = new Room(9);
		Room a9 = new Room(0);
		a0.doorsTo.add(a1);
		a1.doorsTo.add(a0);
		a1.doorsTo.add(a2);
		a2.doorsTo.add(a1);
		a2.doorsTo.add(a3);
		a3.doorsTo.add(a2);
		a3.doorsTo.add(a4);
		a4.doorsTo.add(a3);
		a4.doorsTo.add(a5);
		a5.doorsTo.add(a4);
		a5.doorsTo.add(a6);
		a6.doorsTo.add(a5);
		a6.doorsTo.add(a7);
		a7.doorsTo.add(a8);
		a7.doorsTo.add(a6);
		a8.doorsTo.add(a9);
		a8.doorsTo.add(a7);

		assertTrue(Labyrinth.colorExactlyOnce(a0));
	}

	@Test
	public void testColorExactlyOnceTrue3() {
		Room a0 = new Room(1);
		Room a1 = new Room(3);
		Room a2 = new Room(5);
		Room a3 = new Room(4);
		Room a4 = new Room(2);
		Room a5 = new Room(7);
		Room a6 = new Room(8);
		Room a7 = new Room(6);
		Room a8 = new Room(9);
		Room a9 = new Room(0);
		Room a10 = new Room(0);
		Room a11 = new Room(1);
		a0.doorsTo.add(a1);
		a1.doorsTo.add(a2);
		a1.doorsTo.add(a2);
		a2.doorsTo.add(a3);
		a2.doorsTo.add(a3);
		a3.doorsTo.add(a4);
		a3.doorsTo.add(a4);
		a4.doorsTo.add(a5);
		a4.doorsTo.add(a5);
		a5.doorsTo.add(a6);
		a5.doorsTo.add(a6);
		a6.doorsTo.add(a7);
		a6.doorsTo.add(a7);
		a7.doorsTo.add(a8);
		a7.doorsTo.add(a8);
		a8.doorsTo.add(a9);
		a8.doorsTo.add(a9);
		a8.doorsTo.add(a10);
		a8.doorsTo.add(a10);
		a8.doorsTo.add(a11);
		a8.doorsTo.add(a11);

		assertTrue(Labyrinth.colorExactlyOnce(a0));
	}

	@Test
	public void testColorExactlyOnceTrue4() {
		Room a0 = new Room(1);
		Room a1 = new Room(3);
		Room a2 = new Room(5);
		Room a3 = new Room(4);
		Room a4 = new Room(2);
		Room a5 = new Room(7);
		Room a6 = new Room(8);
		Room a7 = new Room(6);
		Room a8 = new Room(9);
		Room a9 = new Room(0);
		a0.doorsTo.add(a1);
		a0.doorsTo.add(a2);
		a0.doorsTo.add(a3);
		a0.doorsTo.add(a4);
		a0.doorsTo.add(a5);
		a0.doorsTo.add(a6);
		a0.doorsTo.add(a7);
		a0.doorsTo.add(a8);
		a0.doorsTo.add(a9);
		a1.doorsTo.add(a2);
		a1.doorsTo.add(a3);
		a1.doorsTo.add(a4);
		a1.doorsTo.add(a5);
		a1.doorsTo.add(a6);
		a1.doorsTo.add(a7);
		a1.doorsTo.add(a8);
		a1.doorsTo.add(a9);
		a2.doorsTo.add(a3);
		a2.doorsTo.add(a4);
		a2.doorsTo.add(a5);
		a2.doorsTo.add(a6);
		a2.doorsTo.add(a7);
		a2.doorsTo.add(a8);
		a2.doorsTo.add(a9);
		a3.doorsTo.add(a4);
		a3.doorsTo.add(a4);
		a3.doorsTo.add(a5);
		a3.doorsTo.add(a6);
		a3.doorsTo.add(a7);
		a3.doorsTo.add(a8);
		a3.doorsTo.add(a9);
		a4.doorsTo.add(a5);
		a4.doorsTo.add(a6);
		a4.doorsTo.add(a7);
		a4.doorsTo.add(a8);
		a4.doorsTo.add(a9);
		a5.doorsTo.add(a6);
		a5.doorsTo.add(a7);
		a5.doorsTo.add(a8);
		a5.doorsTo.add(a9);
		a6.doorsTo.add(a7);
		a6.doorsTo.add(a8);
		a6.doorsTo.add(a9);
		a7.doorsTo.add(a8);
		a7.doorsTo.add(a9);
		a8.doorsTo.add(a9);

		assertTrue(Labyrinth.colorExactlyOnce(a0));
	}

	@Test
	public void testColorExactlyOnceFalse1() {
		Room a0 = new Room(1);
		Room a1 = new Room(3);
		Room a2 = new Room(5);
		Room a3 = new Room(4);
		Room a4 = new Room(2);
		Room a4E = new Room(6);
		Room a5 = new Room(7);
		Room a6 = new Room(8);
		Room a7 = new Room(6);
		Room a8 = new Room(9);
		Room a9 = new Room(0);
		Room a10 = new Room(5);
		a0.doorsTo.add(a1);
		a1.doorsTo.add(a2);
		a2.doorsTo.add(a3);
		a3.doorsTo.add(a4);
		a4.doorsTo.add(a5);
		a4.doorsTo.add(a4E);
		a5.doorsTo.add(a6);
		a6.doorsTo.add(a7);
		a7.doorsTo.add(a8);
		a8.doorsTo.add(a9);
		a9.doorsTo.add(a10);
		a2.doorsTo.add(a5);
		a3.doorsTo.add(a6);
		a7.doorsTo.add(a2);

		assertFalse(Labyrinth.colorExactlyOnce(a0));
	}

	@Test
	public void testColorExactlyOnceFalse2() {
		Room a0 = new Room(1);
		Room a1 = new Room(3);
		Room a2 = new Room(5);
		Room a3 = new Room(4);
		Room a4 = new Room(2);
		Room a4E = new Room(6);
		Room a5 = new Room(7);
		Room a6 = new Room(8);
		Room a7 = new Room(6);
		Room a8 = new Room(9);
		Room a9 = new Room(0);
		a0.doorsTo.add(a1);
		a1.doorsTo.add(a2);
		a2.doorsTo.add(a3);
		a3.doorsTo.add(a4);
		a4.doorsTo.add(a5);
		a4.doorsTo.add(a4E);
		a6.doorsTo.add(a7);
		a7.doorsTo.add(a8);
		a8.doorsTo.add(a9);
		a2.doorsTo.add(a5);
		a3.doorsTo.add(a6);
		a7.doorsTo.add(a2);

		assertFalse(Labyrinth.colorExactlyOnce(a0));
	}

	@Test
	public void testColorNotSuccessivelyTrue1() {
		Room a1 = new Room(1);
		Room a2 = new Room(3);
		Room a3 = new Room(4);
		Room a4 = new Room(2);
		Room a5 = new Room(2);
		Room a6 = new Room(1);
		a1.doorsTo.add(a2);
		a1.doorsTo.add(a4);
		a2.doorsTo.add(a3);
		a3.doorsTo.add(a1);
		a4.doorsTo.add(a5);
		a4.doorsTo.add(a6);

		assertTrue(Labyrinth.colorNotSuccessively(a1));
	}

	@Test
	public void testColorNotSuccessivelyTrue2() {
		Room a1 = new Room(1);

		assertTrue(Labyrinth.colorNotSuccessively(a1));
	}

	@Test
	public void testColorNotSuccessivelyTrue3() {
		Room a1 = new Room(1);
		for (int i = 0; i < 10000; i += 1) {
			a1.doorsTo.add(new Room(1));
		}
		a1.doorsTo.add(new Room(0));

		assertTrue(Labyrinth.colorNotSuccessively(a1));
	}

	@Test
	public void testColorNotSuccessivelyFalse1() {
		Room a1 = new Room(1);
		Room a2 = new Room(3);
		Room a3 = new Room(4);
		Room a4 = new Room(2);
		Room a5 = new Room(2);
		Room a6 = new Room(1);
		a1.doorsTo.add(a2);
		a1.doorsTo.add(a4);
		a2.doorsTo.add(a3);
		a3.doorsTo.add(a1);
		a4.doorsTo.add(a5);
		a4.doorsTo.add(a6);
		a5.doorsTo.add(a6);
		a6.doorsTo.add(a5);

		assertFalse(Labyrinth.colorNotSuccessively(a1));
	}

	@Test
	public void testColorNotSuccessivelyFalse2() {
		Room a1 = new Room(1);
		Room a2 = new Room(3);
		Room a3 = new Room(4);
		Room a4 = new Room(2);
		Room a5 = new Room(2);
		Room a6 = new Room(2);
		a1.doorsTo.add(a2);
		a1.doorsTo.add(a4);
		a2.doorsTo.add(a3);
		a3.doorsTo.add(a1);
		a4.doorsTo.add(a5);
		a4.doorsTo.add(a6);

		assertFalse(Labyrinth.colorNotSuccessively(a1));
	}

	@Test
	public void testColorNotSuccessivelyFalse3() {
		Room a1 = new Room(1);
		Room a2 = new Room(3);
		Room a3 = new Room(4);
		Room a4 = new Room(1);
		Room a5 = new Room(2);
		Room a6 = new Room(1);
		a1.doorsTo.add(a2);
		a1.doorsTo.add(a4);
		a2.doorsTo.add(a3);
		a3.doorsTo.add(a1);
		a4.doorsTo.add(a5);
		a4.doorsTo.add(a6);

		assertFalse(Labyrinth.colorNotSuccessively(a1));
	}

	@Test
	public void testRemoveCycle1() {
		Room a0 = new Room(4);
		Room a1 = new Room(1);
		Room a2 = new Room(3);
		Room a3 = new Room(4);
		Room a4 = new Room(2);
		Room a5 = new Room(1);
		Room a6 = new Room(2);
		a0.doorsTo.add(a1);
		a1.doorsTo.add(a2);
		a1.doorsTo.add(a4);
		a2.doorsTo.add(a3);
		a3.doorsTo.add(a1);
		a4.doorsTo.add(a5);
		a4.doorsTo.add(a6);

		Labyrinth.removeCycle(a0);

		assertDoorFromTo(a0, a1);
		assertNoDoorFromTo(a1, a2);
		assertDoorFromTo(a1, a4);
		assertNoDoorFromTo(a2, a3);
		assertNoDoorFromTo(a3, a1);
		assertDoorFromTo(a4, a5);
		assertDoorFromTo(a4, a6);
	}

	@Test
	public void testRemoveCycle2() {
		Room a0 = new Room(4);
		Room a1 = new Room(1);
		Room a2 = new Room(3);
		Room a3 = new Room(4);
		Room a4 = new Room(2);
		Room a5 = new Room(1);
		Room a6 = new Room(2);
		a0.doorsTo.add(a1);
		a1.doorsTo.add(a2);
		a1.doorsTo.add(a4);
		a2.doorsTo.add(a3);
		a3.doorsTo.add(a4);
		a4.doorsTo.add(a5);
		a4.doorsTo.add(a6);

		Labyrinth.removeCycle(a0);

		assertDoorFromTo(a0, a1);
		assertDoorFromTo(a1, a2);
		assertDoorFromTo(a1, a4);
		assertDoorFromTo(a2, a3);
		assertDoorFromTo(a3, a4);
		assertDoorFromTo(a4, a5);
		assertDoorFromTo(a4, a6);
	}

	@Test
	public void testRemoveCycle3() {
		Room a0 = new Room(4);
		Room a1 = new Room(1);
		Room a2 = new Room(3);
		Room a3 = new Room(4);
		Room a4 = new Room(2);
		Room a5 = new Room(1);
		Room a6 = new Room(2);
		a0.doorsTo.add(a1);
		a1.doorsTo.add(a2);
		a1.doorsTo.add(a2);
		a1.doorsTo.add(a4);
		a2.doorsTo.add(a3);
		a2.doorsTo.add(a3);
		a3.doorsTo.add(a1);
		a3.doorsTo.add(a1);
		a4.doorsTo.add(a5);
		a4.doorsTo.add(a6);

		Labyrinth.removeCycle(a0);

		assertDoorFromTo(a0, a1);
		// Nur die Tuer der Schleife wird geloescht, die andere bleibt.
		assertDoorFromTo(a1, a2, 1);
		assertDoorFromTo(a1, a4);
		// Nur die Tuer der Schleife wird geloescht, die andere bleibt.
		assertDoorFromTo(a2, a3, 1);
		// Nur die Tuer der Schleife wird geloescht, die andere bleibt.
		assertDoorFromTo(a3, a1, 1);
		assertDoorFromTo(a4, a5);
		assertDoorFromTo(a4, a6);
	}

	// Prueft, dass eine Tuer von 'from' zu 'to' existiert.
	private static void assertDoorFromTo(Room from, Room to) {
		assertTrue(from.doorsTo.contains(to));
	}

	// Prueft, dass keine Tuer von 'from' zu 'to' existiert.
	private static void assertNoDoorFromTo(Room from, Room to) {
		assertFalse(from.doorsTo.contains(to));
	}

	// Prueft, dass 'num' viele Tueren von 'from' zu 'to' existiert.
	private static void assertDoorFromTo(Room from, Room to, int num) {
		int count = 0;
		for (Room room : from.doorsTo) {
			if (room == to) {
				count += 1;
			}
		}
		assertEquals(num, count);
	}
}
