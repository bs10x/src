import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ContactTracerTest {
	
	/** Dieser Test Case demonstriert, wie bei anonymen Begegnungen IDs getauscht werden sollen. */
	@Test
	public void testAnonymousEncounters() {
		ContactTracer tracer = new ContactTracer();
		
		Person alice = tracer.createPerson(20);
		Person bob = tracer.createPerson(30);
		
		// Alice und Bob haben eine Begegnung	
		tracer.registerEncounter(alice, bob);	
		
		// Da es nur eine Begegnung gab, gelten die folgenden Eigenschaften
		
		// Bob hat 1 ID genutzt
		assertEquals(bob.getUsedIds().size(), 1);
		// Bob hat 1 ID gesehen
		assertEquals(bob.getSeenIds().size(), 1);
		// Alice hat 1 ID genutzt
		assertEquals(alice.getUsedIds().size(), 1);
		// Alice hat 1 ID gesehen
		assertEquals(alice.getSeenIds().size(), 1);
		
		// Alle genutzten IDs sind einzigartig (frisch)
		assertDisjointLists(List.of(alice.getUsedIds(), bob.getUsedIds()));
		
		assertSameIds(alice.getSeenIds(), bob.getUsedIds(), "Alice hat nur Bobs ID gesehen");
		assertSameIds(bob.getSeenIds(), alice.getUsedIds(), "Bob hat nur Alices ID gesehen");
	}
	
	/** Dieser Test Case demonstriert die Benachrichtigung von jüngeren Personen. */
	@Test
	public void testYoungsters() {
		ContactTracer tracer = new ContactTracer();
		
		Person alice = tracer.createPerson(20);
		Person bob = tracer.createPerson(30);
		Person margret = tracer.createPerson(30);
		
		tracer.registerEncounter(alice, bob);
		
		// Alice wird positiv getestet 
		alice.setTestsPositively();

		// Alice ist positiv, hat daher keine Benachrichtigung
		assertNoNotification(alice);
		// Bob ist ein direkter Kontakt -> High-Risk Benachrichtigung
		assertHasHighRisk(bob);

		assertNoNotification(margret);
	}
	
	/** Dieser Test Case demonstriert die Low-Risk Benachrichtigung von älteren Personen. */
	@Test
	public void testElderlyPerson() {
		ContactTracer tracer = new ContactTracer();
		
		Person alice = tracer.createPerson(20);
		Person bob = tracer.createPerson(30);
		Person margret = tracer.createPerson(80);
				
		tracer.registerEncounter(alice, bob);
		tracer.registerEncounter(bob, margret);
		
		// Alice wird positiv getestet 
		alice.setTestsPositively();

		// Alice ist positiv, hat daher keine Benachrichtigung
		assertNoNotification(alice);
		// Bob ist ein direkter Kontakt -> High-Risk Benachrichtigung
		assertHasHighRisk(bob);
		// Margret ist ein indirekter Kontakt und > 60 Jahre alt -> Low-Risk Benachrichtigung
		assertHasLowRisk(margret);	
	}
	
	/** Dieser Test Case demonstriert wie mehrere positive Tests markiert werden. */
	@Test
	public void testMultiplePositiveTests() {
		ContactTracer tracer = new ContactTracer();
		
		Person alice = tracer.createPerson(20);
		Person bob = tracer.createPerson(30);
		Person margret = tracer.createPerson(80);
				
		tracer.registerEncounter(alice, bob);
		tracer.registerEncounter(bob, margret);
		
		// Zunächst testet Alice positiv
		alice.setTestsPositively();

		
		// Alice ist positiv, hat daher keine Benachrichtigung
		assertNoNotification(alice);
		// Bob ist ein direkter Kontakt -> High-Risk Benachrichtigung
		assertHasHighRisk(bob);
		// Margret ist ein indirekter Kontakt und > 60 Jahre alt -> Low-Risk Benachrichtigung
		assertHasLowRisk(margret);	
		
		
		// Jetzt testet auch Bob positiv
		bob.setTestsPositively();
		
		
		// Alice gilt weiterhin als positiv getestet -> Keine Benachrichtigung
		assertNoNotification(alice);
		// Da Bob nun positiv getestet wurde, soll er keine Benachrichtigung mehr erhalten		
		assertNoNotification(bob);
		// Margret ist nun ein direkter Kontakt	-> High-Risk Benachrichtigung
		assertHasHighRisk(margret);
	}
	
	// Test Utilities	
	
	/** Stellt sicher, dass alle IDs in l1 auch in l2 vorkommen und umgekehrt. */
	public static void assertSameIds(List<Integer> l1, List<Integer> l2) {
		
		assertSameIds(l1, l2, "");
	}
	
	/** Prüft, dass alle IDs in l1 auch in l2 vorkommen und umgekehrt. */
	public static void assertSameIds(List<Integer> l1, List<Integer> l2, String message) {
		HashSet<Integer> s1 = new HashSet<Integer>(l1);
		HashSet<Integer> s2 = new HashSet<Integer>(l2);
		
		assertEquals(s1, s2, "Die Liste der IDs " + l1 + " stimmt nicht ueberein mit der Liste der IDs " + l2);
	}
	
	/** Prüft, dass alle IDs die in den gegebenen Listen vorkommen, einzigartig über alle gegeben Listen hinweg sind. */  
	public static void assertDisjointLists(List<List<Integer>> lists) {
		assertDisjointLists(lists, "");
	}
	
	/** Prüft, dass alle IDs die in den gegebenen Listen vorkommen, einzigartig über alle gegeben Listen hinweg sind. */
	public static void assertDisjointLists(List<List<Integer>> lists, String message) {
		HashSet<Integer> s = new HashSet<Integer>();
		
		message = message + "Die folgenden Listen sind nicht disjunkt:\n" + getStringRepresentation(lists);
	
		for (List<Integer> l : lists) {
			int expectedIncreaseInSize = l.size();
			int sizeBefore = s.size();
			s.addAll(l);
            assertEquals(sizeBefore + expectedIncreaseInSize, s.size(), message); 
		}
	}
	
	public static String getStringRepresentation(List<List<Integer>> lists) {
		String result = "";
		for(List<Integer> list : lists) {
			result = result + "\n" + list.toString();
		}
		
		return result;
	}
	
	/** Prüft ob die gegebene Person die Benachrichtigungswarnstufe NoNotification hat. */
	public static void assertNoNotification(Person p) { 
		assertEquals(Person.NotificationType.NoNotification, p.getNotification(), "Es wurde fuer die Person keine Warnstufe erwartet, aber die Warnstufe ist " + p.getNotification());
	}
	
	/** Prüft ob die gegebene Person die Benachrichtigungswarnstufe HighRiskNotification hat. */
	public static void assertHasHighRisk(Person p) {
		assertEquals(Person.NotificationType.HighRiskNotification, p.getNotification(), "Es wurde fuer die Person eine High-Risk Warnstufe erwartet, aber die Warnstufe ist " + p.getNotification());
	}
	
	/** Prüft ob die gegebene Person die Benachrichtigungswarnstufe LowRiskNotification hat. */
	public static void assertHasLowRisk(Person p) {
		assertEquals(Person.NotificationType.LowRiskNotification, p.getNotification(), "Es wurde fuer die Person eine Low-Risk Warnstufe erwartet, aber die Warnstufe ist " + p.getNotification());
	}
}
