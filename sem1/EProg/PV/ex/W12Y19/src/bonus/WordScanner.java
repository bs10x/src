package bonus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

/** Wrapper fuer die Scanner Klasse, welche Satzzeichen aus den geparsten Woertern entfernt */
public class WordScanner {

	private Scanner scanner;
	
	public WordScanner(File source) throws FileNotFoundException {
        scanner = new Scanner(source);
        scanner.useLocale(Locale.US);
    }
	
	public WordScanner(InputStream source) {
		scanner = new Scanner(source);
	}
	
	public WordScanner(Readable source) {
		scanner = new Scanner(source);
	}
	
	/** Gibt 'true' zurueck, falls noch ein Wort gelesen werden kann. */
	boolean hasNext() {
		return scanner.hasNext();
	}
	
	/** Gibt das naechste gescannte Wort zurueck, wobei Satzzeichen aus dem Wort entfernt werden und Grossbuchstaben in Kleinbuchstaben geaendert werden. */
	String next() {
		String nextWord = scanner.next();
		String wordWithoutPunctation = nextWord.replaceAll("[^a-zA-Z]", "").toLowerCase();
		return wordWithoutPunctation;		
	}
}
