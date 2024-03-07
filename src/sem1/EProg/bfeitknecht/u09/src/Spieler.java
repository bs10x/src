/**
 * Ein Spieler im {@link RateSpiel}. Tritt gegen den {@link Computer} an und versucht, ein Wort zu
 * erraten. Wichtig: Diese Klasse hat selber keine Funktionalität und muss (mittels Vererbung)
 * erweitert werden. Alle Methoden dieser Klasse sollten überschrieben werden.
 */
public class Spieler {
    
    /**
     * Wird aufgerufen, wenn ein neues Spiel beginnt. Die Liste der Wörter, welche in diesem Spiel
     * verwendet wird, wird als Array übergeben.
     */
    public void neuesSpiel(String[] verwendeteWoerter) {}
    
    /**
     * Mit dieser Methode gibt der Spieler einen Tipp ab. Diese Methode wird in jeder Runde eines
     * Spiels aufgerufen. Der Hinweis des Computers für diesen Tipp wird danach mit der
     * bekommeHinweis()-Methode übergeben.
     */
    public String gibTipp() {
        return "Dies ist ein dummer Tipp, weil gibTipp() nicht überschrieben wurde (oder die Klasse Spieler instanziert wurde)";
    }
    
    /**
     * Mit dieser Methode wird dem Spieler den Hinweis für seinen letzten Tipp übergeben. Der Tipp
     * wird komfortablerweise nochmals übergeben.
     */
    public void bekommeHinweis(String tipp, String hinweis) {}
    
    /**
     * Der Name dieses Spielers.
     */
    public String name() {
        return "Spieler (welcher eigentlich nicht instanziert werden sollte...)";
    }
}
