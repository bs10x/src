import java.io.PrintStream;

// Ausser der "extends" Klausel, veraendern Sie diese Datei nicht!

public class A /* Wenn noetig, dann fuegen Sie hier eine "extends" Klausel hinzu */ {

	int a1 = 0;

	A() {}

	A(int v) {
		a1 = v;
	}

	public void foo(PrintStream output) {
		output.println("Found");
	}
}
