import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class PartialGridTest {
	
	@Test
	public void testIsPartalGrid01() {
		Node n00 = new Node();
		
		Node n10 = new Node();
		
		Node n01 = new Node();
		
		n00.setDown(n10);
		n00.setRight(n01);
		
		assertTrue(PartialGrid.isPartialGrid(n00));
		
		Node n11 = new Node();
		
		n01.setDown(n11);
		
		assertTrue(PartialGrid.isPartialGrid(n00));
		
		n10.setRight(new Node());
		
		assertFalse(PartialGrid.isPartialGrid(n00));
		
		n10.setRight(n11);
		
		assertTrue(PartialGrid.isPartialGrid(n00));
	}
	
	@Test
	public void testIsPartialGrid02() {
		Node n00 = new Node();
		
		Node m = new Node();
		
		n00.setRight(m);
		n00.setDown(m);
		
		assertFalse(PartialGrid.isPartialGrid(n00));
	}
	
	@Test
	public void testIsPartialGrid03() {
		Node n00 = new Node();
		Node n01 = new Node();
		Node n02 = new Node();
		
		Node n10 = new Node();
		Node n11 = new Node();
		Node n12 = new Node();
		
		Node n21 = new Node();
		
		n00.setRight(n01);
		n01.setRight(n02);
		n11.setRight(n12);
		
		n00.setDown(n10);
		n01.setDown(n11);
		n02.setDown(n12);
		n11.setDown(n21);
		
		assertTrue(PartialGrid.isPartialGrid(n00));
		
		n11.setRight(new Node());
		
		assertFalse(PartialGrid.isPartialGrid(n00));
	}
	
	@Test
	public void testIsRepresentable01() {
		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
		coordinates.add(new Coordinate(2,2));
		coordinates.add(new Coordinate(2,4));
		
		assertFalse(PartialGrid.isRepresentable(coordinates));
	}
	
	@Test
	public void testIsRepresentable02() {
		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
		coordinates.add(new Coordinate(2,2));
		coordinates.add(new Coordinate(2,3));
		coordinates.add(new Coordinate(2,1));
		
		assertTrue(PartialGrid.isRepresentable(coordinates));
	}
	
	@Test
	public void testIsRepresentable03() {
		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
		
		
		coordinates.add(new Coordinate(0,0));
		coordinates.add(new Coordinate(0,1));
		coordinates.add(new Coordinate(0,2));
		
		coordinates.add(new Coordinate(1,0));
		coordinates.add(new Coordinate(1,1));
		coordinates.add(new Coordinate(1,2));
		
		coordinates.add(new Coordinate(2,1));
		
		assertTrue(PartialGrid.isRepresentable(coordinates));	
	}	

}
