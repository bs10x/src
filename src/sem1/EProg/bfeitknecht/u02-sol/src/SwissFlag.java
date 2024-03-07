/*
 * Author: Maximiliana Muster
 * für Einfuehrung in die Programmierung, HS 2017
 * 
 * Das Programm gibt die Schweizer Flagge auf der Konsole aus.
 */
public class SwissFlag {

	public static void main(String[] args) {
		printLine();
		printEmptyPart();
		printNarrowPart();
		printWidePart();
		printNarrowPart();
		printEmptyPart();
		printLine();
	}

	public static void printLine() {
		System.out.println("-----------------------------------");
	}

	public static void printEmptyPart() {
		System.out.println("|                                 |");
		System.out.println("|                                 |");
	}

	public static void printNarrowPart() {
		System.out.println("|             +++++++             |");
		System.out.println("|             +++++++             |");
		System.out.println("|             +++++++             |");
	}

	public static void printWidePart() {
		System.out.println("|      +++++++++++++++++++++      |");
		System.out.println("|      +++++++++++++++++++++      |");
		System.out.println("|      +++++++++++++++++++++      |");
	}
}
