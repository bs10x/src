import java.io.PrintStream;

// Ausser der "extends" Klausel, veraendern Sie diese Datei nicht!

public class B /* Wenn noetig, dann fuegen Sie hier eine "extends" Klausel hinzu */ {

	B() {}

	B(int w) {
		super(w);
	}

	public void bar(PrintStream output) {
		super.bar(output);
		output.println("Bingo");
	}
}
