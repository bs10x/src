import java.util.Scanner;

/* 
 * Author: Remi Meier, Michael Faes
 * for Einführung in die Programmierung
 * 
 * A program that evaluates expressions the user enters.
 */
public class EvaluatorApp {

    public static void main(String[] args) {
        System.out.print("Enter a value for x: ");
        Scanner scanner = new Scanner(System.in);

        double xValue = scanner.nextDouble();
        scanner.nextLine();
        ExprEvaluator evaluator = new ExprEvaluator(xValue);

        while (true) {
            System.out.print(">> ");
            String function = scanner.nextLine();
            if (function.equals("exit"))
                break;
            try {
                double result = evaluator.eval(function);
                System.out.println(result);
            } catch (EvaluationException e) {
                System.out.println("sdf " + e.getMessage());
            }
        }
    }
}
