import java.util.Scanner;

/*
 * Author: Maximiliana Muster
 * für Einführung in die Programmierung
 *
 * Liest eine Serie von Zahlen von der Konsole ein, füllt damit eine LinkedIntList und
 * gibt sie wieder aus.
 */
public class Echo {

  public static void main(String[] args) {
    LinkedIntList list = new LinkedIntList();
    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNextInt())
      list.addLast(scanner.nextInt());

    for (IntNode n = list.first; n != null; n = n.next)
      System.out.println(n.value);
  }
}
