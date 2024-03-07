import java.util.Optional;

public class SquareGrid {

    public static int analyzeSquareGrid(Node origin) {
    	if (origin.uniqueDownRightCloses()) return recursiveExtends(origin.getDown(), origin.getRight(), 1);
    	return 1;
    }
    
    private static int recursiveExtends(Node D, Node R, int prevRes) {
    	int res = prevRes;
    	if (recursivelyCloses(D, R)) {
    		res++;
    		if (nextExtendValid(D, R)) return recursiveExtends(D.getDown(), R.getRight(), res);
    	}
    	return res;	
    }

    private static boolean recursivelyCloses(Node D, Node R) {
    	if (D == null || R == null) return false;
    	if (D == R) return true;
    	if (sameNextClose(D, R)) return true;
    	return recursivelyCloses(D.getRight(), R.getDown());
    }

    private static boolean nextExtendValid(Node D, Node R) {
    	boolean nextExtendValid = differentNextExtend(D, R)
    			&& recursivelyCloses(D.getDown(), R.getRight())
            	&& prevClose(D, R)
            		.map(prevClose -> prevClose.uniqueDownRightCloses())
            		.orElse(false);
        return nextExtendValid;        
    }
    
    private static Optional<Node> prevClose(Node D, Node R) {
    	if (D == null || R == null) return Optional.empty();
    	if (sameNextClose(D, R)) return Optional.of(D.getRight());
    	else return prevClose(D.getRight(), R.getDown());
    }
    
    private static boolean sameNextClose(Node D, Node R) {
    	return D.hasRight() && R.hasDown() && D.getRight() == R.getDown();
    }
    
    private static boolean differentNextExtend(Node D, Node R) {
    	return D.hasDown() && R.hasRight() && D.getDown() != R.getRight();
    }
}






///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	test04		SOLVED
//   
// 	  (0, 0) - (0, 1) - (0, 2) - (0, 3) - (0, 4)
//       |        |        |        |        |
//     (1, 0) - (1, 1) - (1, 2) - (1, 3) - (1, 4)
//       |        |        |        |        |
//     (2, 0) - (2, 1) - (2, 2) - (2, 3) - (2, 4)
//       |        |        |        |        |
//     (3, 0) - (3, 1) - (3, 2) - (3, 3) - (3, 4)
//       |        |        |        |        |
//     (4, 0) - (4, 1) - (4, 2) - (4, 3) - (4, 4)
//
//    Node[][] grid = generateLinkedGrid(5);
//
//    assertEquals(5, SquareGrid.analyzeSquareGrid(grid[0][0])); // got 3
//    assertEquals(4, SquareGrid.analyzeSquareGrid(grid[1][0])); // got 3
//
//
//  test07		SOLVED		UNSOLVED AGAIN		SOLVED AGAIN
//    
//	 (0, 0) - (0, 1) - (0, 2) - (0, 3)
//	   |        |        |        |
//	 (1, 0) - (1, 1) - (1, 2) - (1, 3)
//	   |        |        |	      X
//	 (2, 0) - (2, 1) - (2, 2) - (2, 3)
//	   |        |        |        |
//	 (3, 0) - (3, 1) X (3, 2) - (3, 3)
//    
//	 Note that the edges from (3, 1) to (3, 2) and from (1, 3) to (2, 3) are missing.
//    
//	n00.setRight(n01);
//	n01.setRight(n02);
//	n02.setRight(n03);
//
//	n00.setDown(n10);
//	n01.setDown(n11);
//	n02.setDown(n12);
//	n03.setDown(n13);
//
//	n10.setRight(n11);
//	n11.setRight(n12);
//	n12.setRight(n13);
//
//	n10.setDown(n20);
//	n11.setDown(n21);
//	n12.setDown(n22);
//  MISSING
//
//	n20.setRight(n21);
//	n21.setRight(n22);
//	n22.setRight(n23);
//
//	n20.setDown(n30);
//	n21.setDown(n31);
//	n22.setDown(n32);
//	n23.setDown(n33);
//
//	n30.setRight(n31);
//	MISSING
//	n32.setRight(n33);
//
//		assertEquals(3, SquareGrid.analyzeSquareGrid(n00));
//
//
//    test08	SOLVED
//    
//    // (0, 0) - (0, 1) - (0, 2) - (0, 3) - (0, 4)
//    //   |        |        |        |        |
//    // (1, 0) - (1, 1) - (1, 2) - (1, 3) - (1, 4)
//    //   |        |                 |        |
//    // (2, 0) - (2, 1)   (2, 2) - (2, 3) - (2, 4)
//    //   |        |        |        |        |
//    // (3, 0) - (3, 1) - (3, 2) - (3, 3) - (3, 4)
//    //   |        |        |        |        |
//    // (4, 0) - (4, 1) - (4, 2) - (4, 3) - (4, 4)
//
//    // Note that the edges from (1, 2) to (2, 2) and from (2, 1) to (2, 2) are missing. 
//
//    Node[][] grid = generateLinkedGrid(5);
//
//    grid[1][2].setDown(null);
//    grid[2][1].setRight(null);
//
//
// test10	SOLVED
//    
//     (0, 0) - (0, 1) - (0, 2)
//       |        |        |  
//     (1, 0) - (1, 1) - (1, 2)
//       |        |        |
//       |        |      (Node)
//       |        |
//     (2, 0) - (2, 1) - (Node)
//    
//    Node[][] grid = generateLinkedGrid(3);
//    Node extra = new Node();
//
//    grid[1][2].setDown(extra);
//
//
// test13	SOLVED MOTHERFUCKER, FUCK YEAH!!!!!
//    
//    Node[][] grid = generateLinkedGrid(3);
//    Node k = new Node();
//
//    grid[1][0].setRight(k);
//    k.setRight(grid[1][2]);
//    k.setDown(grid[2][1]);
//    
//     (0, 0) - (0, 1) - (0, 2)
//       |        |        |  
//       |      (1, 1) - (1, 2)
//       |             __ -' |
//     (1, 0) - (k) R- '    |
//       |        |        |
//     (2, 0) - (2, 1) - (2, 2)
//    
// Note that there are also edges from (k) to (1, 2) and from (1, 1) to (2, 1)