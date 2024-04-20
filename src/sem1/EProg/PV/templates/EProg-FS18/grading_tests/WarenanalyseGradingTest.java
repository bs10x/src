import static java.nio.file.Files.createTempFile;
import static java.nio.file.Files.readAllLines;
import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Rule;
import org.junit.Test;

public class WarenanalyseGradingTest {

	@Test
	public void testAll() throws IOException {
		List<String> input = asList(
				"Apfeli Fraenzili 1 1.00", 
				"Kaffeekapseli VISli 3000 0.45",
				"US-Tastaturli LSTli 80 24.95", 
				"Pilzli Marioli 10 100.00", 
				"Sternli Marioli 3 50.00",
				"Capeli Marioli 1 79.90", 
				"Pilzli Luigili 3 150.00", 
				"Sternli Luigili 4 75.00",
				"Pilzli Luigili 3 145.00",
				"Capeli Luigili 1 950.05", 
				"Pfirsichli Peachli 42 1.65");

		String expectedOutput =
				"US-Tastaturli 24.9[54]\\d*\n" +
				"Luigili 2135.0[54]\\d*\n" +
				"Capeli\n?";
		assertMatches(input, expectedOutput);
	}

	@Test
	public void testBugs() throws IOException {
		List<String> input = asList(
				"US-Tastaturli LSTli 80 24.95",
				"US-Tastaturli LSTli 1 20.00",
				"US-Tastaturli LSTli 1 30.00",
				"Capeli Luigili 1 1000.00",
				"Capeli Luigili 1 1.00");

		String expectedOutput =
				"US-Tastaturli 24.9[54]\\d*\n" +
				"LSTli 2046(\\.0+)?\n" +
				"Capeli\n?";
		assertMatches(input, expectedOutput);
	}

	@Test
	public void testFirstPart() throws IOException {
		List<String> input = asList(
		        "Apfeli Fraenzili 1 1.00", 
				"Kaffeekapseli VISli 3000 0.45",
				"US-Tastaturli LSTli 80 24.95", 
				"Pilzli Marioli 10 100.00", 
				"Sternli Marioli 3 50.00",
				"Capeli Marioli 1 79.90", 
				"Pilzli Luigili 3 150.00", 
				"Sternli Luigili 4 75.00",
				"Pilzli Luigili 3 145.00",
				"Capeli Luigili 1 950.05", 
				"Pfirsichli Peachli 42 1.65");

		assertMatchesLowerCase(input,
				"us-tastaturli 24.9[54]\\d*(.|\\n)*");
	}

	@Test
	public void testSecondPart() throws IOException {
		List<String> input = asList(
		        "Apfeli Fraenzili 1 1.00", 
				"Kaffeekapseli VISli 3000 0.45",
				"US-Tastaturli LSTli 80 24.95", 
				"Pilzli Marioli 10 100.00", 
				"Sternli Marioli 3 50.00",
				"Capeli Marioli 1 79.90", 
				"Pilzli Luigili 3 150.00", 
				"Sternli Luigili 4 75.00",
				"Pilzli Luigili 3 145.00",
				"Capeli Luigili 1 950.05", 
				"Pfirsichli Peachli 42 1.65");

		assertMatchesLowerCase(input,
				"(.|\\n)*luigili 2135.0[54]\\d*(.|\\n)*");
	}

	@Test
	public void testThirdPart() throws IOException {
		List<String> input = asList(
		        "Apfeli Fraenzili 1 1.00", 
				"Kaffeekapseli VISli 3000 0.45",
				"US-Tastaturli LSTli 80 24.95", 
				"Pilzli Marioli 10 100.00", 
				"Sternli Marioli 3 50.00",
				"Capeli Marioli 1 79.90", 
				"Pilzli Luigili 3 150.00", 
				"Sternli Luigili 4 75.00",
				"Pilzli Luigili 3 145.00",
				"Capeli Luigili 1 950.05", 
				"Pfirsichli Peachli 42 1.65");

		assertMatchesLowerCase(input,
				"(.|\\n)*capeli\n?");
	}

	@Test
	public void testSingleEntry() throws IOException {
		List<String> input = asList("Kaffeekapseli VISli 3000 0.45");

		String expectedOutput =
				"Kaffeekapseli 0.4[54]\\d*\n" +
				"VISli 1350(\\.0+)?\n" +
				"Kaffeekapseli\n?";
		assertMatches(input, expectedOutput);
	}

	@Test
	public void testSingleEntryFuzzy() throws IOException {
		List<String> input = asList("Kaffeekapseli VISli 3000 0.45");
		List<String> patterns = asList(
				"kaffeekapseli[^\n]*0.4[54]", "visli", "1350", "kaffeekapseli$");

		runAndAssert(input, output -> {
			for (String pattern : patterns) {
				assertTrue(output.toLowerCase().matches("(.|\n)*" + pattern + "(.|\n)*"));
			}
		});
	}

	private void assertMatches(List<String> input, String pattern) throws IOException {
		runAndAssert(input, output -> {
			if (!output.matches(pattern)) {
			    throw new AssertionError("\"" + output.replace("\n", "\\n") + "\" did not match");
			}
		});
	}

	private void assertMatchesLowerCase(List<String> input, String pattern) throws IOException {
		runAndAssert(input, output -> {
		    if (!output.toLowerCase().matches(pattern)) {
				throw new AssertionError("\"" + output.toLowerCase().replace("\n", "\\n") + "\" did not match");
			}
		});
	}

	private void runAndAssert(List<String> input, Consumer<String> assertion) throws IOException {
		try {
			assertion.accept(runAnalyze(input, true));
		} catch (Throwable t) {
			assertion.accept(runAnalyze(input, false));
		}
	}

	private String runAnalyze(List<String> input, boolean withNewline) throws IOException {
		Path inputFile = createTempFile("transaktionen-in", ".txt");
		Path outputFile = createTempFile("transaktionen-out", ".txt");
		try {
			try (BufferedWriter writer = Files.newBufferedWriter(inputFile)) {
				String suffix = withNewline ? "\n" : "";
				writer.write(input.stream().collect(joining("\n")) + suffix);
			}
			Warenanalyse.analyse(inputFile.toFile(), outputFile.toFile());
			return normalize(readAllLines(outputFile));
		} finally {
			inputFile.toFile().deleteOnExit();
			outputFile.toFile().deleteOnExit();
		}
	}

	private String normalize(List<String> output) {
		return output.stream().map(String::trim).filter(l -> !l.isEmpty())
				.map(l -> l.replaceAll("%$", "")).collect(joining("\n"));
	}
}
