import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillsTest {

  @Test
  public void testProcess() throws FileNotFoundException, IllegalFileFormatException {
    Scanner input = new Scanner(new File("Data.txt"));

    File outputFile = new File("Output.txt");
    PrintStream output = new PrintStream(outputFile);

    Bills bills = new Bills();
    bills.process(input, output);

    input.close();
    output.close();

    Scanner scanner = new Scanner(outputFile);

    assertTrue(scanner.hasNextLine());
    assertEquals("345 0", scanner.nextLine());

    assertTrue(scanner.hasNextLine());
    assertEquals("346 10", scanner.nextLine());

    assertTrue(scanner.hasNextLine());
    assertEquals("456 15", scanner.nextLine());

    assertTrue(scanner.hasNextLine());
    assertEquals("789 20", scanner.nextLine());

    assertTrue(scanner.hasNextLine());
    assertEquals("110 0", scanner.nextLine());

    assertTrue(scanner.hasNextLine());
    assertEquals("109 26", scanner.nextLine());

    assertTrue(scanner.hasNextLine());
    assertEquals("112 70", scanner.nextLine());

    assertTrue(scanner.hasNextLine());
    assertEquals("113 70", scanner.nextLine());

    assertTrue(scanner.hasNextLine());
    assertEquals("114 70", scanner.nextLine());

    assertTrue(scanner.hasNextLine());
    assertEquals("116 600", scanner.nextLine());

    assertTrue(scanner.hasNextLine());
    assertEquals("117 400", scanner.nextLine());

    assertTrue(scanner.hasNextLine());
    assertEquals("118 1552", scanner.nextLine());

    assertFalse(scanner.hasNextLine());

    scanner.close();
    
    assertEquals(456, bills.query(10, 19));
    
    assertEquals(789, bills.query(10, 20));
  }
}
