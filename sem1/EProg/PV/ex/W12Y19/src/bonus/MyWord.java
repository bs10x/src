package bonus;

import java.util.HashSet;
import java.util.Set;

public class MyWord implements Comparable<MyWord> {
	
	private String word;
	private Set<Integer> positions;
	
	public MyWord(String word, Set<Integer> positions) {
		this.word = word;
		this.positions = positions;
	}
	
	public String getWord() {  
		return word;
	}
	
	public Set<Integer> getPositions() {
		return new HashSet<Integer>(positions);
	}
	
	@Override
	public String toString() {
		return word + ": " + positions.toString();
	}

	@Override
	public int compareTo(MyWord o) {
		// TODO: implementieren Sie die Ordnung wie auf dem Aufgabenblatt beschrieben
		return 0;
	}
}
