
// Veraendern Sie diese Datei nicht!

interface SearchCheck {
	// Returned true genau dann wenn der Divisor nicht zu klein ist.
	boolean check(double divisor);
}

class DivisorSearch {
	
	// Returned die tiefste doubleingpointnumber, fuer welchen 'check.check' true ergibt.
	static double search(SearchCheck check) {
		
		double low = 0f;
		double high = 2f;
		
		while (!check.check(high)) {
			high = high * high;
		}
		
		double middle = (high - low) /  2 + low;
		while (low < middle && middle < high) {
			if (check.check(middle)) {
				high = middle;
			} else {
				low = middle;
			}
			middle = (high - low) /  2 + low;
		} 
		
		return high;
	}
}

