import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class RezeptBGradingTest {

	@Test
	public void testVereinfacheEmptyRezept() {
		// minimal test for them doing anything:
		testVereinfache2StepRezept();
		
		Einheit e = new Einheit();
		e.schritte.add("step");
		Rezept rezept = new Rezept(e);

		var initial = rezeptToMap(rezept);
		rezept.vereinfache();
		var after = rezeptToMap(rezept);
		assertEquals(initial, after);
	}
	
	@Test
	public void testVereinfache2StepRezept() {
		Einheit e1 = new Einheit();
		e1.schritte.add("step1");
		Einheit e2 = new Einheit();
		e2.schritte.add("step2");
		e2.vorbedingungen.add(e1);
		Rezept rezept = new Rezept(e2);

		rezept.vereinfache();
		var after = rezeptToMap(rezept);
		assertFalse(after.containsKey(List.of("step1")));
		assertFalse(after.containsKey(List.of("step2")));
		assertEquals(Set.of(), after.get(List.of("step1", "step2")));
	}
	
	@Test
	public void testVereinfache3StepRezept() {
		Einheit e1 = new Einheit();
		e1.schritte.add("step1");
		Einheit e2 = new Einheit();
		e2.schritte.add("step2");
		e2.vorbedingungen.add(e1);
		Einheit e3 = new Einheit();
		e3.schritte.add("step3");
		e3.vorbedingungen.add(e2);
		Rezept rezept = new Rezept(e3);

		rezept.vereinfache();
		var after = rezeptToMap(rezept);
		assertFalse(after.containsKey(List.of("step1")));
		assertFalse(after.containsKey(List.of("step2")));
		assertFalse(after.containsKey(List.of("step3")));
		assertEquals(Set.of(), after.get(List.of("step1", "step2", "step3")));
	}
	
	@Test
	public void testVereinfacheLongChains() {
		for (int max = 5; max < 15; max++) {
			Einheit prev = new Einheit();
			prev.schritte.add("step0");
			for (int j = 1; j < max; j++) {
				Einheit e = new Einheit();
				e.schritte.add("step" + j);
				e.vorbedingungen.add(prev);
				prev = e;
			}
			Rezept rezept = new Rezept(prev);

			rezept.vereinfache();
			var after = rezeptToMap(rezept);
			assertEquals(1, after.size());
			for (int j = 0; j < max; j++) {
				assertFalse(after.containsKey(List.of("step" + j)));
			}

			assertEquals(Set.of(), after.get(
					IntStream.range(0, max)
							.mapToObj(x -> "step" + x)
							.collect(Collectors.toList())));
		}
	}
	
	@Test
	public void testVereinfacheSchoggi() {
		Rezept rezept = schokokuchen();

		rezept.vereinfache();
		var after = rezeptToMap(rezept);
		
		assertEquals(
				Set.of(List.of("Zutaten bereit stellen")),
				after.get(List.of(
						"Butter in Pfanne schmelzen",
						"Schokolade beifügen, schmelzen",
						"Pfanne von der Platte nehmen",
						"Zucker beifügen",
						"Nüsse beifügen",
						"Mehl beifügen")));

		assertTrue(
				after.containsKey(List.of(
						"In Ofenmitte 30 min backen",
						"Mit Kakao bestäuben",
						"Servieren")));
		assertEquals(6, after.size());
	}
	
	@Test
	public void testVereinfacheDiamondRezept() {
		// minimal test for them doing anything:
		testVereinfache2StepRezept();


		Einheit e0 = new Einheit();
		e0.schritte.add("step0");
		Einheit e1 = new Einheit();
		e1.schritte.add("step1");
		e1.vorbedingungen.add(e0);
		Einheit e2 = new Einheit();
		e2.schritte.add("step2");
		e2.vorbedingungen.add(e0);
		Einheit e3 = new Einheit();
		e3.schritte.add("step3");
		e3.vorbedingungen.add(e1);
		e3.vorbedingungen.add(e2);
		Rezept rezept = new Rezept(e3);

		var initial = rezeptToMap(rezept);
		rezept.vereinfache();
		var after = rezeptToMap(rezept);
		assertEquals(initial, after);
	}

	static private Map<List<String>, Set<List<String>>> rezeptToMap(Rezept rezept) {
		Map<List<String>, Set<List<String>>> res = new HashMap<>();
		collectMap(res, rezept.letzteEinheit);
		return res;
	}

	static private void collectMap(Map<List<String>, Set<List<String>>> res, Einheit einheit) {
		if (!res.containsKey(einheit.schritte)) {
			Set<List<String>> vorb = einheit.vorbedingungen.stream()
					.map(e -> e.schritte)
					.map(ArrayList::new)
					.collect(Collectors.toSet());
			res.put(new ArrayList<>(einheit.schritte), vorb);
			for (Einheit pred : einheit.vorbedingungen) {
				collectMap(res, pred);
			}
		}
	}

	private static Rezept schokokuchen() {
		Einheit e1 = new Einheit();
		e1.schritte.add("Zutaten bereit stellen");
		
		Einheit e2 = new Einheit();
		e2.vorbedingungen.add(e1);
		e2.schritte.add("Ofen auf 180 C vorheizen");
		
		Einheit e3 = new Einheit();
		e3.vorbedingungen.add(e1);
		e3.schritte.add("Butter in Pfanne schmelzen");
		e3.schritte.add("Schokolade beifügen, schmelzen");
		e3.schritte.add("Pfanne von der Platte nehmen");
		
		Einheit e4 = new Einheit();
		e4.vorbedingungen.add(e3);
		e4.schritte.add("Zucker beifügen");
		e4.schritte.add("Nüsse beifügen");
		e4.schritte.add("Mehl beifügen");
		
		Einheit e5 = new Einheit();
		e5.vorbedingungen.add(e1);
		e5.schritte.add("Eigelb von Eiweiss trennen");
		e5.schritte.add("Eiweiss steif schlagen");
		
		Einheit e6 = new Einheit();
		e6.vorbedingungen.add(e4);
		e6.vorbedingungen.add(e5);
		e6.schritte.add("Eigelb beifügen");
		e6.schritte.add("Eischnee unter Masse heben");
		e6.schritte.add("Teig in Form geben");
		
		Einheit e7 = new Einheit();
		e7.vorbedingungen.add(e2);
		e7.vorbedingungen.add(e6);
		e7.schritte.add("In Ofenmitte 30 min backen");
		e7.schritte.add("Mit Kakao bestäuben");
		
		Einheit e8 = new Einheit();
		e8.vorbedingungen.add(e7);
		e8.schritte.add("Servieren");
		Rezept rezept = new Rezept(e8);
		return rezept;
	}
}
