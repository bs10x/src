import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EnthaltenMitAbstandTest {

	@Test
	public void testVomAufgabenBlatt() {
		assertEquals(true, EnthaltenMitAbstand.enthalten("cbbbbba", "acb", 2));
		assertEquals(false, EnthaltenMitAbstand.enthalten("cbbbbba", "acb", 1));
	}
	
	@Test
	public void einfacheTests() {
		assertEquals(true, EnthaltenMitAbstand.enthalten("abc", "abc", 0));
		assertEquals(false, EnthaltenMitAbstand.enthalten("abc", "abcc", 0));
	}

	@Test
	public void weitereTests() {
		assertEquals(true, EnthaltenMitAbstand.enthalten("abc", "abc", 2));
		assertEquals(false, EnthaltenMitAbstand.enthalten("abc", "abcc", 2));
		assertEquals(true, EnthaltenMitAbstand.enthalten("abc", "abc", 30));
		assertEquals(false, EnthaltenMitAbstand.enthalten("abc", "abcc", 30));
	}
}


