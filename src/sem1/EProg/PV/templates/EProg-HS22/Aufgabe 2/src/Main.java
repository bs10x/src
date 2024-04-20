
public class Main {
	
	public static void main(String[] args) {
		// Sie koennen die main Methode beliebig aendern.

		ContactTracer tracer = new ContactTracer();
		
		Person alice = tracer.createPerson(20);
		Person bob = tracer.createPerson(30);
		Person margret = tracer.createPerson(30);
		
		tracer.registerEncounter(alice, bob);
		
		// alice wird positiv getestet 
		alice.setTestsPositively();

		// alice ist positiv, hat daher keine Benachrichtigung
		System.out.println("Alice notification (erwartet: NoNotification): " + alice.getNotification());
		// bob ist ein direkter Kontakt -> High-Risk Benachrichtigung
		System.out.println("Bob notification (erwartet: HighRiskNotification): " + bob.getNotification());

		System.out.println("Margret notification (erwartet: NoNotification): " + margret.getNotification());		
	}

}
