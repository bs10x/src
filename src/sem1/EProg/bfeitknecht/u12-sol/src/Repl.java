
import java.util.Map.Entry;
import java.util.Scanner;

/* 
 * Author: Remi Meier, Michael Faes
 * for Einführung in die Programmierung
 * 
 * A program that repeatedly evaluates programs entered by the user.
 */
public class Repl {

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        
        while(true) {
            System.out.print(">> ");
            String program = new Scanner(System.in).nextLine();
            if(program.equals("exit"))
                break;
            
            try {
                interpreter.interpret(program);
            } catch(ProgramException e) {
                System.out.println(e.getMessage());
            }
            
            for(Entry<String, Double> entry : interpreter.getVars().entrySet())
                System.out.println("    " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
