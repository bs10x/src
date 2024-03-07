/*
 * Author: Maximiliana Muster
 * für Einfuehrung in die Programmierung, HS 2017
 * 
 * Dieses Programm gibt den eingegebenen Namen als in einem Quadrat angeordnete Scrabble-Steine aus.
 */
public class Scrabble {

	public static void main(String[] args) {
		drawNameSquare("Alfred");
	}
	
	public static void drawNameSquare(String name) {
	    name = name.toUpperCase();
        int n = name.length();

        //Drucke den obersten Rahmen
        System.out.print("+");
        for (int i = 0; i < n; i += 1) {
            System.out.print("---+");
        }
        System.out.println();

        //Drucke name im gewünschten Format | N | A | M | E | 
        System.out.print("|");
        for (int i = 0; i < n; i += 1) {
            System.out.print(" " + name.charAt(i) + " |");
        }
        System.out.println();

        //Drucke den unteren Rahmen der obersten Zeile
        System.out.print("+");
        for (int i = 0; i < n; i += 1) {
            System.out.print("---+");
        }
        System.out.println();
        
        //Drucke den Vertikalen Teil des Quadrats, nothingness ist die Länge der Lücke in der Mitte
        int nothingness = (n-2)*4 - 1;
        for (int j = 1; j < n-2; j += 1) {
            System.out.print("| " + name.charAt(j) + " |");
            for (int i = 0; i < nothingness; i += 1) {
                System.out.print(" ");
            }
            System.out.print("| " + name.charAt(n - 1 - j) + " |");
            System.out.println();

            System.out.print("+---+");
            for (int i = 0; i < nothingness; i += 1) {
                System.out.print(" ");
            }
            System.out.print("+---+");
            System.out.println();
        }
        System.out.print("| " + name.charAt(n-2) + " |");
        for (int i = 0; i < nothingness; i += 1) {
            System.out.print(" ");
        }
        System.out.print("| " + name.charAt(1) + " |");
        System.out.println();

        //Drucke den oberen Rahmen der untersten Zeile
        System.out.print("+");
        for (int i = 0; i < n; i += 1) {
            System.out.print("---+");
        }
        System.out.println();

	   	//Drucke name im gewünschten Format rückwärts | E | M | A | N | 
	    System.out.print("|");
	    for (int i = n-1; i >= 0; i -= 1) {
	        System.out.print(" " + name.charAt(i) + " |");
	    }
	    System.out.println();
	
	    //Drucke den untersten Rahmen
	    System.out.print("+");
	    for (int i = 0; i < n; i += 1) {
	        System.out.print("---+");
	    }
	    System.out.println();
	}
       
}
