public class PrefixConstruction {

  public static boolean isPrefixConstruction(String s, String t, int n) {
    return isPrefixConstructionAux(s, "", n, t);
  }

  // helper method
  public static boolean isPrefixConstructionAux(String remainingS, String currentPrefix, int remainingN, String t) {
    if (remainingS.isEmpty()) {
      // base case: s is empty -> condition (at most n prefixes) met
      return true;
    }

    if (remainingN > 0 && isPrefixConstructionAux(remainingS, t, remainingN - 1, t)) {
      // still possible to use more prefixes from t to construct s, given remainingN > 0 -> condition met
      return true;
    }

    if (!currentPrefix.isEmpty() && remainingS.charAt(0) == currentPrefix.charAt(0)) {
      // first character of remainingS matches current prefix -> check if rest also matches
      return isPrefixConstructionAux(remainingS.substring(1), currentPrefix.substring(1), remainingN, t);
    }

    return false;
  }
}