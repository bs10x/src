import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Bills {

  public static void main(String[] args) throws FileNotFoundException {
    String inputFile = "Data.txt";
    Scanner input = new Scanner(new File(inputFile));
    PrintStream output = new PrintStream(System.out);

    //String outputFile = "Output.txt";
    //PrintStream output = new PrintStream(new File(outputFile));
    
    process(input, output);  
    
    input.close();
    output.close();
  }

  public static void process(Scanner input, PrintStream output) {
	  int intervals = 0;
      int[] intervalLimits = null;
      int[] intervalPrices = null;
	  
	  while(input.hasNext()) {
		  String token = input.next();
		  		  
		  if(token.equals("Tarif")) {
			  intervals = input.nextInt();
			  intervalLimits = new int[intervals];
			  intervalPrices = new int[intervals];
			  
			  for (int i = 0; i < intervals; i++) {
                  intervalLimits[i] = input.nextInt();
                  intervalPrices[i] = input.nextInt();
              }
		  }
		  else {
			  int ID = Integer.parseInt(token);
			  int fiscalConsumption = 0;
			  
			  for(int i = 0; i < 4; i++) {
				  fiscalConsumption += input.nextInt();
			  }
			  
			  output.println(ID + " " + Math.round(Math.ceil(calculateBill(fiscalConsumption, intervalLimits, intervalPrices))));
		  }   
	  }	  
  }
  
  private static double calculateBill(int fiscalConsumption, int[] intervalLimits, int[] intervalPrices) {
      double bill = 0;

      for (int i = 0; i < intervalLimits.length; i++) {
          if (fiscalConsumption <= intervalLimits[i]) {
              bill += fiscalConsumption * intervalPrices[i] / 100.0;
              break;
          }
          else {
              bill += intervalLimits[i] * intervalPrices[i] / 100.0;
              fiscalConsumption -= intervalLimits[i];
          }
      }
      return bill;
  }
}	

