import java.util.Scanner;


public class Rechner {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		parse(scanner);
	}
	
	public static Expr parse(String input) {
		return parse(new Scanner(input));
	}

	public static Expr parse(Scanner input) {
		// TODO: Implementieren Sie diese Methode wie auf dem Aufgabenblatt gefordert
		return null;
	}

}
