class Main {
  
  public static void main(String[] args) {
    int round = In.readInt(); // read the number of actions
    for (int i = 0; i < round; i++) {
      Out.println(median(In.readInt(), In.readInt(), In.readInt()));
    }
  }
  

  public static int median(int a, int b, int c) {
    boolean aMed = (a <= b && a >= c) || (a <= c && a >= b);
    boolean bMed = (b <= c && b >= a) || (b <= a && b >= c);
    boolean cMed = (c <= a && c >= b) || (c <= b && c >= a);
    return (aMed) ? a : (bMed) ? b : c;
  }
}