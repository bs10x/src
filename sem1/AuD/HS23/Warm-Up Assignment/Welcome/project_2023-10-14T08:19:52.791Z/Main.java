class Main {

  public static void main(String[] args) {
    int round = In.readInt(); // read the number of actions
    for (int i = 0; i < round; i++) {
      Out.println(welcome(In.readWord()));
    }
  }
    
  public static String welcome(String s) {
    return "Welcome " + s +"!";
  }
}
