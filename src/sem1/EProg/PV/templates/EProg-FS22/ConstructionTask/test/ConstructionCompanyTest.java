import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ConstructionCompanyTest {
	
	@Test
	public void taskA_simple1() {
		CCompany company = new CCompany();
		CSite site = company.createCSite(Set.of(1), 1);
		
		Resource r1 = new IdResource(1);
		Resource r2 = new IdResource(2);
		
		assertEquals(Set.of(), site.resources());
		
		assertEquals(true, site.canAdd(r1));
		
		site.add(r1);
		
		assertEquals(Set.of(r1), site.resources());
		
		site.use(r1);
		
		assertEquals(Set.of(), site.resources());
		
		assertEquals(false, site.canAdd(r2));
	}
	
	@Test
	public void taskA_simple2() {
		CCompany company = new CCompany();
		CSite site = company.createCSite(Set.of(1,2), 3);
		
		Resource r1 = new IdResource(1);
		Resource r2 = new IdResource(1);
		Resource r3 = new IdResource(1);
		Resource r4 = new IdResource(1);
		Resource r5 = new IdResource(2);
		Resource r6 = new IdResource(3);
		
		assertEquals(Set.of(), site.resources());
		
		site.add(r1);
		site.add(r2);
		site.add(r3);
		
		assertEquals(Set.of(r1,r2,r3), site.resources());
		
		assertEquals(false, site.canAdd(r4));
		
		assertEquals(true, site.canAdd(r5));
		
		site.add(r5);
		
		assertEquals(Set.of(r1,r2,r3,r5), site.resources());
		
		assertEquals(false, site.canAdd(r6));
	}
	
	@Test
	public void taskB_simple1() {
		CCompany company = new CCompany();
		CSite site = company.createCSite(1);
		
		Resource r1 = new IdResource(1);
		Resource r2 = new IdResource(2);
		Resource r3 = new IdResource(1);
		Resource r4 = new IdResource(1);
		Resource r5 = new IdResource(1);
		
		company.add(r1);
		company.add(r2);
		company.add(r3);
		company.add(r4);
		company.add(r5);
		
		assertEquals(Set.of(r1, r2, r3, r4, r5), company.resources());
		
		assertEquals(Set.of(), site.resources());
		
		company.nextDay();
		
		assertEquals(Set.of(r1, r3), site.resources());
		
		site.use(r1);
		
		assertEquals(Set.of(r3), site.resources());
		
		company.nextDay();
		
		assertEquals(Set.of(r3, r4), site.resources());
		
		site.use(r4);
		
		site.close();
		
		company.nextDay();
		
		assertEquals(Set.of(r2, r5), company.resources());
	}
	
	@Test
	public void taskB_simple2() {
		CCompany company = new CCompany();
		CSite site1 = company.createCSite(1);
		CSite site2 = company.createCSite(1);
		CSite site3 = company.createCSite(2);
		
		Resource r1 = new IdResource(1);
		Resource r2 = new IdResource(2);
		Resource r3 = new IdResource(1);
		Resource r4 = new IdResource(1);
		
		company.add(r1);
		company.add(r2);
		company.add(r3);
		company.add(r4);
		
		company.nextDay();
		
		assertEquals(Set.of(r1, r3), site1.resources());
		assertEquals(Set.of(r4), site2.resources());
		assertEquals(Set.of(r2), site3.resources());
		
		CSite site4 = company.createCSite(1);
		
		Resource r5 = new IdResource(1);
		
		company.add(r5);
		
		site2.close();
		
		company.nextDay();
		
		assertEquals(Set.of(r5), site4.resources());
	}
	
	@Test
	public void taskC_simple1() {
		CCompany company = new CCompany();
		CSite site = company.createCSite(Set.of(1), 2, 1);
		
		Resource r1 = new IdResource(1);
		Resource r2 = new IdResource(1);
		Resource r3 = new IdResource(1);
		
		company.add(r1);
		company.add(r2);
		company.add(r3);
		
		company.nextDay();
		
		assertEquals(Set.of(r1, r2), site.resources());
		
		// r1 oder r2 werden an company uebergeben und dann wird r3 an site uebergeben.
		company.nextDay();
		
		assertEquals(2, site.resources().size());
		assertTrue(site.resources().contains(r3));
	}
	
	@Test
	public void taskC_simple2() {
		CCompany company = new CCompany();
		CSite site = company.createCSite(Set.of(1,2,3), 2, 1);
		
		Resource r1 = new IdResource(1);
		Resource r2 = new IdResource(1);
		Resource r3 = new IdResource(1);
		Resource r4 = new IdResource(2);
		Resource r5 = new IdResource(2);
		Resource r6 = new IdResource(2);
		Resource r7 = new IdResource(3);
		
		company.add(r1);
		company.add(r2);
		company.add(r3);
		company.add(r4);
		company.add(r5);
		company.add(r6);
		company.add(r7);
		
		company.nextDay();
		
		assertEquals(Set.of(r1, r2, r4, r5, r7), site.resources());
		
		// r1 oder r2 und r4 oder r5 werden an company uebergeben und dann wird r3 and r6 an site uebergeben.
		company.nextDay();
		
		assertEquals(5, site.resources().size());
		assertTrue(site.resources().contains(r3));
		assertTrue(site.resources().contains(r6));
		assertTrue(site.resources().contains(r7));
	}
	
	@Test 
	public void testNormal1() {
		CCompany company = new CCompany();
		CSite s1 = company.createCSite(Set.of(1), 1);
		CSite s2 = company.createCSite(Set.of(1), 1);
		
		Resource r1 = new IdResource(1);
		Resource r2 = new IdResource(1);
		Resource r3 = new IdResource(1);
		
		company.add(r1); 
		
		assertEquals(Set.of(), s1.resources());
		assertEquals(Set.of(), s2.resources());
		
		company.nextDay();
		
		assertEquals(Set.of(r1), s1.resources());
		assertEquals(Set.of(), s2.resources());
		
		company.add(r2); 
		company.add(r3); 
		
		assertEquals(Set.of(r1), s1.resources());
		assertEquals(Set.of(), s2.resources());
		
		company.nextDay();
		
		assertEquals(Set.of(r1), s1.resources());
		assertEquals(Set.of(r2), s2.resources());
	}
}

class IdResource implements Resource {
	int typeId;
	IdResource(int typeId) {
		this.typeId = typeId;
	} 
	
	@Override
	public int type() {
		return typeId;
	}
}
