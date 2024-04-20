import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class GridTest {
	
	@Test
	public void testBasic1() {
		Node n00 = new Node(null, null);
		Node n01 = new Node(null, null);
		n00.setRight(n01);
		Node n10 = new Node(null, null);
		n00.setBottom(n10);
		Node n11 = new Node(null, null);
		
		n01.setBottom(n11);
		n10.setRight(n11);
		
		Grid.adjustGrid(n00, 3, 2);
		
		assertEquals(n01, n00.getRight());
		assertEquals(n10, n00.getBottom());
		assertEquals(n11, n01.getBottom());
		assertEquals(n11, n10.getRight());
		
		assertNull(n01.getRight());
		assertNull(n11.getRight());
		
		
		Node n20 = n10.getBottom();
		assertNotNull(n20);
		Node n21 = n11.getBottom();
		assertNotNull(n21);
		
		assertEquals(n21, n20.getRight());
        assertNull(n20.getBottom());
		assertNull(n21.getBottom());
		assertNull(n21.getRight());		
	}
	
	@Test
	public void testBasic2() {
		Node n00 = new Node(null, null);
		Node n01 = new Node(null, null);
		n00.setRight(n01);
		Node n10 = new Node(null, null);
		n00.setBottom(n10);
		Node n11 = new Node(null, null);
		
		n01.setBottom(n11);
		n10.setRight(n11);
		
		Grid.adjustGrid(n00, 1, 2);
		
		assertEquals(n01, n00.getRight());
		assertNull(n00.getBottom());
		assertNull(n01.getRight());
		assertNull(n01.getBottom());
	}
	
	@Test
	public void testExerciseSheet() {
		//row 0
		Node n00 = new Node(null, null);
		Node n01 = new Node(null, null);
		Node n02  =new Node(null, null);
		n00.setRight(n01);
		n01.setRight(n02);
		
		//row 1
		Node n10 = new Node(null, null);
		Node n11 = new Node(null, null);
		Node n12  =new Node(null, null);
        n10.setRight(n11);
        n11.setRight(n12);
        
        n00.setBottom(n10);
        n01.setBottom(n11);
        n02.setBottom(n12);
        
        Grid.adjustGrid(n00, 4, 2);
        
        //after call: row 0
        Node m01 = n00.getRight();
        assertEquals(n01, m01);
        assertNull(m01.getRight());
        
        //after call: row 1   
        Node m10 = n00.getBottom();
        assertEquals(n10, m10);
        Node m11 = n01.getBottom();
        assertEquals(n11, m11);
        assertEquals(m11, m10.getRight());
        assertNull(m11.getRight());
        
        //after call: row 2
        Node m20 = m10.getBottom();
        assertNotNull(m20);
        Node m21 = m11.getBottom();
        assertNotNull(m21);
        assertEquals(m21, m20.getRight());
        assertNull(m21.getRight());
        
        //after call: row 3
        Node m30 = m20.getBottom();
        assertNotNull(m30);
        Node m31 = m21.getBottom();
        assertNotNull(m31);
        assertEquals(m31, m30.getRight());
        assertNull(m31.getRight());
        
        assertNull(m30.getBottom());
        assertNull(m31.getBottom());
	}

}
