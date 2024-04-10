import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NamesTest {

	@Test
	public void testsFromTaskDescription() {
		testEntry("mor", "Mutter", "w");
		testEntry("far", "Vater", "m");
		testEntry("farmor", "Grossmutter", "wm");
		testEntry("morfar", "Grossvater", "mw");
		testEntry("farfar", "Grossvater", "mm");
		testEntry("mormormor", "Urgrossmutter", "www");
		testEntry("morfarmor", "Urgrossmutter", "wmw");
		testEntry("mormormormor", "Ururgrossmutter", "wwww");
	}
	
	public static void testEntry(String name, String expectedGermanName, String expectedSummary) {
		String[] result = Names.toGerman(name);
		assertEquals(2, result.length);
		assertEquals(expectedGermanName, result[0]);
		assertEquals(expectedSummary, result[1]);
	}
}
