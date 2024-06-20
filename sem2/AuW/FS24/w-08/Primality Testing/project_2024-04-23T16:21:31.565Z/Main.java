import algorithms.*;
import java.math.*;
import java.util.Random;

class Main {
    public static void main(String[] args) {
      In.open("public/custom.in");
      Out.compareTo("public/custom.out");
      int t = In.readInt();
      for (int i = 0; i < t; i++) {
        testCase();
      }
      In.close();
    }
    
    public static void testCase() {
      BigInteger n = new BigInteger(In.readWord());
      Out.println(probablyPrime(n) ? "yes" : "no");
    }
    
    public static boolean probablyPrime(BigInteger n) {
      BigInteger one = BigInteger.ONE;
      BigInteger two = BigInteger.valueOf(2);
      
      if (n.equals(one)) return false;
      if (n.equals(two)) return true;
      
      BigInteger s = BigInteger.valueOf(n.subtract(one).getLowestSetBit());
      BigInteger d = n.subtract(one).shiftRight(s.intValue());
      
      int k = 100;
      for (int i = 0; i < k; i++) {
        BigInteger a = randomBase(n);
        BigInteger x = a.modPow(d, n);
        BigInteger y = BigInteger.ZERO;
        
        for (int j = 0; j < s.longValue(); j++) {
          y = x.modPow(two, n);
          if (y.equals(one) && !x.equals(one) && !x.equals(n.subtract(one))) return false;
          x = y;
        }
        if (!y.equals(one)) return false;
      }
      return true;
    }
    
    public static BigInteger randomBase(BigInteger n) {
      Random rng = new Random();
      BigInteger randomBase;
      do randomBase = new BigInteger(n.bitLength(), rng).add(BigInteger.valueOf(2));
      while (randomBase.compareTo(n.subtract(BigInteger.valueOf(2)))  >= 0);
      return randomBase;
    }
}
