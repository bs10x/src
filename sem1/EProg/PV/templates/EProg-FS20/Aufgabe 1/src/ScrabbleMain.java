import java.util.Scanner;

public class ScrabbleMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name, please. ");
        String name = scanner.nextLine();

        Scrabble.drawNameSquare(name);
        
        scanner.close();
    }
}
