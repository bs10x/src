import static org.junit.Assert.*;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

public class HotellerieTest {
	
	// Sie koennen alles in dieser Datei aendern
    
	@Test
	public void gleicherTestHintereinander() throws FileNotFoundException {
		
		String dateiName = "hotelDaten1.txt";
		String erwarteterOutput = erwarteterOutputString(1, 42, 256, 22000.75);
		
        assertEquals(erwarteterOutput, runAnalyse(dateiName));
        assertEquals(erwarteterOutput, runAnalyse(dateiName));
	}
	
	/** Fuehrt die analyse Methode auf der gegebenen Datei aus und returned den Output als String */
	public static String runAnalyse(String dateiName) throws FileNotFoundException {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream output = new PrintStream(bytes);
		Scanner input = new Scanner(new File(dateiName));
		
		Hotellerie.analyse(input, output);
		String resultat = bytes.toString();
		
		input.close();
		output.close();
		
        return resultat;
	}
	
	/** Returned den String, welcher dem erwarteten Output mit den gegebenen Ergebnissen entspricht */
	public static String erwarteterOutputString(int haeufigste, int meiste, int reichste, double summe) {
		return "Am haeufigsten gebucht: " + haeufigste + System.lineSeparator() +
				"Am meisten besetzt: " + meiste + System.lineSeparator() +
				"Groessten Betrag eingebracht: " + reichste + System.lineSeparator() +
				"Gesamteinnahmen des Hotels: " + summe + System.lineSeparator();
	}
}
