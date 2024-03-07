import java.io.PrintStream;

// Veraendern Sie diese Datei nicht!

public class Klassen {
	
	public static void main(String[] args) {
		klassen(System.out);
	}


    public static void klassen(PrintStream output) {
        Z ref1 = new B(); 
        ref1.bar(output);
        output.println("++");
        Z ref2 = new A();
        ((A) ref2).bar(output);
        output.println("++");

        C c1 = new C();
        output.println("C.foo():");
        c1.foo(output);
        output.println("--");
        D d1 = new D();
 
        if (d1 instanceof C) {
            ((C)d1).test(output);
        } else {
            d1.foo(output);
        }
    }
}