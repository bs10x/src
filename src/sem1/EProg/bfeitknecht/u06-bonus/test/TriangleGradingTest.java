
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TriangleGradingTest {
	
	@Test
	void test01GIVEN01() {
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
	void test04_NumberOfColsPerRow() {
		final int Z = 10; 
		final int S = 5;
		
		Triangle t = new Triangle(Z,S);
		for (int i=0; i<t.rows.length; i++) {
			final int expected_num_cols = (i * (S - 1)/(Z - 1)) + 1;
			final int row_idx = i;
			for (int j=0; j<expected_num_cols; j++) {
				final int col_idx = j;
				assertDoesNotThrow(() -> {
					t.get(row_idx, col_idx);
				}, "Zugriff auf (" + i + "," + j + ") sollte nicht fehlschlagen (Länge = " + t.rows[row_idx].length + ")");
			}
		}
	}
	
	@Test
	void test05_NumberOfColsPerRow() {
		final int Z = 3; 
		final int S = 2;
		
		Triangle t = new Triangle(Z,S);
		for (int i=0; i<t.rows.length; i++) {
			final int expected_num_cols = (i * (S - 1)/(Z - 1)) + 1;
			final int row_idx = i;
			for (int j=0; j<expected_num_cols; j++) {
				final int col_idx = j;
				assertDoesNotThrow(() -> {
					t.get(row_idx, col_idx);
				}, "Zugriff auf (" + i + "," + j + ") sollte nicht fehlschlagen (Länge = " + t.rows[row_idx].length + ")");
			}
		}
	}
	
	@Test
	void test06PutGIVEN02() {
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
	void test08_SimplePutGet() {
		Triangle t = new Triangle(4, 4);
		
		t.put(1, 1, 11);
		assertEquals(11, t.get(1, 1), "put()/get() verändert die Daten");
	}
	
	@Test
	void test09_InvalidPutGet() {
		Triangle t = new Triangle(4, 4);
		
		try {
			t.put(10, 10, 11);
		} catch (Exception e) {
			// nop
		}
		
		assertEquals(0, t.get(1, 1), "put()/get() verändert die Daten nicht, wenn die Koordinaten ausserhalb des Dreiecks liegen");
	}
	
	@Test
	void test10InitGIVEN03() {
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
	void test11InitTallShape() {
		Triangle t = new Triangle(10, 2);
		int[] data = {
				1,2,3,4,5,6,7,8,9,100,101
		};
		t.init(data);
		
		assertEquals(t.get(8, 0), 9, "Die vorletzte Reihe hat Wert 9, d.h. alle Reihen bis 9 haben nur eine Zeile.");
		assertEquals(t.get(9, 0), 100, "Die letzte Reihe hat Wert 100");
		assertEquals(t.get(9, 1), 101, "Die letzte Reihe hat Wert 101");	
	}

	@Test
	void test12LinearGIVEN04() {
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
	void test13LinearTall() {
		Triangle t = new Triangle(10, 2);
		int[] a = new int[11];
		for (int i=0; i<a.length; i++) {
			a[i] = i;
		}
		
		t.init(a);
		int[] r = t.linear();
		
		assertArrayEquals(a,r);
	}
	
	@Test
	void test14AddGIVEN05() {
		Triangle t = new Triangle(4,4);
		Triangle s = new Triangle(4,4);
		
		t.put(1, 1, 2);
		t.put(2, 1, 5);
		s.put(2, 1, 4);
		s.put(3, 0, 1);
		
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
	void test15AddGIVEN06() {
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
	void test16AddGIVEN07() {
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
	
	@Test
	void test17AddUnaffected() {
		Triangle t = new Triangle(2, 2);
		Triangle z = new Triangle(2, 2);
		
		t.init(new int[] {
			1, 2, 3
		});
		
		z.init(new int[] {
			0, 2, 3
		});
		
		t.add(z);
		
		assertEquals(1, t.get(0, 0), "Index 0,0 sollte nicht verändert sein");
	}


	@Test
	void test18ReallyLongShape() {
		Triangle t = new Triangle(2, 20);
		
		t.init(new int[] {
				1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21
		});
		
		assertEquals(2, t.get(1, 0), "Index 1,0 repraesentiert das 21. Element");
	}
	
	@Test
	void test19LargeTriangle() {
		Triangle t = new Triangle(20,20);
		
		int[] data = {
			    0,
			    0, 0,
			    0, 0, 0,
			    0, 0, 0, 0,
			    0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			};
		t.init(data);
		
		assertEquals(0, t.get(11,4), "Index 11,6 sollte den Wert 0 haben");
		assertEquals(8, t.get(11,5), "Index 11,5 sollte den Wert 8 haben");
		assertEquals(0, t.get(11,6), "Index 11,6 sollte den Wert 0 haben");
	}
	
	@Test
	void test20AddLarger() {
		Triangle t = new Triangle(2, 2);
		
		Triangle s = new Triangle(10, 10);
		int[] data = {
			    0,
			    0, 0,
			    0, 0, 0,
			    0, 0, 0, 0,
			    0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			};
		s.init(data);
		
		t.add(s);
		
		assertEquals(0, t.get(0, 0), "Index 0,0 ist von add() unbeinflusst.");
		assertEquals(0, t.get(1, 0), "Index 1,0 ist von add() unbeinflusst.");
		assertEquals(0, t.get(1, 1), "Index 1,1 ist von add() unbeinflusst.");
	}
}
