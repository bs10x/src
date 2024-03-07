import java.util.Scanner;

/*
 * Author: Maximiliana Muster
 * für Einfuehrung in die Programmierung, HS 2018
 * 
 * Dieses Programm zaehlt die Primzahlen bis zu einem Limit.
 */
public class Sieb {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Geben Sie eine positive ganze Zahl ein: ");
        int limit = scanner.nextInt();
        if(limit <= 0) {
            System.out.println("Keine positive ganze Zahl!");
        }
        else {
            // True: "ist Primzahl", false: "ist keine Primzahl"
            boolean[] sieb = new boolean[limit + 1];
            // Setze alle Elemente ab 2 auf true
            for(int i = 2; i < sieb.length; i++)
                sieb[i] = true;
            
            // Gehe alle Zahlen bis limit durch und setze Vielfache davon auf false
            // Zusaetzliche Optimierung waere: statt bis limit nur bis wurzel(limit)
            for(int i = 2; i < limit; i++) {
                // Optimierung: nur, wenn Element nicht schon false ist
                if(sieb[i]) {
                    for (int vielfaches = 2 * i; vielfaches <= limit; vielfaches += i) { 
                        sieb[vielfaches] = false;
                    }
                }
            }
            
            // Zaehle uebrig gebliebene Zahlen - Primzahlen
            int primzahlen = 0;
            for(int i = 2; i < sieb.length; i++)
                if(sieb[i])
                    primzahlen++;
                
            System.out.println(primzahlen);
        }
    }
}
