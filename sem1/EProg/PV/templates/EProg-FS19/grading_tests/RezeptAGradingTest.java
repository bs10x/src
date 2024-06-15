import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Test;

public class RezeptAGradingTest {
	
	@Test
	public void testGivenLinearisiereDoener() {
		Rezept rezept = doener();

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		rezept.linearisiere(new PrintStream(stream));
		String output = stream.toString();
		String[] lines = output.split("\r?\n");

		String[] sol1 = {
				"1",
				"Kollegen auftreiben",
				"2",
				"Geld auftreiben",
				"3",
				"Dönerbude aufsuchen",
				"Döner bestellen",
				"\"Mit allem\"",
				"\"Und scharf\"",
				"Bezahlen" };
		String[] sol2 = {
				"1",
				"Geld auftreiben",
				"2",
				"Kollegen auftreiben",
				"3",
				"Dönerbude aufsuchen",
				"Döner bestellen",
				"\"Mit allem\"",
				"\"Und scharf\"",
				"Bezahlen" };
		assertThat(lines, anyOf(is(sol1), is(sol2)));
	}

	private static Rezept doener() {
		Einheit e1 = new Einheit();
		e1.schritte.add("Kollegen auftreiben");
		
		Einheit e2 = new Einheit();
		e2.schritte.add("Geld auftreiben");
		
		Einheit e3 = new Einheit();
		e3.vorbedingungen.add(e2);
		e3.vorbedingungen.add(e1);
		e3.schritte.add("Dönerbude aufsuchen");
		e3.schritte.add("Döner bestellen");
		e3.schritte.add("\"Mit allem\"");
		e3.schritte.add("\"Und scharf\"");
		e3.schritte.add("Bezahlen");
		Rezept rezept = new Rezept(e3);
		return rezept;
	}

	@Test
	public void testLinearisiereEmptyRezept() {
		Einheit e = new Einheit();
		e.schritte.add("step");
		Rezept rezept = new Rezept(e);

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		rezept.linearisiere(new PrintStream(stream));
		String output = stream.toString();
		String[] lines = output.split("\r?\n");
		String[] sol1 = {
				"1",
				"step"
		};
		assertThat(lines, is(sol1));
	}
	
	@Test
	public void testLinearisiereLinearRezept() {
		Einheit e1 = new Einheit();
		e1.schritte.add("step1");
		Einheit e2 = new Einheit();
		e2.schritte.add("step2");
		e2.vorbedingungen.add(e1);
		Rezept rezept = new Rezept(e2);

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		rezept.linearisiere(new PrintStream(stream));
		String output = stream.toString();
		String[] lines = output.split("\r?\n");
		String[] sol1 = {
				"1",
				"step1",
				"2",
				"step2"
		};
		assertThat(lines, is(sol1));
	}
	
	@Test
	public void testLinearisiereDiamondRezept() {
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

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		rezept.linearisiere(new PrintStream(stream));
		String output = stream.toString();
		String[] lines = output.split("\r?\n");
		
		String[] sol1 = {
				"1",
				"step0",
				"2",
				"step1",
				"3",
				"step2",
				"4",
				"step3"
		};
		String[] sol2 = {
				"1",
				"step0",
				"2",
				"step2",
				"3",
				"step1",
				"4",
				"step3"
		};
		assertThat(lines, anyOf(is(sol1), is(sol2)));
	}
	
	@Test
	public void testLinearisiereSchoggi() {
		Rezept rezept = schokokuchen();
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		rezept.linearisiere(new PrintStream(stream));
		String output = stream.toString();
		String[] lines = output.split("\r?\n");
		
		assertIsValidLinearisation(lines, rezept);
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

	static private void assertIsValidLinearisation(String[] lines, Rezept rezept) {
		Set<List<Einheit>> all = generateAllLinearisations(rezept);
		List<Matcher<? super String[]>> solutions = all.stream()
				.map(RezeptAGradingTest::linearisationToString)
				.map(CoreMatchers::is)
				.collect(Collectors.toList());
		assertThat(lines, anyOf(solutions));
	}

	static private String[] linearisationToString(List<Einheit> linear) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < linear.size(); i++) {
			Einheit e = linear.get(i);
			sb.append((i + 1) + "\n");
			sb.append(String.join("\n", e.schritte) + "\n");
		}
		return sb.toString().split("\n");
	}

	static private Set<List<Einheit>> generateAllLinearisations(Rezept rezept) {
		Set<Einheit> todo = new HashSet<>();
		Set<List<Einheit>> res = new HashSet<>();
		Set<Einheit> emitted = new HashSet<>();
		List<Einheit> current = new ArrayList<>();
		collectEinheiten(rezept.letzteEinheit, todo);
		generateLinearPermutations(current, res, emitted, todo);
		return res;
	}

	static private void generateLinearPermutations(List<Einheit> current, Set<List<Einheit>> res, Set<Einheit> emitted, Set<Einheit> todo) {
		if (todo.isEmpty()) {
			res.add(new ArrayList<>(current));
			return;
		}
		
		// find all Einheiten ready to be emitted and start a recursion from there
		HashSet<Einheit> todoCopy = new HashSet<>(todo);
		for (Einheit e : todo) {
			assert !emitted.contains(e);
			if (e.vorbedingungen.stream().allMatch(emitted::contains)) {
				// e is ready to be emitted
				current.add(e);
				emitted.add(e);
				todoCopy.remove(e);
				generateLinearPermutations(current, res, emitted, todoCopy);
				todoCopy.add(e);
				current.remove(current.size() - 1);
				emitted.remove(e);
			}
		}
	}

	static private void collectEinheiten(Einheit e, Set<Einheit> result) {
		// don't bother only visiting once. 'rezept' is acyclic
		result.add(e);
		for (Einheit einheit : e.vorbedingungen) {
			collectEinheiten(einheit, result);
		}
	}
}
