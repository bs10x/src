import static org.junit.Assert.*;
import org.junit.Test;

public class MedianTest {

	@Test
	public void testMedianExamples() {
		
		assertEquals(3.0, Median.median(new int[] {1, 5, 4, 3, 0}), 0.0001);
		assertEquals(0.0, Median.median(new int[] {1000, -100, 0}), 0.0001);
		assertEquals(4.5, Median.median(new int[] {4, 17, 5, 1}), 0.0001);
	}
}
