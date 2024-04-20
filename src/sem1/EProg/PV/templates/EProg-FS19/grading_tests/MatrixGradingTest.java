import static org.junit.Assert.*;

import org.junit.Test;

public class MatrixGradingTest {

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

	@Test
	public void testExample() {
		char[][] matrix = {
				{ 'g', 'e', 'e', 'a', 'p', 'r', 'o', 'g', 'o' },
				{ 'o', 'p', 'b', 'p', 'e', 'p', 'r', 'o', 'g' },
				{ 'r', 'r', 'c', 'c', 'r', 'c', 'c', 'c', 'e' },
				{ 'p', 'o', 'd', 'd', 'd', 'o', 'd', 'd', 'p' },
				{ 'e', 'g', 'e', 'e', 'e', 'e', 'g', 'e', 'r' } };
		String word = "eprog";
		Position[] pos = Matrix.findWord(matrix, word);
		assertNotNull(pos);
		verifyPositions(matrix, word, pos);
	}

	@Test
	public void testTrivialImplementation() {
		char[][] matrix = {
				{ 'a', 'a', 'a', },
				{ 'a', 'a', 'a', },
				{ 'a', 'a', 'a', },
		};
		assertNotNull(Matrix.findWord(matrix, "a"));
	}

	@Test
	public void testSingleValue() {
		char[][] matrix = {
				{ 'a', },
		};
		Position[] pos = Matrix.findWord(matrix, "a");
		assertArrayEquals(positions(0, 0), pos);
	}

	@Test
	public void testEmptyWord() {
		testTrivialImplementation();
		assertNull(Matrix.findWord(abcMatrix, ""));
	}

	@Test
	public void testEmptyMatrix() {
		testTrivialImplementation();
		assertNull(Matrix.findWord(new char[0][0], "x"));
	}

	@Test
	public void testWordNotFound() {
		testTrivialImplementation();
		assertNull(Matrix.findWord(abcMatrix, "x"));
	}

	@Test
	public void testOnlyFoundNotFoundH() {
		assertNotNull(Matrix.findWord(abcMatrix, "ab"));
		assertNotNull(Matrix.findWord(abcMatrix, "ef"));
		assertNull(Matrix.findWord(abcMatrix, "xx"));
	}

	@Test
	public void testOnlyFoundNotFoundV() {
		assertNotNull(Matrix.findWord(abcMatrix, "ad"));
		assertNotNull(Matrix.findWord(abcMatrix, "eh"));
		assertNull(Matrix.findWord(abcMatrix, "xx"));
	}

	@Test
	public void testOnlyFoundNotFoundD() {
		assertNotNull(Matrix.findWord(abcMatrix, "ae"));
		assertNotNull(Matrix.findWord(abcMatrix, "hl"));
		assertNull(Matrix.findWord(abcMatrix, "xx"));
	}

	@Test
	public void testWordExceedsBounds() {
		testTrivialImplementation();
		assertNull(Matrix.findWord(abcMatrix, "efx"));
		assertNull(Matrix.findWord(abcMatrix, "hkx"));
		assertNull(Matrix.findWord(abcMatrix, "hlx"));
	}

	@Test
	public void testWordPartiallyExists() {
		testTrivialImplementation();
		assertNull(Matrix.findWord(abcMatrix, "dex"));
		assertNull(Matrix.findWord(abcMatrix, "bex"));
		assertNull(Matrix.findWord(abcMatrix, "dhx"));
	}

	@Test
	public void testDontLookForOtherMatches() {
		testTrivialImplementation();
		assertNull(Matrix.findWord(abcMatrix, "cb"));
		assertNull(Matrix.findWord(abcMatrix, "bca"));

		assertNull(Matrix.findWord(abcMatrix, "eb"));
		assertNull(Matrix.findWord(abcMatrix, "hkb"));

		assertNull(Matrix.findWord(abcMatrix, "ie"));
		assertNull(Matrix.findWord(abcMatrix, "eia"));

		assertNull(Matrix.findWord(abcMatrix, "ge"));
		assertNull(Matrix.findWord(abcMatrix, "eg"));
		assertNull(Matrix.findWord(abcMatrix, "ecg"));
	}

	@Test
	public void testExampleH() {
		char[][] matrix = {
				{ 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
				{ 'b', 'b', 'b', 'b', 'e', 'p', 'r', 'o', 'g' },
				{ 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c' },
				{ 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd' },
				{ 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e' } };
		Position[] pos = Matrix.findWord(matrix, "eprog");
		assertArrayEquals(positions(1, 4, 1, 5, 1, 6, 1, 7, 1, 8), pos);
	}

	@Test
	public void testExampleV() {
		char[][] matrix = {
				{ 'a', 'e', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
				{ 'b', 'p', 'b', 'b', 'b', 'b', 'b', 'b', 'b' },
				{ 'c', 'r', 'c', 'c', 'c', 'c', 'c', 'c', 'c' },
				{ 'd', 'o', 'd', 'd', 'd', 'd', 'd', 'd', 'd' },
				{ 'e', 'g', 'e', 'e', 'e', 'e', 'e', 'e', 'e' } };
		Position[] pos = Matrix.findWord(matrix, "eprog");
		assertArrayEquals(positions(0, 1, 1, 1, 2, 1, 3, 1, 4, 1), pos);
	}

	@Test
	public void testExampleD() {
		char[][] matrix = {
				{ 'a', 'a', 'e', 'a', 'a', 'a', 'a', 'a', 'a' },
				{ 'b', 'b', 'b', 'p', 'b', 'b', 'b', 'b', 'b' },
				{ 'c', 'c', 'c', 'c', 'r', 'c', 'c', 'c', 'c' },
				{ 'd', 'd', 'd', 'd', 'd', 'o', 'd', 'd', 'd' },
				{ 'e', 'e', 'e', 'e', 'e', 'e', 'g', 'e', 'e' } };
		Position[] pos = Matrix.findWord(matrix, "eprog");
		assertArrayEquals(positions(0, 2, 1, 3, 2, 4, 3, 5, 4, 6), pos);
	}

	private Position[] positions(int... coords) {
		assert coords.length % 2 == 0;
		Position[] pos = new Position[coords.length / 2];
		for (int i = 0; i < coords.length; i += 2) {
			pos[i / 2] = new Position(coords[i], coords[i + 1]);
		}
		return pos;
	}

	private void verifyPositions(char[][] matrix, String word, Position[] pos) {
		if (matrix.length == 0 || pos == null) {
			assertNull(pos);
			return;
		}

		assertEquals(word.length(), pos.length);
		int index = 0;
		for (Position position : pos) {
			assertTrue(position.i < matrix.length && position.i >= 0
					&& position.j < matrix[0].length && position.j >= 0);
			assertEquals(matrix[position.i][position.j], word.charAt(index));
			index++;
		}
	}
}
