import java.io.PrintStream;

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
		
		Delta[] ds = {s, a, k};
		for (int j = 0; j < ds.length; j += 1) {
			output.println(ds[j].name());
			output.println("---");
		}
		output.println(i.name());
		output.println("---");
		
		choose(z, i, z);
		Alpha aa = l.choose(s, a, k);
		
		Iota ii = k.create();
		output.println(ii.name());
		
		if (k == aa) {
			output.println("ok");
		} else if (i.calc() > k.calc().length() && s.calc()) {
			output.println("wrong");
		} else {
			output.println("wrong");
		}
		
		Zeta zz = z.create();
		output.println(zz.name());
	}
	
	public static Delta choose(Lambda l, Iota i, Alpha a) {
		return i;
	}
}
