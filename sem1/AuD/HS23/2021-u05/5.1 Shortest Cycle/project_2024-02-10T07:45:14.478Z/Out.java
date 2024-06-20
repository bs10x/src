import java.io.*;
import java.util.Scanner;


/** Simple output to the console and to files.
 <p>Copyright (c) 2005 Hanspeter Moessenboeck, University of Linz</p>
 <p>Copyright (c) 2018 ETH Zurich</p>

 <p>This class is free software; you can redistribute it and/or modify it
 under the terms of the GNU General Public License as published by the
 Free Software Foundation; either version 2, or (at your option) any
 later version.</p>

 <p>This class is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 or FITNESS FOR A PARTICULAR PURPOSE.  See the <a href="http://www.gnu.org/copyleft/gpl.html">
 GNU General Public License</a> for more details.</p>
 <hr>

 <p>This class allows printing formatted data either to the console
 or to a file. It is intended to be used in an introductory
 programming course when classes, packages and exceptions are unknown
 at the beginning. To use it, simply copy Out.class into the
 current directory. </p>

 <p>All output goes to the current output file, which is initially
 the console. Opening a file with open() makes it the new current
 output file. Closing a file with close() switches back to the previous
 output file.</p>
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class Out {
  
  private static PrintStream out;
  private static PrintStream[] stack;
  private static int sp;
  private static boolean done;
  private static Scanner compareToScanner;
  private static ByteArrayOutputStream compareToStream;
  private static double error_eps;
  private static boolean comparing_to = false;
  private static boolean out_initialized = false;
  private static int cur_line = 0;
  
  /** Return true if the previous Out operation was
   successful, otherwise return false. */
  public static boolean done() {
      return done && ! getOut().checkError();
  }
  
  /** Print the boolean value b either as "true" or "false". */
  public static void print(boolean b) { getOut().print(b); }
  
  /** Print the character value c. */
  public static void print(char s) { getOut().print(s); }
  
  /** Print the integer value i. */
  public static void print(int i) { getOut().print(i); }
  
  /** Print the long value l. */
  public static void print(long l) { getOut().print(l); }
  
  /** Print the float value f. */
  public static void print(float f) { getOut().print(f); }
  
  /** Print the float value f, rounded to `digits` decimal digits. */
  public static void print(float f, int digits) { getOut().printf("%." + digits + "f", f); }
  
  /** Print the double value d. */
  public static void print(double d) { getOut().print(d); }
  
  /** Print the double value d, rounded to `digits` decimal digits */
  public static void print(double d, int digits) { getOut().printf("%." + digits + "f", d); }
  
  /** Print the character array a. */
  public static void print(char[] a) { getOut().print(a); }
  
  /** Print the String s. */
  public static void print(String s) { getOut().print(s); }
  
  /** Print the Object o as resulting from String.valueOf(o). */
  public static void print(Object o) { getOut().print(o); }
  
  /** Terminate the current line by writing a line separator string.
   On windows this is the character sequence '\r' and '\n' */
  public static void println() { getOut().println(); newLinePrinted();}
  
  /** Print the boolean value b and terminate the line. */
  public static void println(boolean b) { getOut().println(b); newLinePrinted();}
  
  /** Print the character value c and terminate the line. */
  public static void println(char s) { getOut().println(s); newLinePrinted();}
  
  /** Print the integer value i and terminate the line. */
  public static void println(int i) { getOut().println(i); newLinePrinted();}
  
  /** Print the long value l and terminate the line. */
  public static void println(long l) { getOut().println(l); newLinePrinted();}
  
  /** Print the float value f and terminate the line. */
  public static void println(float f) { getOut().println(f); newLinePrinted();}
  
  /** Print the float value f, rounded to `digits` decimal digits, and terminate the line. */
  public static void println(float f, int digits) { getOut().printf("%." + digits + "f\n", f); newLinePrinted();}
  
  /** Print the double value d and terminate the line. */
  public static void println(double d) { getOut().println(d); newLinePrinted();}
  
  /** Print the double value d, rounded to `digits` decimal digits, and terminate the line. */
  public static void println(double d, int digits) { getOut().printf("%." + digits + "f\n", d); newLinePrinted();}
  
  /** Print the character array a and terminate the line. */
  public static void println(char[] a) { getOut().println(a); newLinePrinted();}
  
  /** Print the String s and terminate the line. */
  public static void println(String s) { getOut().println(s); newLinePrinted();}
  
  /** Print the Object o as resulting from String.valueOf(o)
   and terminate the line. */
  public static void println(Object o) { getOut().println(o); newLinePrinted();}
  
  /** Open the file with the name fn as the current output file.
   All subsequent output goes to this file until it is closed.
   The old output file will be restored when the new output file is closed. */
  @SuppressWarnings("SameParameterValue")
  public static void open(String fn) {
    if (comparing_to) {
      System.err.println("You can't call open() after calling compareTo()");
      System.err.println("IGNORING CALL TO open()");
      return;
    }
    
    if ("submit".equalsIgnoreCase(System.getenv("ACTION")) ||
        "test".equalsIgnoreCase(System.getenv("ACTION"))) return;
    try {
      PrintStream s = new PrintStream(new FileOutputStream(fn));
      stack[sp++] = getOut();
      out = s;
    } catch (Exception e) {
      done = false;
    }
  }
  
  /** Compare the output of the program, line by line the given file **/
  public static void compareTo(String fn) {
    // Don't compare to a file in a non-interactive mode
    if (!"run".equalsIgnoreCase(System.getenv("ACTION")))
      return;
      
    if (out_initialized) {
      System.err.println("You have to call compareTo() before any other call to Out");
      System.err.println("IGNORING CALL TO compareTo()");
      return;
    }
    
    out_initialized = true;
    
    try {
      compareToScanner = new Scanner(new File(fn));
      compareToStream = new ByteArrayOutputStream();
      PrintStream s = new PrintStream(compareToStream);
      stack[sp++] = getOut();
      out = s;
      comparing_to = true;
    } catch (Exception e) {
      System.err.println("Couldn't open file, check you spelled the path properly");
    }
  }
  
  /** Call this function everytime a new line is printed. If compareTo is used
  it will compare the last printed line to the expected one. Otherwise, it 
  does nothing**/
  public static void newLinePrinted() {
    if (!comparing_to) return;
    String expected;
    if (compareToScanner.hasNextLine()) {
      expected = compareToScanner.nextLine();
    } else {
      expected = "";
    }
    
    
    String output;
    try{
      output = compareToStream.toString("UTF8");
      String lines[] = output.split("\\r?\\n");
      output = lines[lines.length - 1];
    } catch (UnsupportedEncodingException e) {
      // no worry, UTF-8 is fine.
      output = "Contact your TA if you see this message!";
    }
    if (isMatch(expected, output)) { System.out.print("\033[32m");  // GREEN
    } else {                         System.out.print("\033[31m");} //RED
    
    System.out.print("(" + cur_line + ") Expected:      ");
    System.out.println(expected);
    System.out.print("(" + cur_line + ") Your output:   ");
    System.out.print(output);
    System.out.println("\033[0m"); // Reset default text color
    cur_line++;
    //System.out.println();
  }
    
  /** Set epsilon for being able to use compareTo **/
  public static void setEpsilon(double eps) { error_eps = eps;}
  
  /** Compare the expected and actual output, using the right comparison fucntion **/
  private static boolean isMatch(String expected, String actual){
        if (error_eps > 0) { return isSimilarEnough(expected, actual);}
        else {               return isExactMatch(expected, actual); }
    }

  /** Compares two lines, when exact output is expected**/
  private static boolean isExactMatch(String expectedLine, String actualLine) {
      String e = expectedLine.trim();
      String a = actualLine.trim();
      e = e.replaceAll("\\s+", " ");
      a = a.replaceAll("\\s+", " ");
      return a.equals(e);
  }
    
  /** Compare two lines of doubles given the maximum allowed error (error_eps)**/
  private static boolean isSimilarEnough(String expected, String actual) {
        Scanner expectedScanner = new Scanner(expected);
        Scanner actualScanner = new Scanner(actual);
        
        while(expectedScanner.hasNextDouble()) {
            double exp_double = expectedScanner.nextDouble();
            
            if (!actualScanner.hasNextDouble()) return false;
            double act_double = actualScanner.nextDouble();
            
            if (Double.isNaN(act_double)) return false;
            
            if (Double.isInfinite(exp_double) || Double.isInfinite(act_double)) {
                if (exp_double != act_double) return false;
            }
            
            if (Math.abs(exp_double - act_double) > error_eps &&
                Math.abs(exp_double - act_double) / Math.abs(exp_double) > error_eps) {
                return false;        
            }
        }
        
        if (actualScanner.hasNextDouble()) return false; // superflous output at the end
        
        return true;
    }
  
  /** Close the current output file.
   The previous output file is restored and becomes the current output file. */
  public static void close() {
    getOut().flush();
    getOut().close();
    if (sp > 0) out = stack[--sp];
  }
  
  private static PrintStream getOut(){
    if (!out_initialized){
      initialize();
    }
    return out;
  }
  
  public static void initialize() {
    out_initialized = true;
    try{
      out = new PrintStream(System.out, true, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      // no worry, UTF-8 is fine.
    }
    done = true;
  }
  
  static { // initializer
    //initialize();
    stack = new PrintStream[8];
    sp = 0;
  }

}