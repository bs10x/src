import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Autor: Remi Meier, Michael Faes
 * für Einführung in die Programmierung I, HS 2016
 *
 * Ein Ratespiel, wo ein Spieler ein vom Computer zufällig gewähltes Wort erraten muss. Es wird
 * verglichen, wie viele Versuche verschiedene Arten von Spieler (durchschnittlich) benötigen.
 */
public class RateSpielApp {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("woerter.txt"));
        String[] woerter = liesWoerter(scanner);
        scanner.close();

        Computer computer = new Computer();
        Spieler[] spieler = {
            new ZufallsWortSpieler(),
            new ZufallsWortSpielerMitGedaechtnis(),
            new BuchstabenSpieler(),
            new HaeufigeBuchstabenSpieler(),
            new SimulierenderBuchstabenSpieler(),
            new SimulierenderNGrammSpieler()
        };
        RateSpiel spiel = new RateSpiel(woerter, computer, spieler, 100, false);

        double[] durchschnitt = spiel.nSpiele(1000);

        for (int s = 0; s < spieler.length; s++)
            System.out.println(spieler[s].name() + " hat durchschnittlich " + durchschnitt[s]
                + " Versuche benötigt.");
    }

    /**
     * Liest eine Liste von Wörtern von einem Scanner in ein Array ein.
     */
    static String[] liesWoerter(Scanner scanner) {
        String[] woerter = new String[scanner.nextInt()];
        for (int i = 0; i < woerter.length; i++)
            woerter[i] = scanner.next();
        return woerter;
    }
}
