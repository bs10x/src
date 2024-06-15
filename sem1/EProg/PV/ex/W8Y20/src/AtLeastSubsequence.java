

public class AtLeastSubsequence {

	public static boolean subsequence(String s, String t, int n) {
        return n <= f(s, t);
    }

    static int f(String s, String t) {
    	return t.isEmpty() ? 1 : s.isEmpty() ? 0 : f(s.substring(1), t) + (s.charAt(0) == t.charAt(0) ? f(s.substring(1), t.substring(1)) : 0);
    }
}

