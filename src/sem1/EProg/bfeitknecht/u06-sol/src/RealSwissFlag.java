import java.util.Scanner;
import gui.Window;

/*
 * Author: Maximiliana Muster
 * für Einfuehrung in die Programmierung, HS 2018
 * 
 * Dieses Programm zeigt eine Schweizerfahne in einem Fenster an.
 */
public class RealSwissFlag {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Geben Sie die Fahnengrösse ein: ");
        int size = scanner.nextInt();
        
        if(size < 200) {
            System.out.println("Zu klein.");
            System.exit(1);
        }

        double balkenLaenge = size * 5.0/8.0;
        double balkenBreite = balkenLaenge * 6.0/20.0;
        
        Window window = new Window("Fahne", size, size);
        window.setColor(225, 26, 39);
        window.fillRect(0, 0, size, size);
        window.setColor(255, 255, 255);
        window.fillRect((size-balkenLaenge)/2, (size-balkenBreite)/2, balkenLaenge, balkenBreite);
        window.fillRect((size-balkenBreite)/2, (size-balkenLaenge)/2, balkenBreite, balkenLaenge);
        
        window.open();
        window.waitUntilClosed();
    }
}
