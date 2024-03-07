import java.util.Random;
import java.util.Scanner;
import gui.Window;

/* 
 * Author: Maximilian Muster
 * für Einführung in die Programmierung
 * 
 * Simuliert einen Wolf, der versucht aus einer Stadt zu entkommen. Dabei
 * darf er aber jede Kreuzung maximal 1x besuchen.
 */
public class RandomWalks {

	private static final int SIZE = 500;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Stadtgrösse (ungerade Anzahl Strassen)? ");
		int n = scanner.nextInt();
		System.out.println("Anzahl Simulationen?");
		int simulations = scanner.nextInt();

		if (n <= 1 || n % 2 == 0) {
			System.out.println("Ungültige Stadtgrösse.");
		} else if (simulations < 1) {
			System.out.println("Ungültige Anzahl Simulationen.");
		} else {
			RandomWalks walker = new RandomWalks();
			Window win = new Window("Wolf", SIZE, SIZE);
			win.open();

			// do simulations
			WalkResult[] results = new WalkResult[simulations];
			for (int i = 0; i < simulations; i++) {
				results[i] = walker.walk(n);

				if (results[i].successful) {
					win.setColor(0, 255, 0);
				} else {
					win.setColor(255, 0, 0);
				}
				walker.draw(win, n, results[i]);
			}

			walker.printStatistics(results);
			win.waitUntilClosed();
		}

	}

	/**
	 * Gibt einige Statistiken über alle simulierten Wege aus.
	 */
	void printStatistics(WalkResult[] walks) {
		double successRate;
		double avgSuccPathLength = 0;
		double avgTrapPathLength = 0;

		int successes = 0, failures = 0;
		for (int i = 0; i < walks.length; i++) {
			if (walks[i].successful) {
				successes++;
				avgSuccPathLength += walks[i].pathLength;
			} else {
				failures++;
				avgTrapPathLength += walks[i].pathLength;
			}
		}
		avgSuccPathLength /= successes;
		avgTrapPathLength /= failures;
		successRate = (double) successes / (successes + failures);

		System.out.format("Fluchterfolgsrate: %.2f %%\n", successRate * 100);
		System.out.format("Ø-Fluchtpfadlänge: %.2f\n", avgSuccPathLength);
		if (failures > 0)
			System.out.format("Ø-Todespfadlänge: %.2f\n", avgTrapPathLength);
	}

	/**
	 * Führt einen Random-Walk in einer Stadt der Grösse 'n' durch. Dabei wird ein WalkResult-Objekt
	 * zurückgegeben, welches die Länge des zurückgelegten Pfades enthält, sowie ob der Wolf
	 * erfolgreich entkam.
	 */
	WalkResult walk(int n) {
		Random rand = new Random();
		CityGrid grid = new CityGrid(n);

		int posX = grid.getCentreX();
		int posY = grid.getCentreY();
		grid.mark(posX, posY);

		int pathLength = 1;
		boolean trapped = false;
		LinkedIntList directions = new LinkedIntList();
		do {
			int[] dirs = grid.unmarkedDirectionsFrom(posX, posY);
			if (dirs.length > 0) {
				int decision = rand.nextInt(dirs.length);
				int chosenDirection = dirs[decision];

				int[] dxs = { 0, 1, 0, -1 };
				int[] dys = { -1, 0, 1, 0 };
				posX += dxs[chosenDirection];
				posY += dys[chosenDirection];

				grid.mark(posX, posY);
				directions.addLast(chosenDirection);

				pathLength++;
			} else {
				trapped = true;
			}
		} while (!trapped && !grid.isAtBorder(posX, posY));

		return new WalkResult(!trapped, pathLength, directions);
	}

	/**
	 * Zeichnet den Pfad, der im 'WalkResult' gespeichert ist auf ein 'Window'
	 */
	void draw(Window win, int n, WalkResult res) {
		CityGrid grid = new CityGrid(n);
		int posX = grid.getCentreX();
		int posY = grid.getCentreY();

		int factor = (int) (Math.min(win.getHeight(), win.getWidth()) / (n - 1));

		LinkedIntList directions = res.directions;
		for (int i = 0; i < directions.size; i++) {
			int[] dxs = { 0, 1, 0, -1 };
			int[] dys = { -1, 0, 1, 0 };
			int newPosX = posX + dxs[directions.get(i)];
			int newPosY = posY + dys[directions.get(i)];

			win.drawLine(posX * factor, posY * factor, 
					newPosX * factor, newPosY * factor);
			win.refresh();

			posX = newPosX;
			posY = newPosY;
		}
	}
}

/**
 * Eine Klasse, die ein Netz von Strassen, bzw. Kreuzungen verwaltet.
 */
class CityGrid {
	private int n;
	private boolean[][] junctions;

	public CityGrid(int n) {
		if (n > 2 && n % 2 == 0)
			throw new RuntimeException("CityGrid requires uneven size.");

		this.n = n;
		this.junctions = new boolean[n][n];
	}

	/**
	 * Gibt zurück, ob sich die xy-Koordinaten am Stadtrand befindet.
	 */
	boolean isAtBorder(int x, int y) {
		return x == n - 1 || y == n - 1 || x == 0 || y == 0;
	}

	/**
	 * Gibt die X-Koordinate des Stadtzentrums zurück.
	 */
	int getCentreX() {
		return n / 2;
	}

	/**
	 * Gibt die Y-Koordinate des Stadtzentrums zurück.
	 */
	int getCentreY() {
		return n / 2;
	}

	/**
	 * Gibt ein int-Array zurück, welches alle unmarkierten Richtungen erhält, die der Wolf von
	 * (x,y) aus besuchen kann.
	 */
	int[] unmarkedDirectionsFrom(int x, int y) {
		// count unmarked directions
		int freeCount = 0;
		int[] dxs = { 0, 1, 0, -1 };
		int[] dys = { -1, 0, 1, 0 };

		for (int i = 0; i < dxs.length; i++) {
			int nx = x + dxs[i];
			int ny = y + dys[i];
			if (validGridIndices(nx, ny) && !junctions[nx][ny])
				freeCount++;
		}

		// create coordinate array of unmarked junctions
		int[] res = new int[freeCount];
		int j = 0;
		for (int dir = 0; dir < dxs.length; dir++) {
			int nx = x + dxs[dir];
			int ny = y + dys[dir];
			if (validGridIndices(nx, ny) && !junctions[nx][ny]) {
				res[j] = dir;
				j++;
			}
		}

		return res;
	}

	/**
	 * Überprüft, ob x und y valide Indices von 'junctions' sein können.
	 */
	private boolean validGridIndices(int x, int y) {
		return x >= 0 && y >= 0 && x < junctions.length && y < junctions[x].length;
	}

	/**
	 * Markiert die Kreuzung, die durch (x,y) spezifiziert ist, als besucht.
	 */
	void mark(int x, int y) {
		if (junctions[x][y])
			throw new RuntimeException("junction already marked!");
		junctions[x][y] = true;
	}
}

/**
 * Klasse, die das Resultat eines Random-Walks enthält (Pfadlänge und ob der Pfad aus der Stadt
 * führt).
 */
class WalkResult {
	boolean successful;
	int pathLength;
	LinkedIntList directions;

	WalkResult(boolean successful, int pathLength, LinkedIntList directions) {
		this.successful = successful;
		this.pathLength = pathLength;
		this.directions = directions;
	}
}
