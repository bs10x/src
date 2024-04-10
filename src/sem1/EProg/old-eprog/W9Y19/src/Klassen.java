import java.io.PrintStream;

// Veraendern Sie diese Datei nicht!

public class Klassen {

	public static void main(String[] args) {
		klassen(System.out);
	}
	
	public static void klassen(PrintStream output) {
		
		Lambda l = new Lambda();
		Sigma s = new Sigma();
		Alpha a = new Alpha();
		Kappa k = new Kappa();
		Iota i = new Iota();
		Zeta z = new Zeta();
		Beta b = new Beta();
		Omega o = new Omega();
		
		Omega[] os = {z, k, l, (Omega) i}; 
		for (int j = 0; j < os.length; j += 1) {
			output.println(os[j].name());
			output.println("---");
		}
		
		b = doSomething(l); 
		int n = choose(l, z); 
		
		b = l.choose(a, s, i);
		output.println(b == a);
		output.println("---");
		
		boolean c = s.calc() > i.calc().length() && z.calc(); 
		
		Lambda ll = o.create();
		output.println(ll.name());
		output.println("---");
	}
	
	public static int choose(Kappa k, Alpha a) {
		return 0;
	}
	
	public static Kappa choose(Alpha a, Kappa k) {
		return a; 
	}
	
	public static Sigma doSomething(Iota i) {
		return null; 
	}
	
	public static int doSomething(Kappa k) {
		return 0;
	}
}




