import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
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
  
  List<Bill> bills;

  /**
   * Ändern Sie nicht die Signatur der Methode
 * @throws IllegalFileFormatException 
   */
  public void process(Scanner input, PrintStream output) throws IllegalFileFormatException {
    bills = new LinkedList<>();
    Tariff tariff = null;
    
    while (input.hasNextLine()) {
    	String[] line = input.nextLine().split(" ");
    	if (line.length > 0) {
    		if (line[0].equals("Tarif")) {
    			if (line.length > 1) {
    				int number = readPositiveInt(line[1])*2;
    				int[] tariffDef = new int[number];
    	    		for (int i = 0, j = 2; i < number && j < line.length; i += 1, j += 1) {
    	    			tariffDef[i] = readPositiveInt(line[j]);
    	    		}
    	    		tariff = new Tariff(tariffDef);
    			}
    		} else if (isPositiveInt(line[0])) {
    			int id = readPositiveInt(line[0]);
    			int sum = 0; 
    			for (int i = 1; i < line.length; i += 1) {
    				sum += readPositiveInt(line[i]);
    			}
    			bills.add(new Bill(id, tariff.calculateDebt(sum)));
    		}
    	}
    }
    
    for (Bill bill : bills) {
    	output.println(bill.getCustomerId() + " " + bill.getDebt());
    }
  }
  
  public int query(int lowB, int upB) {
	  int maxDebt = lowB-1;
	  int maxID = -1;
	  
	  for (Bill bill : bills) {
		  if (lowB <= bill.getDebt() && maxDebt < bill.getDebt() && bill.getDebt() <= upB) {
			  maxDebt = bill.getDebt();
			  maxID = bill.getCustomerId();
		  }
	  }
	  
	  return maxID;
  }
  
  static private int readPositiveInt(String string) throws IllegalFileFormatException {
	  if (!isPositiveInt(string)) { throw new IllegalFileFormatException("Illegal file format."); }
	  return Integer.valueOf(string);
  }
  
  static private boolean isPositiveInt(String string) {
	  try {
		  int parsed = Integer.valueOf(string);
		  return parsed >= 0;
	  } catch (Exception e) {
		  return false;
	  }
  }
}