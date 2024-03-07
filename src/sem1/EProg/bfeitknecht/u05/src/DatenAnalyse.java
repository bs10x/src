import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DatenAnalyse {
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("groessen.txt"));
        int[] groessen = liesGroessen(scanner);
        scanner.close();
        
        einfacheAnalyse(groessen);

        int histMin = 140; //  Wir nehmen an, dass alle Groessen >= 1.40m sind
        int histMax = 200; //  Wir nehmen an, dass alle Groessen < 2.00m sind
        histogrammAnalyse(groessen, histMin, histMax);
    }
    
    /**
     * Liest die Körpergrössen von dem gegebenen Scanner ein und gibt sie als Array zurück.
     */
    static int[] liesGroessen(Scanner scanner) {
        return new int[0];
    }
    
    /**
     * Macht eine einfache Datenanalyse: Gibt die Anzahl Daten, das Minimum, das Maximum und den
     * Durchschnitt der Körpergrössen aus.
     */
    static void einfacheAnalyse(int[] groessen) {
    }
    
    /**
     * Erstellt ein Histogramm der Körpergrössen und gibt es aus. Zuerst wird der Benutzer nach der
     * Anzahl Histogramm-Klassen gefragt.
     */
    static void histogrammAnalyse(int[] groessen, int histMin, int histMax) {
    }
    
    /**
     * Erstellt von den Körpergrössen in dem "groessen"-Array ein Histogramm mit den gegebenen
     * Grössen Minimum, Maximum und Anzahl Klassen.
     * 
     * @return das Histogramm, als Array. Jedes Element entspricht einer Klasse im Histogramm und
     *         enthält die Anzahl Körpergrössen in dieser Klasse.
     */
    static int[] erstelleHistogramm(int[] groessen, int histMin, int histMax, int klassen) {
        return new int[0];
    }
    
    /**
     * Gibt die Klassenbreite in einem Histogramm mit den gegebenen Grössen Minimum, Maximum und
     * Anzahl Klassen zurück. Falls sich die gesamte Breite des Histogramms nicht restlos auf die
     * Klassen aufteilen lässt, wird die Klassenbreite aufgerundet. Das heisst, dass das Histogramm
     * "histMax" überschreiten kann.
     */
    static int klassenBreite(int histMin, int histMax, int klassen) {
        return 0;
    }
}