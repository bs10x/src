
import java.util.Arrays;

public class Triangle {
	
	int rowMax;
	int colMax;
	int[][] rows;
	
	public Triangle(int z, int s) {
		if (z < 1) {
			throw new IllegalArgumentException();
		} else {
			rowMax = z;
			colMax = s;
		}
		rows = new int[rowMax][];
		
		if (rowMax == 1) {
			// 1-dim array, 1 row
			rows[0] = new int[colMax];
		} else { // > 1
            int j = 1;
           for (int i=0; i<rowMax; i++) {
            j = (i*(colMax-1)/(rowMax-1)) + 1;
            rows[i] = new int[j];
           }
		}
	}
	
	public int get(int i, int j) {
		// TODO
		return -1;
	}
	
	public void put(int i, int j, int value) {
		// TODO
	}
	
	public void init(int[] data) {
	    // TODO
	}
	
	public int[] linear() {
		// TODO
		return null;
	}
	
	public void add(Triangle t){
		// TODO
	}
	
	public String toString() {
		   String tmp = "";
		   for (int i = 0; i<rowMax; i++) {
			   tmp = tmp + Arrays.toString(rows[i]) + " \n";
		   }
		   return tmp;
	   }
}
