/**
 * Eine Person mit diversen Eigenschaften, inkl. Alter, Gewichts, Grösse, Geschlecht und einigen
 * spezielleren Körper-Eigenschaften.
 */
public class Person {
    
    // TODO: Instead of persons, may model (simpler) physical objects in this exercise
    // and use them in a physical simulation
    
    int alter;             // Jahre
    double gewicht;        // kg
    double groesse;        // cm
    boolean istMaennlich;
    
    double schulterBreite; // cm
    double brustTiefe;     // cm
    double brustBreite;    // cm
    
    /**
     * Erstellt eine neue Person mit den gegebenen Werten für die Eigenschaften.
     */
    Person(int alter, double gewicht, double groesse, boolean istMaennlich,
            double schulterBreite, double brustTiefe, double brustBreite) {
        this.alter = alter;
        this.gewicht = gewicht;
        this.groesse = groesse;
        this.istMaennlich = istMaennlich;
        this.schulterBreite = schulterBreite;
        this.brustTiefe = brustTiefe;
        this.brustBreite = brustBreite;
    }
    
    /**
     * Gibt eine String-Beschreibung dieser Person zurück, welche Geschlecht, Alter, Grösse und
     * Gewicht enthält.
     */
    String beschreibung() {
        String geschlecht;
        if(istMaennlich)
            geschlecht = "m";
        else
            geschlecht = "w";
        
        return "Person (" + geschlecht + ", " + alter + " Jahre, " + groesse + " cm, " + gewicht + " kg)";
    }
    
    /**
     * Gibt den Body-Mass-Index dieser Person (in kg/m^2) zurück.
     */
    double bodyMassIndex() {
        return 10000 * gewicht / (groesse * groesse);
    }
}
