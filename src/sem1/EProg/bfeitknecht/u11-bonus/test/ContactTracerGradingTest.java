import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ContactTracerGradingTest {

    /**
     * <b>Anonyme IDs</b> {@code ANON} Wir prüfen nur die Mengen an ausgetauschen IDs nach Begegnungen via Person.getUsedIds(),
     * Person.getSeenIds() und ContactTracer.registerEncounter(Person, Person). Hierfür muss das tat-
     * sächliche Contact Tracing nicht implementiert sein. Bei anderen Tests prüfen wir die IDs nicht mehr
     * und nehmen an, dass dieser Aspekt korrekt implementiert ist.
     */

    @Test
    public void test0ANON01NoEncounters() {
        ContactTracer tracer = new ContactTracer();

        Person alice = tracer.createPerson(20);
        Person bob = tracer.createPerson(30);

        assertTrue(alice.getSeenIds().isEmpty());
        assertTrue(bob.getSeenIds().isEmpty());
    }

    @Test
    public void test0ANON02UniqueIDs() {
        ContactTracer tracer = new ContactTracer();

        Person alice = tracer.createPerson(20);
        Person bob = tracer.createPerson(30);
        Person margret = tracer.createPerson(30);

        // Alice und Bob haben eine Begegnung
        tracer.registerEncounter(alice, bob);
        // Bob und Margret haben eine Begegnung
        tracer.registerEncounter(bob, margret);

        // Bob hat 2 IDs genutzt
        assertNumIdsEquals(2, bob.getUsedIds());
        // Bob hat 2 ID gesehen
        assertNumIdsEquals(2, bob.getSeenIds());
        // Alice hat 1 ID genutzt
        assertNumIdsEquals(1, alice.getUsedIds());
        // Alice hat 1 ID gesehen
        assertNumIdsEquals(1, alice.getSeenIds());
        // Margret hat 1 ID genutzt
        assertNumIdsEquals(1, margret.getUsedIds());
        // Margret hat 1 ID gesehen
        assertNumIdsEquals(1, margret.getSeenIds());

        // Alle genutzten IDs sind einzigartig (frisch)
        assertDisjointLists(List.of(alice.getUsedIds(), bob.getUsedIds(), margret.getUsedIds()));
        assertDisjointLists(List.of(alice.getSeenIds(), bob.getSeenIds(), margret.getSeenIds()));

        // Bobs IDs wurden von Alice und Margret gesehen
        assertTrue(bob.getUsedIds().contains(alice.getSeenIds().get(0)));
        assertTrue(bob.getUsedIds().contains(margret.getSeenIds().get(0)));
    }

    @Test
    public void test0ANON03SeenVsUsedIds() {
        ContactTracer tracer = new ContactTracer();

        Person alice = tracer.createPerson(20);
        Person bob = tracer.createPerson(30);
        Person margret = tracer.createPerson(30);

        // Alice und Bob haben eine Begegnung
        tracer.registerEncounter(alice, bob);
        // Bob und Margret haben eine Begegnung
        tracer.registerEncounter(bob, margret);

        // Bobs IDs wurden von Alice und Margret gesehen
        assertTrue(bob.getUsedIds().contains(alice.getSeenIds().get(0)));
        assertTrue(bob.getUsedIds().contains(margret.getSeenIds().get(0)));

        // Margret und Alice sind sich nicht begegnet
        assertDisjointLists(List.of(alice.getSeenIds(), margret.getUsedIds()));
        assertDisjointLists(List.of(margret.getSeenIds(), alice.getUsedIds()));
    }

    @Test
    public void test0ANON04AnonymousEncountersSimpleElderly() {
        ContactTracer tracer = new ContactTracer();

        Person alice = tracer.createPerson(70);
        Person bob = tracer.createPerson(80);

        tracer.registerEncounter(alice, bob);

        assertIntersection(alice.getUsedIds(), bob.getSeenIds(), true);
        assertIntersection(bob.getUsedIds(), alice.getSeenIds(), true);
    }

    @Test
    public void test0ANON05AnonymousEncountersCompleteGraph() {
        Person[] persons = createCompleteGraph(5);

        for (int i = 0; i < 4; ++i) {
            for (int j = i + 1; j < 5; ++j) {
                assertIntersection(persons[i].getUsedIds(), persons[j].getSeenIds(), true);
                assertIntersection(persons[j].getUsedIds(), persons[i].getSeenIds(), true);
            }
        }
    }

    /** Dieser Test Case demonstriert, wie bei anonymen Begegnungen IDs getauscht werden sollen. */
    @Test
    public void test0ANON06_given_testAnonymousEncounters() {
        ContactTracer tracer = new ContactTracer();

        Person alice = tracer.createPerson(20);
        Person bob = tracer.createPerson(30);

        // Alice und Bob haben eine Begegnung
        tracer.registerEncounter(alice, bob);

        // Da es nur eine Begegnung gab, gelten die folgenden Eigenschaften

        // Bob hat 1 ID genutzt
        assertEquals(1, bob.getUsedIds().size());
        // Bob hat 1 ID gesehen
        assertEquals(1, bob.getSeenIds().size());
        // Alice hat 1 ID genutzt
        assertEquals(1, alice.getUsedIds().size());
        // Alice hat 1 ID gesehen
        assertEquals(1, alice.getSeenIds().size());

        // Alle genutzten IDs sind einzigartig (frisch)
        assertDisjointLists(List.of(alice.getUsedIds(), bob.getUsedIds()));

        assertSameIds(alice.getSeenIds(), bob.getUsedIds(), "Alice hat nur Bobs ID gesehen");
        assertSameIds(bob.getSeenIds(), alice.getUsedIds(), "Bob hat nur Alices ID gesehen");
    }

    /**
     * <b>Tracing ohne Alter (1 positiver Test)</b> {@code NOAGE} Hier prüfen wir funktionierendes Contact Tracing mit unter-
     * schiedlich grossen Netzwerken aus Begegnungen, aber ausschliesslich mit Personen ≤ 60 Jahren. Wir
     * prüfen ausserdem nicht den Benachrichtigungsstatus von Personen, die selbst positiv getestet wurden.
     * Pro Test Case soll höchstens eine Person positiv getestet werden.
     */

    @Test
    public void test1NOAGE01_given_testYoungsters() {
        ContactTracer tracer = new ContactTracer();

        Person alice = tracer.createPerson(20);
        Person bob = tracer.createPerson(30);
        Person margret = tracer.createPerson(30);

        tracer.registerEncounter(alice, bob);

        // Alice wird positiv getestet
        alice.setTestsPositively();

        // Alice is positiv, hat daher keine Benachrichtigung
        assertNoNotification(alice);
        // Bob ist ein direkter Kontakt -> High-Risk Benachrichtigung
        assertHasHighRisk(bob);
        // Margret ist ein indirekter Kontakt und < 60 Jahre alt -> Keine Benachrichtigung
        assertNoNotification(margret);
    }

    @Test
    public void test1NOAGE02Triangle() {
        ContactTracer tracer = new ContactTracer();

        Person alice = tracer.createPerson(20);
        Person bob = tracer.createPerson(30);
        Person margret = tracer.createPerson(30);

        tracer.registerEncounter(alice, bob);
        tracer.registerEncounter(bob, margret);
        tracer.registerEncounter(alice, margret);

        alice.setTestsPositively();

        // direct contact Alice <-> Margret overrides Alice <-> Bob <-> Margret
        assertHasHighRisk(margret);
    }


    @Test
    public void test1NOAGE03Chain3() {
        ContactTracer tracer = new ContactTracer();

        Person alice = tracer.createPerson(20);
        Person bob = tracer.createPerson(30);
        Person margret = tracer.createPerson(30);

        tracer.registerEncounter(alice, bob);
        tracer.registerEncounter(bob, margret);

        alice.setTestsPositively();

        assertNoNotification(margret);
    }

    @Test
    public void test1NOAGE04SimpleNetwork() {
        ContactTracer tracer = new ContactTracer();

        Person alice = tracer.createPerson(20);
        Person bob = tracer.createPerson(30);
        Person margret = tracer.createPerson(30);
        Person anna = tracer.createPerson(30);

        tracer.registerEncounter(alice, bob);
        tracer.registerEncounter(bob, margret);
        tracer.registerEncounter(alice, margret);
        tracer.registerEncounter(bob, anna);

        alice.setTestsPositively();

        // anna is only an indirect contact
        assertNoNotification(anna);
    }

    @Test
    public void test1NOAGE05BidirectionalEncounters() {
        ContactTracer tracer = new ContactTracer();

        Person alice = tracer.createPerson(20);
        Person bob = tracer.createPerson(30);

        tracer.registerEncounter(bob, alice);

        alice.setTestsPositively();

        assertHasHighRisk(bob);
    }

    @Test
    public void test1NOAGE06YoungstersCompleteGraph() {
        Person[] persons = createCompleteGraph(6);

        persons[0].setTestsPositively();

        for (int i = 1; i < 6; ++i) {
            assertHasHighRisk(persons[i]);
        }
    }

    /**
     * <b>Tracing ohne Alter (mehrere positive Tests)</b> {@code MULTITEST} Wie der vorige Aspekt, aber mit mehreren positiven
     * Tests. Hier können wir auch die Zwischenzustände zwischen mehreren positiven Tests geprüft werden
     * (e.g. Test 1 => Benachrichtigungen prüfen => Test 2 => Benachrichtigungen prüfen, insbesondere bei
     * Personen die jetzt einen höherwertigen Status haben sollten).
     */


    @Test
    public void test2MULTITEST01MultiTestsSimpleNetwork() {
        ContactTracer tracer = new ContactTracer();

        Person alice = tracer.createPerson(20);
        Person bob = tracer.createPerson(30);
        Person margret = tracer.createPerson(30);
        Person anna = tracer.createPerson(30);

        tracer.registerEncounter(alice, bob);
        tracer.registerEncounter(bob, margret);
        tracer.registerEncounter(alice, margret);
        tracer.registerEncounter(bob, anna);

        alice.setTestsPositively();

        // anna is only an indirect contact
        assertNoNotification(anna);

        // now Bob also tests positively
        bob.setTestsPositively();

        assertHasHighRisk(anna);
        assertHasHighRisk(margret);
    }

    @Test
    public void test2MULTITEST02MultiTestTree() {
        ContactTracer tracer = new ContactTracer();

        // construct a tree of Person objects and encounters
        Person root = tracer.createPerson(20);

        Person l1_1 = tracer.createPerson(10);
        Person l1_2 = tracer.createPerson(10);
        Person l1_3 = tracer.createPerson(10);

        Person l1_1_1 = tracer.createPerson(10);
        Person l1_1_2 = tracer.createPerson(10);
        Person l1_2_1 = tracer.createPerson(10);
        Person l1_2_2 = tracer.createPerson(10);
        Person l1_3_1 = tracer.createPerson(10);
        Person l1_3_2 = tracer.createPerson(10);

        tracer.registerEncounter(root, l1_1);
        tracer.registerEncounter(root, l1_2);
        tracer.registerEncounter(root, l1_3);

        tracer.registerEncounter(l1_1, l1_1_1);
        tracer.registerEncounter(l1_1, l1_1_2);
        tracer.registerEncounter(l1_2, l1_2_1);
        tracer.registerEncounter(l1_2, l1_2_2);
        tracer.registerEncounter(l1_3, l1_3_1);
        tracer.registerEncounter(l1_3, l1_3_2);

        // keep track of lvl1 vs lvl2
        List<Person> lvl1 = List.of(l1_1, l1_2, l1_3);
        List<Person> lvl2 = List.of(l1_1_1, l1_1_2, l1_2_1, l1_2_2, l1_3_1, l1_3_2);

        // only root is positive
        root.setTestsPositively();
        for (Person p : lvl1) {
            assertHasHighRisk(p);
        }
        for (Person p : lvl2) {
            assertNoNotification(p);
        }

        // now all of lvl1 is positive
        for (Person p : lvl1) {
            p.setTestsPositively();
        }
        for (Person p : lvl2) {
            assertHasHighRisk(p);
        }
    }

    @Test
    public void test2MULTITEST03TwoConnected() {
        ContactTracer tracer = new ContactTracer();

        Person a = tracer.createPerson(20);
        Person b = tracer.createPerson(20);

        Person c = tracer.createPerson(20);

        Person d = tracer.createPerson(20);
        Person e = tracer.createPerson(20);

        // a - b - - c - - d - e

        // left to right
        tracer.registerEncounter(a, b);
        tracer.registerEncounter(b, c);

        // right to left
        tracer.registerEncounter(e, d);
        tracer.registerEncounter(d, c);

        a.setTestsPositively();
        e.setTestsPositively();

        // c is too far from both
        assertNoNotification(c);
        assertHasHighRisk(b);
        assertHasHighRisk(d);

        b.setTestsPositively();
        d.setTestsPositively();
        assertHasHighRisk(c);
    }

    @Test
    public void test2MULTITEST04PositiveMeansNoNotification() {
        ContactTracer tracer = new ContactTracer();

        Person a = tracer.createPerson(30);
        Person b = tracer.createPerson(30);
        Person c = tracer.createPerson(30);

        tracer.registerEncounter(a, b);
        tracer.registerEncounter(b, c);

        a.setTestsPositively();

        assertNoNotification(a);
    }

    @Test
    public void test2MULTITEST05_60IsNotElderly() {
        ContactTracer tracer = new ContactTracer();

        Person alice = tracer.createPerson(20);
        Person bob = tracer.createPerson(30);
        Person charlie = tracer.createPerson(60);

        tracer.registerEncounter(alice, bob);
        tracer.registerEncounter(bob, charlie);

        alice.setTestsPositively();

        assertNoNotification(alice);
        assertHasHighRisk(bob);
        assertNoNotification(charlie);
    }

    @Test
    public void test2MULTITEST06YoungstersMultiplePositiveCompleteGraph() {
        Person[] persons = createCompleteGraph(6);

        for (int i = 5; i >= 0; --i) {
            persons[i].setTestsPositively();

            for (int j = 0; j < 6; ++j) {
                if (j < i) {
                    assertHasHighRisk(persons[j]);
                } else {
                    assertNoNotification(persons[j]);
                }
            }
        }
    }

    /**
     * <b>Volles Contact Tracing </b> {@code FULL} Hier prüfen wir funktionierendes Contact Tracing mit unterschiedlich grossen
     * Netzwerken aus Begegnungen, mit Personen unterschiedlichen Alters (Volle Spezifikation der Aufgabe
     * soll erfüllt sein).
     */

    @Test
    public void test3FULL01BasicWithAge() {
        ContactTracer tracer = new ContactTracer();

        Person a = tracer.createPerson(30);
        Person b = tracer.createPerson(30);
        Person c = tracer.createPerson(130);

        tracer.registerEncounter(a, b);
        tracer.registerEncounter(b, c);

        a.setTestsPositively();

        assertHasHighRisk(b);
        assertHasLowRisk(c);
    }

    @Test
    public void test3FULL02TreeWithAge() {
        ContactTracer tracer = new ContactTracer();

        // construct a tree of Person objects and encounters
        Person root = tracer.createPerson(20);

        Person l1_1 = tracer.createPerson(10);
        Person l1_2 = tracer.createPerson(50);
        Person l1_3 = tracer.createPerson(100);

        Person l1_1_1 = tracer.createPerson(100);
        Person l1_1_2 = tracer.createPerson(100);
        Person l1_2_1 = tracer.createPerson(100);
        Person l1_2_2 = tracer.createPerson(100);
        Person l1_3_1 = tracer.createPerson(10);
        Person l1_3_2 = tracer.createPerson(10);

        tracer.registerEncounter(root, l1_1);
        tracer.registerEncounter(root, l1_2);
        tracer.registerEncounter(root, l1_3);

        tracer.registerEncounter(l1_1, l1_1_1);
        tracer.registerEncounter(l1_1, l1_1_2);
        tracer.registerEncounter(l1_2, l1_2_1);
        tracer.registerEncounter(l1_2, l1_2_2);
        tracer.registerEncounter(l1_3, l1_3_1);
        tracer.registerEncounter(l1_3, l1_3_2);

        // keep track of lvl1 vs lvl2
        List<Person> lvl1 = List.of(l1_1, l1_2, l1_3);
        List<Person> lvl2 = List.of(l1_3_1, l1_3_2);
        List<Person> lvl2_elderly = List.of(l1_1_1, l1_1_2, l1_2_1, l1_2_2);

        // only root is positive
        root.setTestsPositively();
        for (Person p : lvl1) {
            assertHasHighRisk(p);
        }
        for (Person p : lvl2_elderly) {
            assertHasLowRisk(p);
        }

        for (Person p : lvl2) {
            assertNoNotification(p);
        }
    }

    @Test
    public void test3FULL03AllMultihopElderly() {
        List<Person> multihopParticipants = multiHopSetup();
        Person receiver = multihopParticipants.get(0);
        Person onehop = multihopParticipants.get(1);
        Person twohop = multihopParticipants.get(2);
        Person threehop = multihopParticipants.get(3);

        // three hops (too far away)
        threehop.setTestsPositively();
        assertNoNotification(receiver);

        // two hops (indirect)
        twohop.setTestsPositively();
        assertHasLowRisk(receiver);

        // one hop (direct)
        onehop.setTestsPositively();
        assertHasHighRisk(receiver);
    }

    @Test
    public void test3FULL04AllMultihopOutOfOrderElderly() {
        List<Person> multihopParticipants = multiHopSetup();
        Person receiver = multihopParticipants.get(0);
        Person onehop = multihopParticipants.get(1);
        Person twohop = multihopParticipants.get(2);
        Person threehop = multihopParticipants.get(3);

        // two hops (indirect)
        twohop.setTestsPositively();
        assertHasLowRisk(receiver);

        // one hop (direct)
        onehop.setTestsPositively();
        assertHasHighRisk(receiver);

        // three hop (direct)
        threehop.setTestsPositively();
        assertHasHighRisk(receiver); // still high-risk
    }

    /** Dieser Test Case demonstriert die Low-Risk Benachrichtigung von älteren Personen. */
    @Test
    public void test3FULL05_given_testEldlerlyPerson() {
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
        // Bob is ein direkter Kontakt -> High-Risk Benachrichtigung
        assertHasHighRisk(bob);
        // Margret ist ein indirekter Kontakt und > 60 Jahre alt -> Keine Benachrichtigung
        assertHasLowRisk(margret);
    }

    /** Dieser Test Case demonstriert wie mehrere positive Tests markiert werden. */
    @Test
    public void test3FULL06_given_testMultiplePositiveTests() {
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
        // Margret ist ein indirekter Kontakt und > 60 Jahre alt -> Keine Benachrichtigung
        assertHasLowRisk(margret);


        // Jetzt testet auch Bob positiv
        bob.setTestsPositively();


        // Alice gilt weiterhin als positiv getestet -> Keine Benachrichtigung
        assertNoNotification(alice);
        // Da Bob nun positiv getestet wurde, soll er Keine Benachrichtigung mehr erhalten
        assertNoNotification(bob);
        // Margret ist nun ein direkter Kontakt	-> High-Risk Benachrichtigung
        assertHasHighRisk(margret);
    }

    @Test
    public void test3FULL07MultiplePositiveComplex() {
        ContactTracer tracer = new ContactTracer();

        Person alice = tracer.createPerson(20);
        Person bob = tracer.createPerson(30);
        Person charlie = tracer.createPerson(80);
        Person david = tracer.createPerson(90);
        Person erin = tracer.createPerson(40);
        Person frank = tracer.createPerson(70);

        tracer.registerEncounter(alice, bob);
        tracer.registerEncounter(alice, erin);
        tracer.registerEncounter(bob, charlie);
        tracer.registerEncounter(charlie, david);
        tracer.registerEncounter(erin, charlie);
        tracer.registerEncounter(frank, erin);
        tracer.registerEncounter(frank, david);


        alice.setTestsPositively();


        assertNoNotification(alice);
        assertHasHighRisk(bob);
        assertHasLowRisk(charlie);
        assertNoNotification(david);
        assertHasHighRisk(erin);
        assertHasLowRisk(frank);


        bob.setTestsPositively();


        assertNoNotification(alice);
        assertNoNotification(bob);
        assertHasHighRisk(charlie);
        assertHasLowRisk(david);
        assertHasHighRisk(erin);
        assertHasLowRisk(frank);


        david.setTestsPositively();


        assertNoNotification(alice);
        assertNoNotification(bob);
        assertHasHighRisk(charlie);
        assertNoNotification(david);
        assertHasHighRisk(erin);
        assertHasHighRisk(frank);
    }

    // Test Utilities

    /**
     * Constructs the following network of encounters:
     *
     * (receiver) ─────────────────────────  (one-hop)
     *    │ │
     *    │ └────────── (p0) ──────────────  (two-hop)
     *    │
     *    └───── (p1) ───────── (p2) ──────  (three-hop)
     *
     * Returns the list [receiver, one-hop, two-hop, three-hop].
     */
    private static List<Person> multiHopSetup() {
        ContactTracer tracer = new ContactTracer();

        // construct a tree of Person objects and encounters
        Person receiver = tracer.createPerson(200);

        // direct contact
        Person onehop = tracer.createPerson(20);

        // two-hop contact
        Person twohop_0 = tracer.createPerson(20);
        Person twohop_1 = tracer.createPerson(20);

        // three-hop contact
        Person threehop_0 = tracer.createPerson(20);
        Person threehop_1 = tracer.createPerson(20);
        Person threehop_2 = tracer.createPerson(20);

        tracer.registerEncounter(receiver, onehop);

        tracer.registerEncounter(receiver, twohop_1);
        tracer.registerEncounter(twohop_1, twohop_0);

        tracer.registerEncounter(receiver, threehop_2);
        tracer.registerEncounter(threehop_2, threehop_1);
        tracer.registerEncounter(threehop_1, threehop_0);

        return List.of(receiver, onehop, twohop_0, threehop_0);
    }

    private static Person[] createCompleteGraph(int size) {
        return createCompleteGraph(size, 20);
    }

    private static Person[] createCompleteGraph(int size, int age) {
        ContactTracer tracer = new ContactTracer();

        Person[] persons = new Person[size];
        for (int i = 0; i < size; ++i) {
            persons[i] = tracer.createPerson(age);
        }

        for (int i = 0; i < size - 1; ++i) {
            for (int j = i + 1; j < size; ++j) {
                tracer.registerEncounter(persons[i], persons[j]);
            }
        }

        return persons;
    }

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
            HashSet<Integer> listSet = new HashSet<>(l);
            int expectedIncreaseInSize = listSet.size();
            int sizeBefore = s.size();
            s.addAll(listSet);
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

    private static void assertIntersection(List<Integer> l1, List<Integer> l2, boolean shouldHaveIntersection) {
        HashSet<Integer> s = new HashSet<Integer>(l1);
        s.retainAll(l2);

        if (shouldHaveIntersection) {
            assertFalse(s.isEmpty());
        } else {
            assertTrue(s.isEmpty());
        }
    }

    public static void assertNumIdsEquals(int numIds, List<Integer> ids) {
        var s = new HashSet<>(ids);
        assertEquals(numIds, s.size(), "Expected a total of " + numIds + " unique IDs in list but got " + s.size() + " (unique IDs counted " + s + ")");
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

