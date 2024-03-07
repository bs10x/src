public class Main {

    public static void main(String[] args) {
//        Node n00 = new Node();
//        Node n10 = new Node();
//        Node n01 = new Node();
//
//        n00.setDown(n10);
//        n00.setRight(n01);
//
//        System.out.println("analyzeSquareGrid Resultat (erwartet: 1) " + SquareGrid.analyzeSquareGrid(n00));
//
//        Node n11 = new Node();
//        n01.setDown(n11);
//        n10.setRight(new Node());
//
//        System.out.println("analyzeSquareGrid Resultat (erwartet: 1): " + SquareGrid.analyzeSquareGrid(n00));
//
//        n10.setRight(n11);
//
//        System.out.println("analyzeSquareGrid Resultat (erwartet: 2): " + SquareGrid.analyzeSquareGrid(n00));
        
        
       
    	
    	
//		 (0, 0) - (0, 1) - (0, 2)
//		   |        |        |
//		 (1, 0) - (1, 1) - (1, 2)
//		   |        |        |
//		   |      (Node)     |
//		   |                 |
//		 (2, 0) - (2, 1) - (2, 2)


//		Node n00 = new Node();
//		Node n01 = new Node();
//		Node n02 = new Node();
//
//		Node n10 = new Node();
//		Node n11 = new Node();
//		Node n12 = new Node();
//
//		Node n20 = new Node();
//		Node n21 = new Node();
//		Node n22 = new Node();
//
//		n00.setRight(n01);
//		n01.setRight(n02);
//
//		n00.setDown(n10);
//		n01.setDown(n11);
//		n02.setDown(n12);
//
//		n10.setRight(n11);
//		n11.setRight(n12);
//
//		n10.setDown(n20);
//		n11.setDown(new Node());
//		n12.setDown(n22);
//
//		n20.setRight(n21);
//		n21.setRight(n22);
//
//		System.out.println(n00.hasDown());
//		System.out.println(n00.hasRight());
//		System.out.println(n10.hasDown());
//		System.out.println(n01.hasRight());
//		System.out.println(n20.hasRight());
//		System.out.println(n02.hasDown());
//		System.out.println(n21.hasRight());
//		System.out.println(n12.hasDown());   
//		
//		System.out.println(SquareGrid.analyzeSquareGrid(n00));
		
		
		// testing getClosingNode
//		System.out.println(SquareGrid.getClosingNode(n10, n01));
//		System.out.println(n11);
		
    	
//    	Node singlePringle = new Node();
//    	System.out.println(SquareGrid.analyzeSquareGrid(singlePringle));
    	
    
    	
        // (0, 0) - (0, 1) - (0, 2) - (0, 3) - (0, 4)
        //   |        |        |        |        |
        // (1, 0) - (1, 1) - (1, 2) - (1, 3) - (1, 4)
        //   |        |        |        |        |
        // (2, 0) - (2, 1) - (2, 2) - (2, 3) - (2, 4)
        //   |        |        |        |        |
        // (3, 0) - (3, 1) - (3, 2) - (3, 3) - (3, 4)
        //   |        |        |        |        |
        // (4, 0) - (4, 1) - (4, 2) - (4, 3) - (4, 4)

//        Node[][] grid = generateLinkedGrid(5);
//        
//        System.out.println("expecting 5, getting: " + SquareGrid.analyzeSquareGrid(grid[0][0]));
//        System.out.println("expecting 4, getting: " + SquareGrid.analyzeSquareGrid(grid[1][0]));
    	
    	
    			// (0, 0) - (0, 1) - (0, 2) - (0, 3)
    			//   |        |        |        |
    			// (1, 0) - (1, 1) - (1, 2) - (1, 3)
    			//   |        |        |
    			// (2, 0) - (2, 1) - (2, 2) - (2, 3)
    			//   |        |        |        |
    			// (3, 0) - (3, 1)   (3, 2) - (3, 3)

    			// Note that the edges from (3, 1) to (3, 2) and from (1, 3) to (2, 3) are missing.

//    			Node n00 = new Node();
//    			Node n01 = new Node();
//    			Node n02 = new Node();
//    			Node n03 = new Node();
//
//    			Node n10 = new Node();
//    			Node n11 = new Node();
//    			Node n12 = new Node();
//    			Node n13 = new Node();
//
//    			Node n20 = new Node();
//    			Node n21 = new Node();
//    			Node n22 = new Node();
//
//    			Node n30 = new Node();
//    			Node n31 = new Node();
//
//    			Node n23 = new Node();
//    			Node n32 = new Node();
//    			Node n33 = new Node();
//
//    			n00.setRight(n01);
//    			n01.setRight(n02);
//    			n02.setRight(n03);
//
//    			n00.setDown(n10);
//    			n01.setDown(n11);
//    			n02.setDown(n12);
//    			n03.setDown(n13);
//
//    			n10.setRight(n11);
//    			n11.setRight(n12);
//    			n12.setRight(n13);
//
//    			n10.setDown(n20);
//    			n11.setDown(n21);
//    			n12.setDown(n22);
//
//    			n20.setRight(n21);
//    			n21.setRight(n22);
//
//    			n20.setDown(n30);
//    			n21.setDown(n31);
//
//    			n30.setRight(n31);
//
//    			n22.setRight(n23);
//    			n22.setDown(n32);
//    			n23.setDown(n33);
//    			n32.setRight(n33);
//    			
//    			System.out.println("expected 3 but got: " + SquareGrid.analyzeSquareGrid(n00));
    	
    	
    	
    			
    	

		
		
		
		
		
    } // END OF MAIN
    
    
    
    
    
    private static Node[][] generateLinkedGrid(int size) {
        Node[][] grid = new Node[size][size];
        for (int i = size - 1; i >= 0; i--) {
            for (int j = size - 1; j >= 0; j--) {
                Node newNode = new Node();
                grid[i][j] = newNode;
                if (i != size - 1) {
                    newNode.setDown(grid[i + 1][j]);
                }
                if (j != size - 1) {
                    newNode.setRight(grid[i][j + 1]);
                }
            }
        }
        return grid;
    }
}
