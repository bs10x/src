import java.io.*;
import java.util.*;

public class SeqAnalyzer {
	
	Map<Integer, SeqNumber> numbers;
	
	SeqNumber least;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner inputSequence = new Scanner("1 3 2 9 2 45");
		SeqAnalyzer analyzer = new SeqAnalyzer(inputSequence);

		List<SeqNumber> seqNumbers = analyzer.getNumbers();
		
		for (SeqNumber seqNumber : seqNumbers) {
			System.out.println(seqNumber);
		}
		
		System.out.println(analyzer.getRanking(3));
		
		
		
	}

	public SeqAnalyzer(Scanner scanner) {
		this.numbers = new HashMap<>();
		int i = 0;
		while (scanner.hasNext()) {
			int a = scanner.nextInt();
			if (numbers.keySet().contains(a)) {
				Set<Integer> newSet = new HashSet<>();
				for (int y : numbers.get(a).getPositions()) newSet.add(y);
				newSet.add(i);
				numbers.replace(a, new SeqNumber(a, newSet));
			} else numbers.put(a, new SeqNumber(a, Set.of(i)));
			i++;
		}
		this.least = new SeqNumber(Integer.MAX_VALUE, Set.of(Integer.MAX_VALUE));
		for (SeqNumber s : numbers.values()) if (isGreater(least, s)) least = s;
	}
	
	public List<SeqNumber> getNumbers() {
		List<SeqNumber> list = numbers.values().stream().toList();
		return list;
	}

	public List<SeqNumber> getRanking(int number) {
		if (getNumbers().size() < number) throw new IllegalArgumentException();
		
		if (number == 0) return new ArrayList<>();

		List<SeqNumber> ranking = (List<SeqNumber>) getNumbers().stream().sorted(Comparator.comparingInt((SeqNumber s) -> s.min)).toList().subList(0, number);
		return ranking;
	}

	public boolean isGreater(SeqNumber a, SeqNumber b) {
		if (a == null) return false;
		if (b == null) return true;
		return a.computeMin() > b.computeMin();
	}

	
	
}
