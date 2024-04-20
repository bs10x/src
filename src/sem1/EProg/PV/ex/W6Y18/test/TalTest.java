import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TalTest {

	@Test
	public void testSome() {
		int[] hs = new int[] {0, 17, 17, 8, 8, 11, 4, 8, 0};
		assertEquals(4, Tal.findeGroessteVertiefung(hs));
		
		// TODO: mehr Tests schreiben!
	}
	
}
