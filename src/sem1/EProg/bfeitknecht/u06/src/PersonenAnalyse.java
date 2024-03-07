import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/* 
 * Author: Maximiliana Muster
 * für Einführung in die Programmierung
 * 
 * Analysiert einen Datensatz von Personen-Eigenschaften. Findet "ungesunde" Personen und teilt
 * alle Personen in Trainingspartner-Paare ein.
 */
public class PersonenAnalyse {
    
    public static void main(String[] args) throws FileNotFoundException {}

    /**
     * Liest die Personen-Daten von dem gegebenen Scanner ein und gibt sie als Person[] zurück.
     */
    static Person[] liesPersonen(Scanner scanner) {
        // TODO
        return null;
    }
    
    /**
     * Findet alle Personen, die nicht normalgewichtig sind und gibt deren Beschreibungen, sowie die
     * entsprechende Gewichts-Klassen aus.
     */
    static void druckeUngesunde(Person[] personen, PrintStream ausgabe) {
        // TODO
    }

    /**
     * Gibt die Partner-Qualität von zwei Trainingspartner p1 und p2 zurück. Wert liegt zwischen 0
     * (schlechtester Wert) und 1 (bester Wert).
     */
    static double partnerQualitaet(Person p1, Person p2) {
        // TODO
        return 0;
    }
}