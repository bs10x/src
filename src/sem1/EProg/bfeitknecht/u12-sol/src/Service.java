import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Service {
	
	private List<Record> records;

    public Service(Scanner scanner) {
        records = new LinkedList<>();
        while (scanner.hasNextLine()) {
        	Scanner line = new Scanner(scanner.nextLine());
        	if (line.hasNext()) { // line is not empty
        		records.add(new Record(
            			line.nextInt(), // scan id
            			new double[]{ // scan 3 grades
            					line.nextDouble(), 
            					line.nextDouble(), 
            					line.nextDouble()
            			}
            	));
        	}
        }
    }

    public List<Integer> critical(double bound1, double bound2) {
        List<Integer> result = new LinkedList<>();
        for (Record rec : records) { // filter students
        	if (rec.grades[0] <= bound1 && rec.grades[1] + rec.grades[2] < bound2) {
        		result.add(rec.id);
        	}
        }
        return result;
    }

    public List<Integer> top(int limit) {
    	// first sort, then take the sublist, and finally map to the id.
        Collections.sort(records);
        List<Record> relevant = records.subList(0, limit);
        List<Integer> result = new LinkedList<>();
        for (Record rec : relevant) {
        	result.add(rec.id);
        }
        return result;
    }
}

class Record implements Comparable<Record> {
	int id;
	double[] grades;
	
	Record(int id, double[] grades) {
		this.id = id;
		this.grades = grades;
	}
	
	double gradeSum() {
		double result = 0;
		for (double grade : grades) {
			result += grade;
		}
		return result;
	}

	@Override
	public int compareTo(Record other) { // want highest grades first, i.e. descending order of sums
		return -Double.compare(gradeSum(), other.gradeSum());
	}
}