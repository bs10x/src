package ethz.EProg;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.*;

//			for ( ; ; )			// ENDLESS LOOP
public class pls {
	
	public static void arrayThing() {

		int[] a = new int[6];

		a[0] = 5;
		a[5] = 5;
		
		int sum1 = 0;
		for (int x: a) sum1 += x;
		
		int sum2 = 0;
		for (int x: a) sum2 += a[x];
		
		System.out.println(sum1);
		System.out.println(sum2);
	}
	public static void arrayListThing() {
		ArrayList<Integer> aL = new ArrayList<>();
		for (int i = 0; i < 10; i++) aL.add(i);
		for (int x : aL) System.out.println(x);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static void swap(int i, int j, int[] A) {
		if (i == j) return;
		int tmp = A[j];
		A[j] = A[i];
		A[i] = tmp;
		return;
	}
	private static int[] heapify(int[] A, int i, int m) {
        while (2 * i <= m) {
            int j = 2 * i; 
            if (j + 1 <= m && A[j + 1] > A[j]) j++; 
            if (A[j] > A[i]) {
                int tmp = A[i]; 
                A[i] = A[j]; 
                A[j] = tmp; 
                i = j; 
            } else break; 
        }
        return A; 
    }
	public static int[] heapsort(int[] A){
        for (int i = A.length / 2; i >= 0; i--) heapify(A, i, A.length - 1);
        for (int i = A.length - 1; i >= 1; i--) {
            int tmp = A[0]; 
            A[0] = A[i]; 
            A[i] = tmp; 
            heapify(A, 0, i - 1); 
        }
        return A; 
    }

	public static int[] selectionsort(int[] A) {
		int n = A.length;
		for (int i = 0; i < n - 1; i++) {
			int minimumIndex = i;
			for (int j = i + 1; j < n; j++)
				if (A[j] < A[minimumIndex])
					minimumIndex = j;
			swap(i, minimumIndex, A);
		}
		return A;
	}
	public static int[] bubblesort(int[] A) {
		int n = A.length;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (A[j] > A[j + 1]) swap(j, j + 1, A);
		return A;
	}
	public static int[] insertionsort(int[] A) {
		int n = A.length;
		for (int i = 1; i < n; i++) {
			int currentValue = A[i];
			int j = i - 1;
			while (j > -1 && currentValue < A[j]) {
				A[j + 1] = A[j];
				j--;
			}
			A[j + 1] = currentValue;
		}
		return A;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static char[] randomCharArray(int size) {
		Random rng = new Random();
		char[] C = new char[size];
		for (int i = 0; i < size; i++) C[i] = (char) ('a' + rng.nextInt(26));
		return C;
	}
	public static int[] randomArray(int size) {
		Random rng = new Random();
		int[] randomArray = new int[size];
		for (int i = 0; i < size; i++) {
			randomArray[i] = rng.nextInt(size);
		}
		return randomArray;
	}
    public static int[][] randomMatrix(int m, int n) {
    	int[][] matrix = new int[m][n];
    	for (int i = 0; i < m; i++) matrix[i] = randomArray(n);
    	return matrix;
    }
    public static void printArray(int[] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i]);
			if (i < A.length -1) System.out.print(", ");
		}
		System.out.println();
	}
    public static void printArray(char[] C) {
		for (int i = 0; i < C.length; i++) System.out.print(C[i]);
		System.out.println();
	}
    public static void printMatrix(int[][] matrix) {
    	for (int i = 0; i < matrix.length; i++) {
    		printArray(matrix[i]);
		}
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static int fibonacci(int f1, int n) {
		int[] dp = new int[n+1];
		dp[0] = 0;
		dp[1] = f1;
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		return dp[n];
	}
    public static int minPathSum(int[][] matrix) {
    	int m = matrix.length;
    	int n = matrix[0].length;

    	int[][] DP = new int[m][n];
    	
    	DP[0][0] = matrix[0][0];
    	for (int i = 1; i < m; i++) DP[i][0] = DP[i - 1][0] + matrix[i][0];
    	for (int i = 1; i < n; i++) DP[0][i] = DP[0][i - 1] + matrix[0][i];
    	
    	for (int i = 1; i < m; i++) {
    		for (int j = 1; j < n; j++) {
    			DP[i][j] = Math.min(DP[i - 1][j], DP[i][j - 1]) + matrix[i][j];
    		}
    	}
    	return DP[m - 1][n - 1];
    }
    public static int uniquePaths(int m, int n) {
    	int[][] DP = new int[m][n];
    	for (int i = 0; i < m; i ++) {
    		for (int j = 0; j < n; j++) {
    			if (i == 0 || j == 0) {
    				DP[i][0] = 1;
    				DP[0][j] = 1;
    			}
    			else DP[i][j] = DP[i - 1][j] + DP[i][j - 1];
    		}
    	}
    	return DP[m - 1][n - 1];
    }
    public static int minPED(char[] C) {
    	/*
    	 * 		  	  0	 1	2  3  4	
    	 *        	  |	 |	|  |  |
    	 * 			[ a, b, c, d, d ]
    	 * 	
    	 *	0--a   	  0	 1  1  2  2	
    	 * 	1--b 	     0  1  1  2	
    	 * 	2--c	    	0  1  1
    	 * 	3--d   	 		   0  0
    	 * 	4--d	    		  0
    	 */
    	
    	int n = C.length;
    	int[][] DP = new int[n][n];
    	
    	for (int a = 0; a < n; a++) DP[a][a] = 0;		// base case, every substring of length 1 is palindrome
    	
    	for (int l = 2; l <= n; l++) {					// iterate over all possible lengths of substring
    		for (int i = 0; i <= n - l; i++) {			// iterate over all possible starting index
    			
    			int j = i + l - 1;						// set ending index for current length
    			
    			DP[i][j] = (C[i] == C[j]) ?				// are starting and ending character the same?
    					DP[i + 1][j - 1] :				// yes	-> no operation needed
    						1 +								// no	-> one additional operation needed
    						Math.min(					// take minimum between
    								Math.min(
    										DP[i][j - 1],	// insertion edit distance
    										DP[i + 1][j]	// deletion edit distance
    										),
    								DP[i + 1][j - 1]			// replace edit distance
    								);
    		}
    	}
    	return DP[0][n - 1];
    }
    public static int minED(char[] A, char[] B) {
    	int m = A.length;
    	int n = B.length;
    	
    	int[][] DP = new int[m][n];
    	
    	for (int a = 0; a < m; a++) DP[a][0] = a;
    	for (int b = 0; b < n; b++) DP[0][b] = b;
    	
    	for (int i = 1; i < m; i++) {
    		for (int j = 1; j < n; j++) {
    			DP[i][j] = (A[i] == B[j]) ?
    					DP[i - 1][j - 1] :
    						1 + Math.min(
    								Math.min(
    										DP[i][j - 1],
    										DP[i - 1][j]
    										),
    								DP[i - 1][j - 1]
    								);
    		}
    	}
    	
    	
    	return DP[m - 1][n - 1];
    }
    public static int LIS(int[] A) {
    	/*
    	 * 			0  1  2  3  4  5  6  7  8  9	->  j
    	 *			|  |  |  |  |  |  |  |  |  |
    	 *		  [ 3, 0, 2, 4, 3, 4, 3, 7, 5, 4 ]
    	 * 			1  1  2  3  1    
    	 */
    	int n = A.length;
    	int[] DP = new int[n];
    	for (int i = 0; i < n; i++) DP[i] = 1;				// base case, every element is LIS of length 1
    	int LIS = 1;										// max LIS is 1
    	for (int i = 1; i < n; i++) {						// iterate over every element (i) as index
    		for (int j = 0; j < i; j++) {					// iterate over every element (j) before index (i)			
    			if (A[i] > A[j] && DP[i] < DP[j] + 1) {		// if (i) > (j) && LIS (i) < LIS (j) + +
    				DP[i] = DP[j] + 1;						// LIS (i) = LIS (j) + 1
    				LIS = DP[i] > LIS ? DP[i] : LIS;		// maxLIS = max(LIS (i), maxLis)
    			}
    		}
    	}
    	return LIS;
    }
    public static int getMaxPo2Sub(int[] A) {

    	int n = A.length;
    	
        int h = 0;
        for (int i = 0; i < n; i++) h = Math.max(h, A[i]);
        
        int[] lastSeen = new int[h + 1];
        for (int i = 0; i <= h; i++) lastSeen[i] = -1;
        
        int[] DP = new int[n];
        int maxLength = 0;
        
        for (int i = 0; i < n; i++)  {										// iterate over all elements
        	DP[i] = 1;														// every element is Po2S of length 1
          														
        	for (int last = 1; last < (1 << 20); last *= 2) {					// 'last' iterates from 2^1 to 2^20
        	  
        		if (A[i] - last >= 0 && lastSeen[A[i] - last] != -1)
        			DP[i] = Math.max(DP[i], DP[lastSeen[A[i] - last]] + 1);
              
        		if (A[i] + last <= h && lastSeen[A[i] + last] != -1)
        			DP[i] = Math.max(DP[i], DP[lastSeen[A[i] + last]] + 1);
        	}
        	lastSeen[A[i]] = i;
        	maxLength = Math.max(maxLength, DP[i]);
        }
        return maxLength;
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WIP
    public static int cc(int[] C, int v) {
    	int n = C.length;
    	int[][] DP = new int[n][v];
    	
    	/*
    	 * 		0	1	2	3	4	5
    	 * 		|	|	|	|	|	|
    	 * 	  [ 1,  2,  3,  4,  4,  4 ]
    	 * 
    	 * 0--	0	0	0	0	0	0
    	 * 1--	1	0	0	0	0	0 	
    	 * 2--	1	1	0	0	0	0
    	 * 3--	
    	 * 4--
    	 * 5--
    	 * 
    	 */

    	
    	// base case, there exists coin with value v
    	for (int i = 0; i < n; i++) DP[i][0] = i + 1;
    	
    	for (int i = 0; i < n; i ++) {
    		for (int j = 0; j < v; j++) {
    			
    			
    			// idk what this does
    			if (C[i] == v) DP[i][j]++;
    			
    		}
    	}
    	return 0;
    }
    
    // WIP
    public static int maxKSegSub(int[] A, int k) {
    	int n = A.length;
    	return 0;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    public static String toHex(char c) {
    	return String.format("%04x", (int) c);
    }
    
    public static void fuckaroundandfindout() {
    	Scanner in = new Scanner(System.in);
    	PrintStream out = new PrintStream(System.out);
    	
    	out.println("Enter some text: ");
    	String input = in.nextLine();
    	StringBuilder output = new StringBuilder();
    	
    	// stream the input and convert each char to hex, append it with whitespace to output
    	
    	output.append(input);
//    	input.toCharArray().length.stream().forEach(c -> c.toHex())
    	

    	
    	out.println(output);    	
    	
    	
    	in.close();
    	out.close();
    }
    
    

}
