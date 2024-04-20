import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class LotteryTest {

	@Test
	public void testBuyTicket() {
		Lottery lottery = new Lottery(42);
		assertEquals(0, lottery.soldTickets());

		int[] numbers = new int[] { 1, 2, 3, 4, 5, 6 };
		Ticket tic = lottery.buyTicket(numbers);
		assertEquals(1, lottery.soldTickets());
		assertNotNull(tic);
		assertEquals(1, tic.getNumber());
		assertEquals(Set.of(1, 2, 3, 4, 5, 6), tic.getNumbers());

		numbers = new int[] { 2, 3, 4, 5, 6, 7 };
		tic = lottery.buyTicket(numbers);
		assertEquals(2, lottery.soldTickets());
		assertNotNull(tic);
		assertEquals(2, tic.getNumber());
		assertEquals(Set.of(2, 3, 4, 5, 6, 7), tic.getNumbers());
	}

	@Test
	public void testIllegalNumbers() {
		Lottery lottery = new Lottery(42);
		assertThrows(IllegalArgumentException.class, () -> {
			lottery.buyTicket(new int[] { 1, 2, 3, 4 });
		});
		assertThrows(IllegalArgumentException.class, () -> {
			lottery.buyTicket(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 });
		});
		assertThrows(IllegalArgumentException.class, () -> {
			lottery.buyTicket(new int[] { -1, 1, 2, 3, 4, 5 });
		});
	}

	@Test
	public void testDraw() {
		// repeat 100 times, because of randomness
		for (int i = 0; i < 100; i++) {
			Lottery lottery = new Lottery(42);
			lottery.draw();
			Set<Integer> winning = lottery.getWinning();
			assertNotNull(winning);
			assertEquals(6, winning.size());
			for (int n : winning) {
				assertTrue(n >= 1 && n <= 42, "n = " + n);
			}
		}
	}

	@Test
	public void testCorrectNumbers() {
		// test with only 10 numbers, so at least 2 must be correct
		for (int i = 0; i < 10; i++) {
			Lottery lottery = new Lottery(10);
			int[] numbers = new int[] { 1, 2, 3, 4, 5, 6 };
			Ticket tic = lottery.buyTicket(numbers);
			lottery.draw();
			Set<Integer> winning = lottery.getWinning();
			Set<Integer> correct = tic.getCorrectNumbers();
			assertNotNull(correct);
			assertTrue(correct.size() >= 2);
			assertTrue(correct.size() <= 6);
			for (int n : correct) {
				assertTrue(winning.contains(n));
				assertTrue(tic.getNumbers().contains(n));
			}
		}
	}

	@Test
	public void testPrize() {
		// test with many numbers, so prize will be low
		for (int i = 0; i < 10; i++) {
			Lottery lottery = new Lottery(100);
			int[] numbers = new int[] { 1, 2, 3, 4, 5, 6 };
			Ticket tic = lottery.buyTicket(numbers);
			lottery.draw();
			Set<Integer> correct = tic.getCorrectNumbers();
			if (correct.size() == 0) {
				assertEquals(0, tic.getPrize());
			} else if (correct.size() == 1) {
				assertEquals(5, tic.getPrize());
			} else if (correct.size() == 2) {
				assertEquals(100, tic.getPrize());
			} // rest is unlikely
		}

		// test with few numbers, so prize will be high
		for (int i = 0; i < 10; i++) {
			Lottery lottery = new Lottery(20);
			int[] numbers = new int[] { 1, 2, 3, 4, 5, 6 };
			Ticket tic = lottery.buyTicket(numbers);
			lottery.draw();
			Set<Integer> correct = tic.getCorrectNumbers();
			if (correct.size() == 3) {
				assertEquals(2000, tic.getPrize());
			} else if (correct.size() == 4) {
				assertEquals(40000, tic.getPrize());
			} else if (correct.size() == 5) {
				assertEquals(800000, tic.getPrize());
			} else if (correct.size() == 6) {
				assertEquals(16000000, tic.getPrize());
			}
		}
	}

	@Test
	public void testIllegalState() {
		Lottery lottery1 = new Lottery(42);
		assertThrows(IllegalStateException.class, () -> {
			// not drawn yet
			lottery1.getWinning();
		});

		Lottery lottery2 = new Lottery(42);
		lottery2.draw();
		assertThrows(IllegalStateException.class, () -> {
			// already drawn
			lottery2.buyTicket(new int[] { 1, 2, 3, 4, 5, 6 });
		});

		Lottery lottery3 = new Lottery(42);
		lottery3.draw();
		assertThrows(IllegalStateException.class, () -> {
			// already drawn
			lottery3.draw();
		});

		Lottery lottery4 = new Lottery(42);
		Ticket tic4 = lottery4.buyTicket(new int[] { 1, 2, 3, 4, 5, 6 });
		assertThrows(IllegalStateException.class, () -> {
			// not drawn yet
			tic4.getCorrectNumbers();
		});

		Lottery lottery5 = new Lottery(42);
		Ticket tic5 = lottery5.buyTicket(new int[] { 1, 2, 3, 4, 5, 6 });
		assertThrows(IllegalStateException.class, () -> {
			// not drawn yet
			tic5.getPrize();
		});
	}
}
