
class SafeMath {
	
	// Addiert 'a' und 'b'. Returned MAX Int falls die Summe overflowen wuerde.
	static int safeAdd(int a, int b) {
		int result;
		try {
			result = Math.addExact(a, b);
		} catch(ArithmeticException e) {
		    result = Integer.MAX_VALUE;
		}
		return result;
	}
}