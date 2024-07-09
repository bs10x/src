package ethz.EProg;
public class A6 {
	
	public static void print(String s) {
		System.out.println(s);
	}

	public static void main(String[] args) {
		
//		Object o = new Aa();
////	(Aa) o.z1();	// method z1() is undefined for the type Object
//		((Aa) o).z1();	// correct downcast
//		Aa ea = new Ee();
//		ea.z1();		// calls Ee's type method z1()
		

		I x1 = new M();
		x1.g();				// M_g
		
		I x2 = new N();	
		x2.g();				// M_g
		
		M x3 = new N();
		x3.g();				// M_g -> implicit upcasting, type of reference determines methods
		
		M x4 = new M();
//		((N)x4).g();		// Exception -> can't cast lower than M
		
		Object z5 = new N();
		M x5 = (N) z5;		// downcasting (instanceof N == true) to M
		x5.h("X");			// M_X N_h -> super method is executed, (M.h(X)) then this (N.h())
		
//		I x6 = new S();		// compile-error, S doesn't implement I
//		x6.g(p);			// what even is p
		
		Object x7 = new P();
//		Q y7 = (Q) x7;		// Exception -> can't cast lower than P
//		y7.g();	
		
		P x8 = new P();	
//		I y8 = (I) x8;		// Exception, P doesn't implement I
//		y8.g();
		
		I x9 = new R();
		x9.g();				// R_Q Q_Q		// ?? methods and variables in class of caller are used
		
		P x10 = new R();
		x10.g();			// R_Q Q_Q		// ?? here the method j of the subclass is used instead of the calling class??
		
		
		
		
		P b1 = new Q();
//		((R)b1).g();		// exception

		S b2 = new S();
//		((I)b2).g();		// exception

		R b3 = new R();
//		((Q)b3).j("Z");		// R_Z

		N b4 = new N();
//		((M)b4).h("Y");		// M_Y N_h

		Object b5 = new S();	
//		((I)b5).g();		// exception

//		I b6 = new P();		// error
//		((Q)b6).j("X");

		Q b7 = new Q();
		((P)b7).g();		// Q_Q Q_Q

		Object b8 = new R();
//		((M)b8).h("Z");		// exception

		N b9 = new N();
//		((S)b9).g();		// error

		P b10 = new P();
//		((R)b10).j("W");	// exception

		
		
		
		
		
		I c1 = new M();
//		c1.s;
//		((M)c1).h(s);
		
		M c2 = new N();
		
//		N c3 = new M();	// error -> needs explicit downcast
//		N c4 = (N) new M();	// exception
		Object c5 = new M();
		
		

	}
}









interface I {
	public void g();
}

class M extends Object implements I { 
	String s = "1";
	public void g() { A6.print("M_g"); }
	public void h(String s) { A6.print("M_" + s); }
}

class N extends M {
	String s = "2";
	public void h(String s) {
		super.h(s);
		A6.print("N_h");
	}
}

class S {
	public void g() { A6.print("W"); }
}

class P {
	public void g() { A6.print("P_g"); }
}

class Q extends P implements I {
	String s = "Q";
	public void g() {
		j(s);
		A6.print("Q_" + s);
	}
	public void j(String s) { A6.print("Q_" + s); }
}

class R extends Q {
	String s = "3";
	public void j(String s) { A6.print("R_" + s); }
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Rr {
	static int v;
	int x;

	public Rr() {
		this.x = 1;	// extra, no args -> no confusion which x, v 
		this.v = 1;	// extra
	}
	public Rr(int x) {
		this();		// correct, without parameterless constructor, v is not initialized
		this.x = x;	// correct, x could be arg or this.x
	}
	public Rr(int x, int y) {
		v = x * y;
		x = v / this.x;	// wrong, this.x is uninitialized and thus zero -> arithmetic exception
	}
	public Rr(int x, int y, int z) {
		x = y + 1;
		v = this.x * x;	// correct, without this.x, v == (y + 1) * (y + 1), with it this.x == 0 * (y + 1)
	}
	public static String getString() {
//		return "v = " + this.v + 		// wrong, static methods may be called without instance of this,
//			  " x = " + this.x;		// thus there may not exist a field to access
		return null;
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Aa {
	Integer s = 1;
	public void z1() { System.out.println("Aa_" + s); }
}
class Bb extends Ff {
	String s = "Bb";
	public void z2() { System.out.println("Bb_z2_" + s); }
}
class Cc extends Ff {
	String s = "Cc";
	public void z2() {
		super.z1();
		System.out.println("Cc_z2__" + s);
	}
	public void z3() { System.out.println("Cc_z3_" + s); }
	public String toString() { return s; }
}
class Dd extends Cc {
	String s = "Dd";
	public void z2() { System.out.println("Dd_z2_" + s); }
}
class Ee extends Aa {
	String s = "Ee";
	public void z1() { System.out.println("Ee_z1_" + s); }
	public void z2() { System.out.println("Ee_z2_" + s); }
}
class Ff extends Aa {
	String s = "Ff";
	public void z1() {
		System.out.println("Ff_z1_" + s);
		z3();
	}
	public void z3() { System.out.println("Ff_z3_" + s); }
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
interface Interface {
	public String toString();
}
class SuperClass {
	String s = "SuperClass!";
	public void f1() { System.out.println(s); }
}
class SubClass extends SuperClass {		// SubClass inherits methods
	String s = "SubClass..";	// 's' attribute shadowed
	//public void f1() { System.out.println(s); }		// @Override
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




