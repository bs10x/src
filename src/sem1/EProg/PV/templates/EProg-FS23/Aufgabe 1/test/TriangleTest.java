
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TriangleTest {
	
	@Test
	void test1() {
		Triangle t = new Triangle(4, 4);
		
		assertEquals(0, t.get(0, 0));
		assertEquals(0, t.get(1, 0));
		assertEquals(0, t.get(1, 1));
		assertEquals(0, t.get(2, 0));
		assertEquals(0, t.get(2, 1));
		assertEquals(0, t.get(2, 2));
		assertEquals(0, t.get(3, 0));
		assertEquals(0, t.get(3, 1));
		assertEquals(0, t.get(3, 2));
		assertEquals(0, t.get(3, 3));
	}  
	
	@Test
	void test2() {
		Triangle t = new Triangle(4, 4);
		
		t.put(2, 1, 4);
		t.put(3, 2, 5);
		t.put(2, 1, 6);
		
		assertEquals(0, t.get(0, 0));
		assertEquals(0, t.get(1, 0));
		assertEquals(0, t.get(1, 1));
		assertEquals(0, t.get(2, 0));
		assertEquals(6, t.get(2, 1));
		assertEquals(0, t.get(2, 2));
		assertEquals(0, t.get(3, 0));
		assertEquals(0, t.get(3, 1));
		assertEquals(5, t.get(3, 2));
		assertEquals(0, t.get(3, 3));
	}  

	@Test
	void test3() {
		Triangle t = new Triangle(4, 4);
		int[] a = new int[10];
		for (int i=0; i<a.length; i++) {
			a[i] = i;
		}
		
		t.init(a);
		
		assertEquals(0, t.get(0, 0));
		assertEquals(1, t.get(1, 0));
		assertEquals(2, t.get(1, 1));
		assertEquals(3, t.get(2, 0));
		assertEquals(4, t.get(2, 1));
		assertEquals(5, t.get(2, 2));
		assertEquals(6, t.get(3, 0));
		assertEquals(7, t.get(3, 1));
		assertEquals(8, t.get(3, 2));
		assertEquals(9, t.get(3, 3));
	}  

	@Test
	void test4() {
		Triangle t = new Triangle(4, 4);
		int[] a = new int[10];
		for (int i=0; i<a.length; i++) {
			a[i] = i;
		}
		
		t.init(a);
		int[] r = t.linear();
		
		assertArrayEquals(a,r);
	}  
	
	@Test
	void test5() {
		Triangle t = new Triangle(4,4);
		Triangle s = new Triangle(4,4);
		
		t.put(1, 1, 2);
		t.put(2, 1, 5);
		s.put(2, 1, 4);
		s.put(3,0, 1);
		
		t.add(s);
		
		assertEquals(0, t.get(0, 0));
		assertEquals(0, t.get(1, 0));
		assertEquals(2, t.get(1, 1));
		assertEquals(0, t.get(2, 0));
		assertEquals(9, t.get(2, 1));
		assertEquals(0, t.get(2, 2));
		assertEquals(1, t.get(3, 0));
		assertEquals(0, t.get(3, 1));
		assertEquals(0, t.get(3, 2));
		assertEquals(0, t.get(3, 3));
	}  

	@Test
	void test6() {
		Triangle t = new Triangle(4, 4);
		int[] a = new int[10];
		for (int i=0; i<a.length; i++) {
			a[i] = i;
		}
		
		t.init(a);
		
		Triangle s = new Triangle(4,4);
		s.init(a);
		t.add(s);
		
		assertEquals(0, t.get(0, 0));
		assertEquals(2, t.get(1, 0));
		assertEquals(4, t.get(1, 1));
		assertEquals(6, t.get(2, 0));
		assertEquals(8, t.get(2, 1));
		assertEquals(10, t.get(2, 2));
		assertEquals(12, t.get(3, 0));
		assertEquals(14, t.get(3, 1));
		assertEquals(16, t.get(3, 2));
		assertEquals(18, t.get(3, 3));
	}  

	@Test
	void test7() {
		Triangle t = new Triangle(4, 4);
		int[] a = new int[10];
		for (int i=0; i<a.length; i++) {
			a[i] = i;
		}
		t.init(a);
		
		Triangle s = new Triangle(3,2);
		int[] b = new int[4];
		for (int i=0; i<b.length; i++) {
			b[i] = i;
		}
		s.init(b);
		
		t.add(s);
		
		assertEquals(0, t.get(0, 0));
		assertEquals(2, t.get(1, 0));
		assertEquals(2, t.get(1, 1));
		assertEquals(5, t.get(2, 0));
		assertEquals(7, t.get(2, 1));
		assertEquals(5, t.get(2, 2));
		assertEquals(6, t.get(3, 0));
		assertEquals(7, t.get(3, 1));
		assertEquals(8, t.get(3, 2));
		assertEquals(9, t.get(3, 3));
	}  

}
