import java.util.Scanner;

public class EvaluatorApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(">> ");
            String function = scanner.nextLine();
            if (function.equals("exit"))
                break;
            try {
                new ExprParser().parse(function);
                System.out.println("Valid expression!");
            } catch (EvaluationException e) {
                System.out.println("Invalid expression! " + e.getMessage());
            }
        }
    }
}
