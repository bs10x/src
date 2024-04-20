public class Rezepte {

	public static Rezept schokokuchen() {
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

		return new Rezept(e8);
	}

	public static Rezept doener() {
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

		return new Rezept(e3);
	}
}
