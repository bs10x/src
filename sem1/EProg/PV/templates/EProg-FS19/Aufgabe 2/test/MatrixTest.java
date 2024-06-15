import static org.junit.Assert.*;

import org.junit.Test;

public class MatrixTest {

	final char[][] abcMatrix = {
			{ 'a', 'b', 'c', },
			{ 'd', 'e', 'f', },
			{ 'g', 'h', 'i', },
			{ 'j', 'k', 'l', },
	};

	@Test
	public void testGiven() {
		Position[] pos = Matrix.findWord(abcMatrix, "ab");
		assertArrayEquals(positions(0, 0, 0, 1), pos);

		pos = Matrix.findWord(abcMatrix, "ad");
		assertArrayEquals(positions(0, 0, 1, 0), pos);

		pos = Matrix.findWord(abcMatrix, "ae");
		assertArrayEquals(positions(0, 0, 1, 1), pos);
	}
	
	private Position[] positions(int... coords) {
		assert coords.length % 2 == 0;
		Position[] pos = new Position[coords.length / 2];
		for (int i = 0; i < coords.length; i += 2) {
			pos[i / 2] = new Position(coords[i], coords[i + 1]);
		}
		return pos;
	}
}
