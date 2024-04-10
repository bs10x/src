package bonus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class WordService {

	public static void main(String[] args) throws FileNotFoundException {
		WordScanner input = new WordScanner(new File("loremIpsum.txt"));
		
		WordService service = new WordService(input);
		
		for (MyWord word : service.getWords()) {
			System.out.println(word);
		}
		
		System.out.println("----------------------------------");
		
	    List<MyWord> topWords = service.top(3);
	    
	    for (MyWord word : topWords) {
			System.out.println(word);
		}
	}
	
	
	public WordService(WordScanner scanner) {
		// TODO: lesen Sie den Text von dem Scanner und initialisieren Sie das Objekt
	}
	
	public List<MyWord> getWords() {
		// TODO: geben Sie eine Liste mit den Woertern des Textes aus
		return null;
	}
	
	public List<MyWord> top(int number) {
		// TODO: geben Sie die number (Anzahl) groessten Woerter zurueck. 
		//       Wobei Groesse durch die definierte Ordnung aus dem Aufgabenblatt bestimmt ist.
		return null;
	}
}
