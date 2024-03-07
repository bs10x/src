import java.util.Scanner;
/*
 * Author: Maximiliana Muster
 * für Einfuehrung in die Programmierung, HS 2017
 * 
 * Dieses Programm berechnet den GGT von zwei ganzen Zahlen.
 */
public class GGT {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Geben Sie zwei positive ganze Zahlen ein: ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        if(x <= 0 || y <= 0) {
            System.out.println("Keine positiven ganzen Zahlen!");
        }
        else {
            while(x < y || x % y != 0) {
                // Zwischenspeichern von y
                int altY = y;
                y = x % y;
                x = altY;
            }
            System.out.println(y);
        }
    }
}
