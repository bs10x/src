import java.util.stream.IntStream;

public class Match {

	public static void main(String[] args) {
		testMatchNumber(1, 32857890, 789);
		testMatchNumber(1, 37897890, 789);
		testMatchNumber(2, 1800765, 7);
		testMatchNumber(-1, 1800765, 8);
		testMatchNumber(1, 75, 7);
	}
	
	public static void testMatchNumber(int expected, long A, int M) {
		int result = matchNumber(A, M);
		if (expected == result) {
			System.out.println("Passed");
		} else {
			System.out.println("Failed. Expected " + expected + ", but got " + result + ".");
		}
	}
	
	public static int matchNumber(long A, int M) {
		
		String stringM = String.valueOf(M);
		String stringA = String.valueOf(A);
		
		if (stringM.length() == 1) stringM = "00" + stringM;
		if (stringM.length() == 2) stringM = "0" + stringM;
		if (stringM.length() >3) return -1;
		
		int lengthOfA = String.valueOf(A).length();
		
		// iterate through all digits of A
		for (int i = lengthOfA -1; i >= 0; --i) {
				
			// guard arm to protect against case where M has one digit, matching the 0th digit of A
			if (String.valueOf(stringM.charAt(2)).equals(String.valueOf(stringA.charAt(0))))
				return lengthOfA -1;
			
			// guard arm to protect against case where M has two digits, matching the 0th and 1st digits of A
			if (String.valueOf(stringM.charAt(2)).equals(String.valueOf(stringA.charAt(1)))
			&& String.valueOf(stringM.charAt(1)).equals(String.valueOf(stringA.charAt(0))))
				return lengthOfA -2;	
				
			if (String.valueOf(stringM.charAt(2)).equals(String.valueOf(stringA.charAt(i)))
			&& String.valueOf(stringM.charAt(1)).equals(String.valueOf(stringA.charAt(i -1)))
			&& String.valueOf(stringM.charAt(0)).equals(String.valueOf(stringA.charAt(i -2))))
				return lengthOfA -i -1;
		}
		
		
		return -1; 
	}
	
	


}
