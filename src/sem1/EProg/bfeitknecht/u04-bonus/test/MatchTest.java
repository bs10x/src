import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MatchTest {

	@Test
	public void testMatchNumber() {
		assertEquals(1, Match.matchNumber(32857890, 789));
		assertEquals(1, Match.matchNumber(37897890, 789));
		assertEquals(2, Match.matchNumber(1800765, 7));
		assertEquals(-1,Match.matchNumber(1800765, 8));
		assertEquals(1, Match.matchNumber(75, 7));
	}
}
