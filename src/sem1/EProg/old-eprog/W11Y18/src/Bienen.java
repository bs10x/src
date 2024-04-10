import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Bienen {
	
	public static void main(String[] args) throws FileNotFoundException {
		String dateiName = "bienen.txt";
		Scanner scanner = new Scanner(new File(dateiName));
		PrintStream output = new PrintStream(System.out);
		
		analyze(scanner, output);
		
		output.close();
		scanner.close();
	}
	
	/**
	 * Ändern Sie nicht die Signatur der Methode
	 */
	public static void analyze(Scanner input, PrintStream output) {
		// TODO: Lesen Sie das File von input und geben Sie Ihre Lösung nach output aus.
		
	}
}
