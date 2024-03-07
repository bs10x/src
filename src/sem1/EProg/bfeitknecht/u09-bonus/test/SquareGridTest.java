import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SquareGridTest {

	@Test
	public void testIsSquareGrid01() {
		// (0, 0) - (0, 1)
		//   |        |
		// (1, 0) - (1, 1)

		Node n00 = new Node();
		Node n01 = new Node();

		Node n10 = new Node();
		Node n11 = new Node();

		n00.setRight(n01);

		n00.setDown(n10);
		n01.setDown(n11);

		n10.setRight(n11);

		assertEquals(2, SquareGrid.analyzeSquareGrid(n00));
	}

	@Test
	public void testIsSquareGrid02() {
		// (0, 0) - (0, 1)
		//   |        |
		//   |      (Node)
		//   |
		// (1, 0) - (Node)

		Node n00 = new Node();
		Node n01 = new Node();
		Node n10 = new Node();

		Node m = new Node();
		Node k = new Node();

		n00.setRight(n01);
		n00.setDown(n10);

		n01.setDown(m);
		n10.setRight(k);

		assertEquals(1, SquareGrid.analyzeSquareGrid(n00));
	}

	@Test
	public void testIsSquareGrid03() {
		// (0, 0) - (0, 1) - (0, 2)
		//   |        |        |
		// (1, 0) - (1, 1) - (1, 2)

		Node n00 = new Node();
		Node n01 = new Node();
		Node n02 = new Node();

		Node n10 = new Node();
		Node n11 = new Node();
		Node n12 = new Node();

		n00.setRight(n01);
		n01.setRight(n02);

		n00.setDown(n10);
		n01.setDown(n11);
		n02.setDown(n12);

		n10.setRight(n11);
		n11.setRight(n12);

		assertEquals(2, SquareGrid.analyzeSquareGrid(n00));
		assertEquals(2, SquareGrid.analyzeSquareGrid(n01));
	}
	
	@Test
	public void testIsSquareGrid04() {
		// (0, 0) - (0, 1) - (0, 2)
		//   |        |        |
		// (1, 0) - (1, 1) - (1, 2)
		//   |        |        |
		// (2, 0) - (2, 1) - (2, 2)

		Node n00 = new Node();
		Node n01 = new Node();
		Node n02 = new Node();

		Node n10 = new Node();
		Node n11 = new Node();
		Node n12 = new Node();

		Node n20 = new Node();
		Node n21 = new Node();
		Node n22 = new Node();

		n00.setRight(n01);
		n01.setRight(n02);

		n00.setDown(n10);
		n01.setDown(n11);
		n02.setDown(n12);

		n10.setRight(n11);
		n11.setRight(n12);

		n10.setDown(n20);
		n11.setDown(n21);
		n12.setDown(n22);

		n20.setRight(n21);
		n21.setRight(n22);

		assertEquals(3, SquareGrid.analyzeSquareGrid(n00));
	}
	
	@Test
	public void testIsSquareGrid05() {
		// (0, 0) - (0, 1) - (0, 2) - (0, 3)
		//   |        |        |
		// (1, 0) - (1, 1) - (1, 2)
		//   |        |        |
		// (2, 0) - (2, 1) - (2, 2)

		Node n00 = new Node();
		Node n01 = new Node();
		Node n02 = new Node();
		Node n03 = new Node();

		Node n10 = new Node();
		Node n11 = new Node();
		Node n12 = new Node();

		Node n20 = new Node();
		Node n21 = new Node();
		Node n22 = new Node();

		n00.setRight(n01);
		n01.setRight(n02);
		n02.setRight(n03);

		n00.setDown(n10);
		n01.setDown(n11);
		n02.setDown(n12);

		n10.setRight(n11);
		n11.setRight(n12);

		n10.setDown(n20);
		n11.setDown(n21);
		n12.setDown(n22);

		n20.setRight(n21);
		n21.setRight(n22);

		assertEquals(3, SquareGrid.analyzeSquareGrid(n00));
	}

	@Test
	public void testIsSquareGrid06() {
		// (0, 0) - (0, 1) - (0, 2)
		//   |        |        |
		// (1, 0) - (1, 1) - (1, 2)
		//   |        |        |
		//   |      (Node)     |
		//   |                 |
		// (2, 0) - (2, 1) - (2, 2)


		Node n00 = new Node();
		Node n01 = new Node();
		Node n02 = new Node();

		Node n10 = new Node();
		Node n11 = new Node();
		Node n12 = new Node();

		Node n20 = new Node();
		Node n21 = new Node();
		Node n22 = new Node();

		n00.setRight(n01);
		n01.setRight(n02);

		n00.setDown(n10);
		n01.setDown(n11);
		n02.setDown(n12);

		n10.setRight(n11);
		n11.setRight(n12);

		n10.setDown(n20);
		n11.setDown(new Node());
		n12.setDown(n22);

		n20.setRight(n21);
		n21.setRight(n22);

		assertEquals(2, SquareGrid.analyzeSquareGrid(n00));
	}

	@Test
	public void testIsSquareGrid07() {
		// (0, 0) - (0, 1) - (0, 2) - (0, 3)
		//   |        |        |        |
		// (1, 0) - (1, 1) - (1, 2) - (1, 3)
		//   |        |        |
		// (2, 0) - (2, 1) - (2, 2) - (2, 3)
		//   |        |        |        |
		// (3, 0) - (3, 1)   (3, 2) - (3, 3)

		// Note that the edges from (3, 1) to (3, 2) and from (1, 3) to (2, 3) are missing.

		Node n00 = new Node();
		Node n01 = new Node();
		Node n02 = new Node();
		Node n03 = new Node();

		Node n10 = new Node();
		Node n11 = new Node();
		Node n12 = new Node();
		Node n13 = new Node();

		Node n20 = new Node();
		Node n21 = new Node();
		Node n22 = new Node();

		Node n30 = new Node();
		Node n31 = new Node();

		Node n23 = new Node();
		Node n32 = new Node();
		Node n33 = new Node();

		n00.setRight(n01);
		n01.setRight(n02);
		n02.setRight(n03);

		n00.setDown(n10);
		n01.setDown(n11);
		n02.setDown(n12);
		n03.setDown(n13);

		n10.setRight(n11);
		n11.setRight(n12);
		n12.setRight(n13);

		n10.setDown(n20);
		n11.setDown(n21);
		n12.setDown(n22);

		n20.setRight(n21);
		n21.setRight(n22);

		n20.setDown(n30);
		n21.setDown(n31);

		n30.setRight(n31);

		n22.setRight(n23);
		n22.setDown(n32);
		n23.setDown(n33);
		n32.setRight(n33);

		assertEquals(3, SquareGrid.analyzeSquareGrid(n00));
	}
}
