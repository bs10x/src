import algorithms.*;

class Main {
    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file
        In.open("public/sample.in");
        Out.compareTo("public/sample.out");

        int t = In.readInt();
        for (int i = 0; i < t; i++) {
            testCase();
        }
        
        // Uncomment this line if you want to read from a file
        In.close();
    }

    public static void testCase() {
      // IN // a_b, #a, #b >= 10
      // out // #a, #b, a..b, a.rev.sub(1, b), a.rev.sub(1, a)
      
      String a = In.readWord();
      String b = In.readWord();
      
      String op = "";
      op += a.length() + " ";
      op += b.length() + " ";
      op += a.concat(b) + " ";
      op += revsub(a, b);
      
      Out.println(op);
    }
    
    public static String revsub(String a, String b) {
      int n = a.length();
      int m = b.length();
      char[] c = new char[n];
      char[] d = new char[m];
      for (int i = 0; i < n; i++) c[n - i - 1] = a.charAt(i);
      for (int j = 0; j < m; j++) d[m - j - 1] = b.charAt(j);
      char tmp = c[0];
      c[0] = d[0];
      d[0] = tmp;
      return String.valueOf(c) + " " + String.valueOf(d);
    }
}
