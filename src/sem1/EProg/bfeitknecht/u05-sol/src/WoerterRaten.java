import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/* 
 * Author: Maximilian Muster
 * für Einführung in die Programmierung
 * 
 * Ein Rate-Spiel wo ein Spieler ein zufällig gewähltes Wort erraten muss.
 */
public class WoerterRaten {
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("woerter.txt"));
        String[] woerter = liesWoerter(scanner);
        scanner.close();
        
        rateSpiel(woerter);
    }
    
    /**
     * Liest die Wörter von dem gegebenen Scanner ein und gibt sie als Array zurück.
     */
    static String[] liesWoerter(Scanner scanner) {
        String[] woerter = new String[scanner.nextInt()];
        for(int i = 0; i < woerter.length; i++) {
            woerter[i] = scanner.next();
        }
        return woerter;
    }
    
    /**
     * Führt das Rate-Spiel einmal durch.
     */
    static void rateSpiel(String[] woerter) {
        String wort = zufallsWort(woerter);
        
        Scanner scanner = new Scanner(System.in);
        String hinweis;
        int versuche = 0;
        do {
            System.out.print("Tipp? ");
            String tipp = scanner.next();
            hinweis = hinweis(wort, tipp);
            System.out.println("Das Wort " + hinweis + " \"" + tipp + "\"!");
            versuche++;
        } while(!hinweis.equals("ist"));
        System.out.println("Glückwunsch, du hast nur " + versuche + " Versuche benötigt!");
    }
    
    /**
     * Wählt zufällig ein Wort aus dem "woerter"-Array aus und gibt es zurück.
     */
    static String zufallsWort(String[] woerter) {
        Random random = new Random();
        int index = random.nextInt(woerter.length);
        return woerter[index];
    }
    
    /**
     * Vergleicht das gegebene "wort" und die "tipp"-Zeichenkette und gibt einen Hinweis zurück.
     * Folgende Hinweise sind möglich: "ist", "beginnt mit", "endet mit", "beginnt mit und endet
     * mit", "enthält" oder "enthält nicht".
     */
    static String hinweis(String wort, String tipp) {
        if(wort.equals(tipp))
            return "ist";
        else if(wort.startsWith(tipp) || wort.endsWith(tipp)) {
            String hinweis = "";
            if(wort.startsWith(tipp))
                hinweis += "beginnt mit";
            if(wort.endsWith(tipp)) {
                if(!hinweis.equals(""))
                    hinweis += " und ";
                hinweis += "endet mit";
            }
            return hinweis;
        }
        else if(wort.contains(tipp))
            return "enthält";
        else
            return "enthält nicht";
    }
}
