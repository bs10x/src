import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* 
 * Author: Maximiliana Muster
 * für Einführung in die Programmierung
 * 
 * Liest eine Höhenserie aus einer Datei und lokalisiert das versunkeste Tal
 */

public class Tal {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("gipfelhoehen.txt"));
		int[] hoehen = liesHoehen(scanner);
		scanner.close();

		System.out.println("Tiefste Versenkung: " + findeGroessteVertiefung(hoehen) + " m");
	}

	/**
	 * Lies die Höhen aus dem übergebenen Scanner.
	 */
	static int[] liesHoehen(Scanner scanner) {
		int[] hoehen = new int[scanner.nextInt()];
		for (int i = 0; i < hoehen.length; i++) {
			hoehen[i] = scanner.nextInt();
		}
		return hoehen;
	}

	/**
	 * Sucht im Array 'hoehen' nach der grössten "Vertiefung" und gibt diese zurück  
	 */
	static int findeGroessteVertiefung(int[] hoehen) {
	    // TODO
		return 0;
	}

}
