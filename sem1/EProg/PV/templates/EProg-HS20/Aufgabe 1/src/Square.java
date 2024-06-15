public class Square {
	
    public static void main(String[] args) {
    	int[][] t = { 
    			{0, 3, 5, 7, 2}, 
    			{2, 7, 4, 3, 0}, 
    			{4, 4, 6, 1, 4}, 
    			{7, 7, 4, 3, 3}, 
    			{0, 3, 5, 7, 2} 
    		};
    	System.out.println(checkProperty(t));
    }

    static boolean checkProperty(int[][] x) {
    	int n = x.length;
    	boolean hasProperty = true;
    	for (int i = 0; i < n; i++)
    		for (int j = i; j < n - i; j++) 
    			if (x[i][j] != x[n - 1 - i][j]) hasProperty = false;
    	return hasProperty;
    }

}
    
