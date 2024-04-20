import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		// Sie koennen die main Methode beliebig aendern.
		
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

		List<Room> res0 = Labyrinth.exits(N[0], new LinkedList<>(List.of()));
		System.out.println(res0.size());
		
		List<Room> ex0 = new LinkedList<Room>(List.of(N[0],N[5],N[1]));
		System.out.println(Arrays.toString(ex0.toArray()));
		Labyrinth.sortRooms(ex0);
		System.out.println(Arrays.toString(ex0.toArray()));
		
		Room r0 = new Room("2");
		Room r1 = new Room("1", new Room [] {r0});
		
		System.out.println(Labyrinth.pathsWithSameNames(r1, 1));
	}
	
}
