import java.util.Scanner;

/**
 * Ein {@link Spieler}, welcher mit Benutzer-Eingaben über die Konsole gesteuert wird.
 */
public class KonsolenSpieler extends Spieler {

    private Scanner scanner;
    private String name;

    public KonsolenSpieler() {
        scanner = new Scanner(System.in);
        System.out.print("Wie heissen wir? ");
        name = scanner.nextLine();
    }

    public void neuesSpiel(String[] verwendeteWoerter) {
        System.out.println("Zur Info: hier sind die möglichen Wörter:");
        for (int i = 0; i < verwendeteWoerter.length; i++)
            System.out.print(verwendeteWoerter[i] + " ");
        System.out.println();
    }

    public String gibTipp() {
        System.out.print("Tipp von " + name() + "? ");
        return scanner.next();
    }

    public void bekommeHinweis(String tipp, String hinweis) {
        System.out.println("Das Wort " + hinweis + " \"" + tipp + "\"!");
    }

    public String name() {
        return name;
    }
}
