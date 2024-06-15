import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class LabyrinthTest {
	
	static Room[] testGraph() {
		// 6 Raeume: N0, N1, N2, N3, N4, NFive
		Room[] N = new Room[6];
		N[0] = new Room("N0");
		N[1] = new Room("N1");
		N[2] = new Room("N2");
		N[3] = new Room("N3");
		N[4] = new Room("N4");
		N[5] = new Room("NFive");
		
		N[0].doorsTo = new Room[]{ N[1], N[2] };
		N[1].doorsTo = new Room[]{ N[3], N[4] };
		N[2].doorsTo = new Room[]{ N[4], N[5] };
		N[3].doorsTo = new Room[]{  };
		N[4].doorsTo = new Room[]{  };
		N[5].doorsTo = new Room[]{  };
		
		return N;
	}
	
	@Test
	public void testExits1() {
		Room[] N = testGraph();
		
		List<Room> prefix0 = new LinkedList<>(List.of());
		List<Room> res0 = Labyrinth.exits(N[0], prefix0);
		assertNotNull(res0);
		assertEquals(Set.of(N[3],N[4],N[5]), new HashSet<>(res0));
		
		List<Room> prefix1 = new LinkedList<>(List.of(N[0], N[2]));
		List<Room> res1 = Labyrinth.exits(N[0], prefix1);
		assertNotNull(res1);
		assertEquals(Set.of(N[4],N[5]), new HashSet<>(res1));
		
		List<Room> prefix2 = new LinkedList<>(List.of(N[2]));
		List<Room> res2 = Labyrinth.exits(N[0], prefix2);
		assertNotNull(res2);
		assertEquals(Set.of(), new HashSet<>(res2));
		
		List<Room> prefix3 = new LinkedList<>(List.of(N[0],N[3]));
		List<Room> res3 = Labyrinth.exits(N[0], prefix3);
		assertNotNull(res3);
		assertEquals(Set.of(), new HashSet<>(res3));
	}
	
	@Test
	public void testExits2() {
		Room[] N = testGraph();
		
		List<Room> prefix0 = new LinkedList<>(List.of());
		List<Room> res0 = Labyrinth.exits(N[3], prefix0);
		assertNotNull(res0);
		assertEquals(Set.of(N[3]), new HashSet<>(res0));
		
		List<Room> prefix1 = new LinkedList<>(List.of(N[0]));
		List<Room> res1 = Labyrinth.exits(N[3], prefix1);
		assertNotNull(res1);
		assertEquals(Set.of(), new HashSet<>(res1));
		
		List<Room> prefix2 = new LinkedList<>(List.of(N[3]));
		List<Room> res2 = Labyrinth.exits(N[3], prefix2);
		assertNotNull(res2);
		assertEquals(Set.of(N[3]), new HashSet<>(res2));
	}
	
	@Test
	public void testSort() {
		Room[] N = testGraph();
		
		List<Room> ex0 = new LinkedList<Room>(List.of(N[0],N[5],N[1]));
		Labyrinth.sortRooms(ex0);
		assertEquals(List.of(N[5],N[1],N[0]), ex0);
		
		List<Room> ex1 = new LinkedList<Room>(List.of(N[1], N[2]));
		Labyrinth.sortRooms(ex1);
		assertEquals(List.of(N[2],N[1]), ex1);
		
		Room Q = new Room("Q", new Room[] {new Room("R1"), new Room("R2"), new Room("R3"), new Room("R4")});
		List<Room> ex2 = new LinkedList<Room>(List.of(N[0], Q));
		Labyrinth.sortRooms(ex2);
		assertEquals(List.of(N[0],Q), ex2);		
	}
	
	@Test
	public void testPathsWithSameNames1() {		
		Room ex0 = createDenseLabyrinth(new String[][] {{"1"},{"2"},{"3"},{"4"},{"5"}});
		assertEquals(true, Labyrinth.pathsWithSameNames(ex0, 0));
		assertEquals(true, Labyrinth.pathsWithSameNames(ex0, 1));
		assertEquals(false, Labyrinth.pathsWithSameNames(ex0, 2));
	}
	
	@Test
	public void testPathsWithSameNames2() {
		Room ex1 = createDenseLabyrinth(new String[][] {{"1"},{"2"},{"3", "3"},{"4"},{"5"}});
		assertEquals(true, Labyrinth.pathsWithSameNames(ex1, 2));
		assertEquals(false, Labyrinth.pathsWithSameNames(ex1, 3));	
	}
	
	@Test
	public void testPathsWithSameNames3() {
		Room ex2 = createDenseLabyrinth(new String[][] {{"1"},{"2"},{"3", "4"},{"4"},{"5"}});
		assertEquals(true,  Labyrinth.pathsWithSameNames(ex2, 1));
		assertEquals(false, Labyrinth.pathsWithSameNames(ex2, 2));
	}
	
	@Test
	public void testPathsWithSameNames4() {
		Room ex3 = createDenseLabyrinth(new String[][] {{"1"},{"3", "4"},{"4", "3"}});
		assertEquals(true, Labyrinth.pathsWithSameNames(ex3, 2));
		assertEquals(false, Labyrinth.pathsWithSameNames(ex3, 3));
	}
	
	@Test
	public void testPathsWithSameNames5() {
		Room ex4 = createDenseLabyrinth(new String[][] {{"1"},{"2","2"},{"5","5"}});
		assertEquals(true, Labyrinth.pathsWithSameNames(ex4, 3));
	}
	
	/* 
	 * Eine Hilfsfunktion, welche Labyrinthe erstellt. 
	 * Es gibt genau einen Raum fuer jeden Eintrag in `plan`.
	 * `matrix[i][j]` enthaelt den Namen vom entsprechenden Raum.
	 * Der Raum fuer `matrix[i][j]` hat Tueren zu `matrix[i+1][k]` fuer jedes k mit 0 <= k < matrix[i+1].length.
	 * 
	 * */
	static Room createDenseLabyrinth(String[][] plan) {
		Room[] nexts = new Room[0];
		
		for (int i = plan.length-1; i >= 0; i -= 1) {
			Room[] rooms = new Room[plan[i].length];
			for (int j = 0; j < plan[i].length; j += 1) {
				rooms[j] = new Room(plan[i][j], Arrays.copyOf(nexts, nexts.length));
			}
			nexts = rooms;
		}
		
		if (nexts.length > 0) { return nexts[0]; }
		else { return null; }
	}
}
