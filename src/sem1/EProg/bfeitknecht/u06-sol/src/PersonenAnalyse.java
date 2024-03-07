import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/* 
 * Author: Maximiliana Muster
 * für Einführung in die Programmierung
 * 
 * Analysiert einen Datensatz von Personen-Eigenschaften. Findet "ungesunde" Personen und teilt
 * alle Personen in Trainingspartner-Paare ein.
 */
public class PersonenAnalyse {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("body.dat.txt"));
		Person[] personen = liesPersonen(scanner);
		scanner.close();

		PrintStream ungesundAusgabe = new PrintStream(new File("ungesund.txt"));
		druckeUngesunde(personen, ungesundAusgabe);
		ungesundAusgabe.close();

		druckeGuteTrainingsPartner(personen, System.out);
	}

	/**
	 * Liest die Personen-Daten von dem gegebenen Scanner ein und gibt sie als Person[] zurück.
	 */
	static Person[] liesPersonen(Scanner scanner) {
		Person[] personen = new Person[scanner.nextInt()];
		for (int i = 0; i < personen.length; i++)
			personen[i] = liesPerson(scanner);
		return personen;
	}

	/**
	 * Liest eine einzelne Person von dem Scanner ein. Nimmt an, dass der Scanner am Beginn einer
	 * Zeile steht.
	 */
	static Person liesPerson(Scanner scanner) {
		double schulterBreite = scanner.nextDouble();
		double brustTiefe = scanner.nextDouble();
		double brustBreite = scanner.nextDouble();

		int alter = scanner.nextInt();
		double gewicht = scanner.nextDouble();
		double groesse = scanner.nextDouble();
		boolean istMaennlich = scanner.nextInt() == 1;
		return new Person(alter, gewicht, groesse, istMaennlich, schulterBreite, brustTiefe,
				brustBreite);
	}

	/**
	 * Findet alle Personen, die nicht normalgewichtig sind und gibt deren Beschreibungen, sowie die
	 * entsprechende Gewichts-Klassen aus.
	 */
	static void druckeUngesunde(Person[] personen, PrintStream ausgabe) {
		for (int i = 0; i < personen.length; i++) {
			Person person = personen[i];
			String klasse = gewichtsKlasse(person);
			if (!klasse.equals("normalgewichtig"))
				ausgabe.println(person.beschreibung() + " ist " + klasse);
		}
	}

	/**
	 * Gibt die Gewichts-Klasse der gegebenen Person als String zurück. Mögliche Klassen sind
	 * "fettleibig", "übergewichtig", "normalgewichtig" und "untergewichtig".
	 */
	static String gewichtsKlasse(Person person) {
		if (person.bodyMassIndex() >= 30)
			return "fettleibig";
		if (person.bodyMassIndex() >= 25)
			return "übergewichtig";
		if (person.bodyMassIndex() < 18.5)
			return "untergewichtig";
		return "normalgewichtig";
	}

	/**
	 * Gibt die Partner-Qualität von zwei Trainingspartner p1 und p2 zurück. Wert liegt zwischen 0
	 * (schlechtester Wert) und 1 (bester Wert).
	 */
	static double partnerQualitaet(Person p1, Person p2) {
		double distanz = Math.pow(p1.groesse - p2.groesse, 2)
				+ 0.2 * Math.abs(p1.brustTiefe * p1.brustBreite - p2.brustTiefe * p2.brustBreite)
				+ 0.5 * Math.pow(p1.schulterBreite - p2.schulterBreite, 2);
		return 1 / (1 + distanz);
	}

	/**
	 * Findet alle Trainings-Partner, deren Qualität grösser als 0.8 ist, und gibt diese Personen,
	 * sowie deren Gewichtsklasse, aus.
	 */
	static void druckeGuteTrainingsPartner(Person[] personen, PrintStream ausgabe) {
		for (int i = 0; i < personen.length; i++) {
			for (int j = i + 1; j < personen.length; j++) {
				double q = partnerQualitaet(personen[i], personen[j]);
				if (q > 0.8) {
					ausgabe.println(personen[i].beschreibung() + ", " + gewichtsKlasse(personen[i]));
					ausgabe.println(personen[j].beschreibung() + ", " + gewichtsKlasse(personen[j]));
					ausgabe.println("Qualität: " + q);
				}
			}
		}
	}
}
