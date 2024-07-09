

import java.util.Arrays;

public class Triangle {
	
	int rowMax;
	int colMax;
	int[][] rows;
	int elements;
	
	public Triangle(int z, int s) {
		if (z < 1) {
			throw new IllegalArgumentException();
		} else {
			rowMax = z;
			colMax = s;
		}
		rows = new int[rowMax][];
		
		elements = 0;
		if (rowMax == 1) { // 1-dim array, 1 row
			rows[0] = new int[colMax];
			elements = colMax;
		}
		else { // > 1
           int j = 1;
           for (int i=0; i<rowMax; i++) {
            j = (i*(colMax-1)/(rowMax-1)) + 1;
            rows[i] = new int[j];
            elements += j;
           }
		}
	}
	
	
	public int get(int i, int j) {
		if (i < 0 || j < 0) throw new ArrayIndexOutOfBoundsException();
		if (i + 1 > rowMax || j + 1 > colMax) throw new ArrayIndexOutOfBoundsException();
		else return rows[i][j];
	}
	
	public void put(int i, int j, int value) {
		if (i < 0 || j < 0) throw new ArrayIndexOutOfBoundsException();
		if (i + 1 > rowMax || j + 1 > colMax) throw new ArrayIndexOutOfBoundsException();
		else rows[i][j] = value;
	}
	
	public void init(int[] data) {
		int counter = 0;
		for (int i = 0; i < rowMax; i++) {
			for (int j = 0; j < rows[i].length ; j++) {
				rows[i][j] = data[counter];
				counter++;
			}
		}
	}
	
	public int[] linear() {
		int[] linear = new int[elements];
		int counter = 0;
		for (int i = 0; i < rowMax; i++) {
			for (int j = 0; j < rows[i].length ; j++) {
				linear[counter] = rows[i][j];
				counter++;
			}
		}
		return linear;
	}
	
	public int getOrZero(int i, int j) {
		if (i < 0 || j < 0) return 0;
		if (i + 1 > rowMax || j + 1 > colMax) return 0;
		else return rows[i][j];
	}
	
	public void add(Triangle t) {
		for (int i = 0; i < rowMax; i++) {
			for (int j = 0; j < rows[i].length; j++) {
				rows[i][j] = this.get(i, j) + t.getOrZero(i, j);
			}
		}
	}
	
	public String toString() {
		   String tmp = "";
		   for (int i = 0; i<rowMax; i++) {
			   tmp = tmp + Arrays.toString(rows[i]) + " \n";
		   }
		   return tmp;
	   }
}
