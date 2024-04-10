import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Hotellerie {

	public static void main(String[] args) throws FileNotFoundException {
		
		// Sie koennen den Inhalt von main aendern
		
		Scanner scanner = new Scanner(new File("hotelDaten1.txt"));
		PrintStream output = new PrintStream(System.out);
		
		analyse(scanner, output);
	}
	
	public static void analyse(Scanner input, PrintStream output) throws FileNotFoundException {
		// TODO: Lesen Sie das Dateiinhalt von input und geben Sie Ihre Loesung nach output aus.
		
		// Ihr Code kommt hier hin
		
		// Ersetzen Sie null im unteren Code mit Ihren entsprechenden Loesungen
		output.println("Am haeufigsten gebucht: " + null);
		output.println("Am meisten besetzt: " + null);
		output.println("Groessten Betrag eingebracht: " + null);
		output.println("Gesamteinnahmen des Hotels: " + null); 
	}
	
	

}
