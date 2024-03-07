
public class Main {
	
	public static void main(String[] args) {
		
		ContactTracer tracer = new ContactTracer();
		
		Person alice = tracer.createPerson(20);
		Person bob = tracer.createPerson(30);
		Person margret = tracer.createPerson(30);
		Person trudi = tracer.createPerson(60);
		
		tracer.registerEncounter(alice, bob);
		tracer.registerEncounter(alice, trudi);
		
		
		
		//System.out.println(alice.getUsedIds());
		System.out.println(alice.getSeenIds());
		
		//System.out.println(bob.getUsedIds());
		//System.out.println(bob.getSeenIds());		
		//System.out.println(alice.getTestsPositively());
		
		
		// Alice wird positiv getestet 
		alice.setTestsPositively();
		
		//System.out.println(alice.getTestsPositively());
		
		
		
		
		//tracer.printEncounters();
		//tracer.printPrettyPeopleAge();
		
		
		
		
		
		
		

		// Alice ist positiv, hat daher keine Benachrichtigung
		System.out.println("Alice notification (erwartet: NoNotification): " + alice.getNotification());
		// Bob ist ein direkter Kontakt -> High-Risk Benachrichtigung
		System.out.println("Bob notification (erwartet: HighRiskNotification): " + bob.getNotification());

		System.out.println("Margret notification (erwartet: NoNotification): " + margret.getNotification());	
	}

}
