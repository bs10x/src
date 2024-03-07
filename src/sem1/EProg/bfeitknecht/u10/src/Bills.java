import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Bills {

	 public static void main(String[] args) throws FileNotFoundException, IllegalFileFormatException {
			String inputFile = "Data.txt";
			Scanner input = new Scanner(new File(inputFile));
			PrintStream output = new PrintStream(System.out);

			Bills bills = new Bills();
			bills.process(input, output);

			input.close();
			    
			System.out.println("Query(10,19): " + bills.query(10, 19));
		  }

	 /**
	   * Ändern Sie nicht die Signatur der Methode
	   */
	  public void process(Scanner input, PrintStream output) {
		  // TODO Lesen Sie das File von input und geben Sie Ihre Lösung nach output aus.
	  }
	  
	  public int query(int lowB, int upB) {
		  // TODO
		  return -1;
	  }
}