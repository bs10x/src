import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;
public class NestedCyclesTest {
	
	public static Node nestedCyclesExample() {
		//N1
		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);
		Node n13 = new Node(null, null);
		
		n11.setOuter(n12);
		n12.setOuter(n13);
		n13.setOuter(n11);
		
		//N2
		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);
		Node n23 = new Node(null, null);
		Node n24 = new Node(null, null);
		
		n21.setOuter(n22);
		n22.setOuter(n23);
		n23.setOuter(n24);
		n24.setOuter(n21);
		
		n11.setInner(n22);
		n12.setInner(n21);
		n13.setInner(n24);
		
		//N3
		Node n31 = new Node(null, null);
		Node n32 = new Node(null, null);
		Node n33 = new Node(null, null);
		Node n34 = new Node(null, null);
		Node n35 = new Node(null, null);
		
		n31.setOuter(n32);
		n32.setOuter(n33);
		n33.setOuter(n34);
		n34.setOuter(n35);
		n35.setOuter(n31);
		
		n21.setInner(n31);
		n22.setInner(n31);
		n23.setInner(n34);
		n24.setInner(n34);
		
		return n11;
	}
	
	/** cycleDistance **/
	
	@Test
	public void testCycleDistance1() {
		Node n11 = new Node(null,null);		
		Node n12 = new Node(null,null);				
		Node n13 = new Node(null,null);	
		Node n14 = new Node(null,null);
		Node n15 = new Node(null,null);
		
		n11.setOuter(n12);
		n12.setOuter(n13);
		n13.setOuter(n14);
		n14.setOuter(n15);
		n15.setOuter(n11);		
		
		Map<Node, Integer> result = NestedCycles.cycleDistance(n13);
		assertNotNull(result);
		assertEquals(5, result.size());
		
		assertEquals(3, result.get(n11));
		assertEquals(4, result.get(n12));
		assertEquals(0, result.get(n13));
		assertEquals(1, result.get(n14));
		assertEquals(2, result.get(n15));		
	}
	
	@Test
	public void testCycleDistance2() {
		Node n11 = nestedCyclesExample();
		Node n12 = n11.getOuter();
		Node n13 = n12.getOuter();
		
		Map<Node, Integer> result = NestedCycles.cycleDistance(n12);
		assertNotNull(result);
		assertEquals(3, result.size());

		assertEquals(2, result.get(n11));
		assertEquals(0, result.get(n12));
		assertEquals(1, result.get(n13));				
	}

	/** isNestedCycle **/
	@Test
	public void testIsNestedCycle1() {
		Node n = nestedCyclesExample();
		
		assertTrue(NestedCycles.isNestedCycle(n));
	}
	
	@Test
	public void testIsNestedCycle2() {	
		Node n = nestedCyclesExample();
		
		assertTrue(NestedCycles.isNestedCycle(n.getOuter().getInner()));
	}
	
	@Test
	public void testIsNestedCycle3() {
		Node n1 = new Node(null, null);
		Node n2 = new Node(null, null);
		n1.setOuter(n2);
		
		assertFalse(NestedCycles.isNestedCycle(n1));
		
		n2.setOuter(n1);
		
		assertTrue(NestedCycles.isNestedCycle(n1));
	}
	
	@Test
	public void testIsNestedCycle4() {
		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);
		n11.setOuter(n12);
		n12.setOuter(n11);
		
		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);
		n21.setOuter(n22);
		n22.setOuter(n21);
		
		n11.setInner(n21);
		
		assertFalse(NestedCycles.isNestedCycle(n11));
		
		n12.setInner(n21);
		assertTrue(NestedCycles.isNestedCycle(n11));		
	}
	
	@Test
	public void testIsNestedCycle5() {
		//N1
		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);
		n11.setOuter(n12);
		n12.setOuter(n11);
		
		//N2
		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);
		n21.setOuter(n22);
		n22.setOuter(n21);

		n11.setInner(n21);
		n12.setInner(n22);

		//N3
		Node n31 = new Node(null, null);
		Node n32 = new Node(null, null);
		n31.setOuter(n32);
		n32.setOuter(n31);
		
		n21.setInner(n31);
		n22.setInner(n32);
		
		//N4
		Node n41 = new Node(null, null);
		Node n42 = new Node(null, null);
		n41.setOuter(n42);
		n42.setOuter(n41);

		n31.setInner(n41);
		n32.setInner(n42);
		
		//N5
		Node n51 = new Node(null, null);
		Node n52 = new Node(null, null);
		n51.setOuter(n52);
		n52.setOuter(n51);

		n41.setInner(n51);
		n42.setInner(n52);
		
		//N6
		Node n61 = new Node(null, null);
		Node n62 = new Node(null, null);
		n61.setOuter(n62);
		n62.setOuter(n61);
		
		n51.setInner(n61);
		n52.setInner(n62);

		//N7
		Node n71 = new Node(null, null);
		Node n72 = new Node(null, null);
		n71.setOuter(n72);
		n72.setOuter(n71);
		
		n61.setInner(n71);
		n62.setInner(n72);

		//N8
		Node n81 = new Node(null, null);
		Node n82 = new Node(null, null);
		n81.setOuter(n82);
		n82.setOuter(n81);

		n71.setInner(n81);
		n72.setInner(n82);
		
		assertTrue(NestedCycles.isNestedCycle(n11));
	}
}