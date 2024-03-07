import java.util.Scanner;

public class Zahlen {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Geben Sie eine kodierte Anzeige an: ");
        String encoding = scanner.next();
        
        if (encoding.contains("f")) {
        	// übrige Kandidaten: 0, 4, 5, 6, 8, 9
        	if (encoding.contains("b")) {
        		// übrige Kandidaten: 0, 4, 8, 9
        		if (encoding.contains("e")) {
        			// übrige Kandidaten: 0, 8
        			if (encoding.contains("g")) {
        				// übrige Kandidaten: 8
                		System.out.println(8);
                	} else {
                		// übrige Kandidaten: 0
                		System.out.println(0);
                	}
            	} else {
            		// übrige Kandidaten: 4, 9
            		if (encoding.contains("a")) {
            			// übrige Kandidaten: 9
                		System.out.println(9);
                	} else {
                		// übrige Kandidaten: 4
                		System.out.println(4);
                	}
            	}
        	} else {
        		// übrige Kandidaten: 5, 6
        		if (encoding.contains("e")) {
        			// übrige Kandidaten: 6
            		System.out.println(6);
            	} else {
            		// übrige Kandidaten: 5
            		System.out.println(5);
            	}
        	}
        } else {
        	// übrige Kandidaten: 1, 2, 3, 7
        	if (encoding.contains("d")) {
        		// übrige Kandidaten: 2, 3
        		if (encoding.contains("c")) {
        			// übrige Kandidaten: 3
            		System.out.println(3);
            	} else {
            		// übrige Kandidaten: 2
            		System.out.println(2);
            	}
        	} else {
        		// übrige Kandidaten: 1, 7
        		if (encoding.contains("a")) {
        			// übrige Kandidaten: 7
            		System.out.println(7);
            	} else {
            		// übrige Kandidaten: 1
            		System.out.println(1);
            	}
        	}
        }
        
	}
}
