import java.util.Scanner;
/*
 * Author: Maximiliana Muster
 * für Einfuehrung in die Programmierung, HS 2017
 * 
 * Dieses Programm gibt die Binaerdarstellung einer positive Zahl aus, ohne Arrays oder String-
 * Operationen.
 */
public class Binaer {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Geben Sie eine positive ganze Zahl ein: ");
        int z = scanner.nextInt();
        if(z <= 0) {
            System.out.println("Keine positive ganze Zahl!");
        }
        else {
            // Finde twoToTheK = 2^k <= z
            int k = 0;
            int twoToTheK = 1;
            while(z >= twoToTheK) {
                k++;
                twoToTheK *= 2;
            }
            k--;
            twoToTheK /= 2;
            
            // Drucke einzelne Ziffern der Binaerdarstellung von z
            while(k >= 0) {
                int digit = z / twoToTheK;
                z -= digit * twoToTheK;
                System.out.print(digit);
                
                k--;
                twoToTheK /= 2;
            }
            System.out.println();
        }
    }
}
