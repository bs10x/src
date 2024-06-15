import java.util.*;

public class Frequencies {
  public double minDistance; //minDistance entspricht dem "d" in der Aufgabenstellung 
  public ArrayList<Coordinate> stations;
  
  public Frequencies(ArrayList<Coordinate> stations, double minDistance) {
    this.stations = stations;
    this.minDistance = minDistance;
  }

  public static void main(String[] args) {
	    ArrayList<Coordinate> stations = new ArrayList<Coordinate>();
	    stations.add(new Coordinate(0.0, 5.0));
	    stations.add(new Coordinate(1.0, 3.0));
	    stations.add(new Coordinate(6.0, 0.5));
	    
	    Frequencies ff = new Frequencies(stations, 7.0);
	    boolean moeglich = ff.assignmentPossible(2);
	    System.out.println(moeglich); // expected output: true
	    int minFrequenzen = ff.minFrequencies();
	    System.out.println(minFrequenzen); // expected output: 2
  }

  public boolean assignmentPossible(int N) {
    // TODO
    return false;
  }
  
  public int minFrequencies() {
    // TODO 
	return 0;
  }
  
  public List<Coordinate> mostProblematicStations(int m) {
    // TODO
    return null;
  }
  
}
