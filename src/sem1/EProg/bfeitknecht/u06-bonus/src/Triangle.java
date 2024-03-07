import java.util.Arrays;

public class Triangle {
	
	int rowMax;
	int colMax;
	int[][] rows;
	
	public Triangle(int z, int s) {
		if (z <= 1 || s <= 1) {
			throw new IllegalArgumentException();
		} else {
			rowMax = z;
			colMax = s;
		}
		rows = new int[rowMax][];
		
		int j = 1;
        for (int i=0; i<rowMax; i++) {
         j = (i*(colMax-1)/(rowMax-1)) + 1;
         rows[i] = new int[j];
        }
	}
	
	public int get(int i, int j) {
        if (i < 0 || i >= rowMax || j < 0 || j >= rows[i].length) {
            throw new IllegalArgumentException("Invalid indices");
        }
        return rows[i][j];
    }
	
	public void put(int i, int j, int value) {
        if (i < 0 || i >= rowMax || j < 0 || j >= rows[i].length) {
            throw new IllegalArgumentException("Invalid indices");
        }
        rows[i][j] = value;
    }
	
	public void init(int[] data) {
        if (data.length != linear().length) {
            throw new IllegalArgumentException("Data length does not match the size of the triangle");
        }
        int dataIndex = 0;
        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < rows[i].length; j++) {
                rows[i][j] = data[dataIndex];
                dataIndex++;
            }
        }
    }
	
	public int[] linear() {
        int[] result = new int[calculateLinearArraySize()];
        int index = 0;
        
        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < rows[i].length; j++) {
                result[index] = rows[i][j];
                index++;
            }
        }
        
        return result;
    }
	
	// helper function
		private int calculateLinearArraySize() {
	        int size = 0;
	        for (int i = 0; i < rowMax; i++) {
	            size += rows[i].length;
	        }
	        return size;
	    }
	
	public void add(Triangle t) {
		if (t == null) return;
        if (rowMax != t.rowMax || colMax != t.colMax) {
            throw new IllegalArgumentException("Triangles must have the same dimensions for addition");
        }
        
        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < rows[i].length; j++) {
                if (j < t.rows[i].length) {
                    rows[i][j] += t.rows[i][j];
                }
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
	
	
	
	// main method for testing
	public static void main(String[] args) {
		// printing unpopulated Triangle "D" and "data"
		Triangle D = new Triangle(2, 2);
		int[] data = {1, 2, 3};
		System.out.println(D.toString());	// should return [0, 0, 0]
		System.out.println(Arrays.toString(data) +"\n");
		
		// populate "D" with "data" and print it
		D.init(data);
		System.out.println(D.toString() + "\n");
		
		// test void init(int[] data)
		int[] y = D.linear();
		System.out.println(Arrays.equals(y, data)+ "\n");	//should return true
		
		// test int get(int i, int j)
		System.out.println(D.get(0, 0)+"\n");	// should return 1
		
		// make new unpopulated Triangle "t" and add it to "D", print it
		Triangle t = new Triangle(2, 2);
		D.add(t);
		System.out.println(D.toString());	// should return [1, 2, 3]
		
		// populate "t" with "data", add it to "D" and print it
		t.init(data);
		D.add(t);
		System.out.println(D.toString());	// should return [2, 4, 6]
		
		// check if a nonexistent Triangle returns an unchanged "D"
		// and check if put(int i, int j, int value) works
		Triangle u = null;
		D.add(u);
		D.put(0, 0, 0);
		System.out.println(D.toString());	// should return [0, 4, 6]
		
		
		
		
		
	}
}
