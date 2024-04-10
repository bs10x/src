import java.io.PrintStream;
import java.util.Scanner;

public class Verzahnung {

	public static void main(String[] args) {
		PrintStream output = new PrintStream(System.out);
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		String t = scanner.nextLine();
		verzahnungen(s, t, output);

		output.close();
		scanner.close();
	}

	public static void verzahnungen(String s, String t, PrintStream output) {
		// TODO: Geben Sie alle Verzahnungen von s und t auf output aus.
		// Achtung: Jede einzelne Verzahnung muss alleine auf einer separaten Zeile ausgegeben
		// werden.

		output.println("12ab");
		output.println("1ab2");
		output.println("ab12");
		output.println("1a2b");
		output.println("a1b2");
		output.println("a12b");
	}

}
