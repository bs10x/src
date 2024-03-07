import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class PyramidTest {
	
	@Rule
	public Timeout globalTimeout= Timeout.seconds(5);
	
    @Test
    public void testPyramidTwoLevels() {
        Node pyramid = new Node(
                new Node(null, null),
                new Node(null, null));
        assertTrue(Pyramid.isPyramid(pyramid));
    }
    
    @Test
    public void testPyramidThreeLevels() {
        Node center = new Node(null, null);
        Node pyramid = new Node(
                new Node(
                        new Node(null, null),
                        center),
                new Node(
                        center,
                        new Node(null, null)));
        assertTrue(Pyramid.isPyramid(pyramid));
    }
    
    @Test
    public void testPyramidFourLevels() {
        Node[] row4 = {
                new Node(null, null),
                new Node(null, null),
                new Node(null, null),
                new Node(null, null)
        };
        Node[] row3 = {
                new Node(row4[0], row4[1]),
                new Node(row4[1], row4[2]),
                new Node(row4[2], row4[3])
        };
        Node pyramid = new Node(
                new Node(
                        row3[0],
                        row3[1]),
                new Node(
                        row3[1],
                        row3[2]));
        assertTrue(Pyramid.isPyramid(pyramid));
    }
    
    @Test
    public void testNotEnoughNodes1() {
    	Node pyramid = new Node(
    			new Node(null, new Node(null,null)),
    			new Node(null, null)
    			);
    	assertFalse(Pyramid.isPyramid(pyramid));
    }
    	
    @Test
    public void testNotEnoughNodes2() {
    	Node n3 = new Node(null,null);
    	
    	Node pyramid = new Node(
    			new Node(n3, n3),
    			new Node(n3, n3)
    			);
    	assertFalse(Pyramid.isPyramid(pyramid));
    }
    
    @Test
    public void tooManyNodes() {
    	Node[] finalRow = {
    			new Node(null,null),
    			new Node(null,null),
    			new Node(null,null),
    			new Node(null,null)
    	};
    	
    	Node pyramid = new Node(
    			new Node(finalRow[0], finalRow[1]),
    			new Node(finalRow[2], finalRow[3])
    			);
    	assertFalse(Pyramid.isPyramid(pyramid));
    }
    
    @Test
    public void testIncorrectLink1() {
    	Node[] finalRow = {
    			new Node(null,null),
    			new Node(null,null),
    			new Node(null,null)
    	};
    	
    	Node pyramid = new Node(
    			new Node(finalRow[0], finalRow[2]), //!
    			new Node(finalRow[1], finalRow[2])
    			);
    	assertFalse(Pyramid.isPyramid(pyramid));
    }
    
    @Test
    public void testIncorrectLink2() {
    	Node[] row4 = {
    			new Node(null,null),
    			new Node(null,null),
    			new Node(null,null),
    			new Node(null, null)
    	};
    	
    	Node[] row3 = {
    			new Node(row4[0], row4[1]),
    			new Node(row4[1], row4[2]),
    			new Node(row4[0], row4[3])  //!
    	};
    	
    	Node pyramid = new Node(
    			new Node(row3[0], row3[1]),
    			new Node(row3[1], row3[2])
    			);
    	assertFalse(Pyramid.isPyramid(pyramid));
    }
}
