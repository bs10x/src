import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* 
 * Author: Maximilian Muster
 * für Einführung in die Programmierung
 * 
 * Analysiert einen Datensatz von Körpergrössen.
 */
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
        int[] groessen = new int[scanner.nextInt()];
        for(int i = 0; i < groessen.length; i++) {
            groessen[i] = scanner.nextInt();
        }
        return groessen;
    }
    
    /**
     * Macht eine einfache Datenanalyse: Gibt die Anzahl Daten, das Minimum, das Maximum und den
     * Durchschnitt der Körpergrössen aus.
     */
    static void einfacheAnalyse(int[] groessen) {
        int min = Integer.MAX_VALUE, max = 0, sum = 0;
        for(int i = 0; i < groessen.length; i++) {
            min = Math.min(min, groessen[i]);
            max = Math.max(max, groessen[i]);
            sum += groessen[i];
        }
        
        int durchschnitt = sum / groessen.length;
        System.out.println("Anzahl Daten: " + groessen.length);
        System.out.println("Minimum: " + min + " cm");
        System.out.println("Maximum: " + max + " cm");
        System.out.println("Durchschnitt: " + durchschnitt + " cm");
        System.out.println();
    }
    
    /**
     * Erstellt ein Histogramm der Körpergrössen und gibt es aus. Zuerst wird der Benutzer nach der
     * Anzahl Histogramm-Klassen gefragt.
     */
    static void histogrammAnalyse(int[] groessen, int histMin, int histMax) {
        System.out.print("Wie viele Klassen soll das Histogramm enthalten? ");
        int klassen = new Scanner(System.in).nextInt();
        if(klassen <= 0) {
            System.out.println("Ungültige Anzahl Klassen!");
        }
        else {
            int[] histogramm = erstelleHistogramm(groessen, histMin, histMax, klassen);
            druckeHistogramm(histogramm, histMin, histMax);
        }
    }
    
    /**
     * Erstellt von den Körpergrössen in dem "groessen"-Array ein Histogramm mit den gegebenen
     * Grössen Minimum, Maximum und Anzahl Klassen.
     * 
     * @return das Histogramm, als Array. Jedes Element entspricht einer Klasse im Histogramm und
     *         enthält die Anzahl Körpergrössen in dieser Klasse.
     */
    static int[] erstelleHistogramm(int[] groessen, int histMin, int histMax, int klassen) {
        int klassenBreite = klassenBreite(histMin, histMax, klassen);
        int[] histogramm = new int[klassen];
        for(int i = 0; i < groessen.length; i++) {
            int klasse = klasseFuerGroesse(groessen[i], histMin, klassenBreite);
            histogramm[klasse]++;
        }
        
        return histogramm;
    }
    
    /**
     * Ordnet einer gegebenen Körpergrösse eine Klasse in einem Histogramm, welches durch "histMin"
     * und "klassenBreite" gegeben ist, zu.
     */
    static int klasseFuerGroesse(int groesse, int histMin, int klassenBreite) {
        return (groesse - histMin) / klassenBreite;
    }
    
    /**
     * Gibt das gegebene Histogramm als Text-Diagramm aus.
     */
    static void druckeHistogramm(int[] histogramm, int histMin, int histMax) {
        int klassenBreite = klassenBreite(histMin, histMax, histogramm.length);
        for(int k = 0; k < histogramm.length; k++) {
            System.out.print(klassenBeschreibung(k, histMin, klassenBreite) + " ");
            for(int i = 0; i < histogramm[k]; i++)
                System.out.print("|");
            System.out.println();
        }
    }
    
    /**
     * Gibt für die gegebene "klasse" und für das Histogramm, welches durch "histMin" und
     * "klassenBreite" gegeben ist, eine Beschreibung zurück.
     */
    static String klassenBeschreibung(int klasse, int histMin, int klassenBreite) {
        int untereGrenze = histMin + klasse * klassenBreite;
        int obereGrenze = untereGrenze + klassenBreite;
        return "[" + untereGrenze + "," + obereGrenze + ")";
    }
    
    /**
     * Gibt die Klassenbreite in einem Histogramm mit den gegebenen Grössen Minimum, Maximum und
     * Anzahl Klassen zurück. Falls sich die gesamte Breite des Histogramms nicht restlos auf die
     * Klassen aufteilen lässt, wird die Klassenbreite aufgerundet. Das heisst, dass das Histogramm
     * "histMax" überschreiten kann.
     */
    static int klassenBreite(int histMin, int histMax, int klassen) {
        int histBreite = histMax - histMin;
        int klassenBreite = histBreite / klassen;
        if(histBreite % klassen != 0)
            klassenBreite++; // Falls Division nicht aufgeht, mache Klassen etwas "zu breit"
            
        return klassenBreite;
    }
}
