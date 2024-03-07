import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.HashSet;

//import Person.NotificationType;





public class ContactTracer {
	
	private List<Person> people;
    private Map<Integer, List<Integer>> encounters;

    private HashSet<Integer> generatedIds;

    public ContactTracer() {
        this.people = new ArrayList<>();
        this.encounters = new HashMap<>();
        this.generatedIds = new HashSet<>();
    }

	public Person createPerson(int age) {
		Person newPerson = new PersonObject(age);
		ContactTracer tracer = this;
		newPerson.setTracer(tracer);
		people.add(newPerson);
		return newPerson;
	}

	// Protokolliert eine (beidseitige) Begegnung von p1 und p2.
	public void registerEncounter(Person p1, Person p2) {
		int p1Id = generateEncounterId();
		int p2Id = generateEncounterId();
		
		p1.setUsedId(p1Id);
		p2.setUsedId(p2Id);
		
		p1.setSeenId(p2Id);
		p2.setSeenId(p1Id);
		
		int encounterNumber = people.size() + 1;
		
		List<Integer> encounterPair = Arrays.asList(p1Id, p2Id);
			
		encounters.put(encounterNumber, encounterPair);
	}
	
	public int generateEncounterId() {
		Random random = new Random();
		int encounterId = random.nextInt(9000) + 1000;
		if (!generatedIds.contains(encounterId)) {
			generatedIds.add(encounterId);
		} else encounterId = generateEncounterId();
		return encounterId;
	}
	
	
	public void printPrettyPeopleAge() {
		for(Person p : people) {
			System.out.println(p.getAge());
		}
	}
	
	public void printEncounters() {
		for(int i : encounters.keySet()) {
			System.out.println(encounters.get(i));
		}
	}
	
	public void updateNotifications(List<Integer> seenIds) {
		// iterate over all Ids in seenIds, i.e. direct encounters
		for (int seenId : seenIds) {
			Person seenP = getById(seenId);
			// System.out.println(seenId); // print seenId
			// System.out.println(seenP.getAge()); // print seenP.age
			// list indirect encounters by seenId
			List<Integer> smelltIds = seenP.getSeenIds();

			// iterate over indirecte encounters
			for (int smelltId : smelltIds) {
				Person smelltP = getById(smelltId);
				// conditions; not testsPositively, > 60 y/o
				if (!smelltP.getTestsPositively() && smelltP.getAge() > 60) {
					smelltP.setNotification(Person.NotificationType.LowRiskNotification);
				}
			}

			// System.out.println(seenP.getAge());
			// System.out.println(seenP.getTestsPositively());
			// conditions; not testsPositively, leq 60 y/o or > 60 y/o
			if (!seenP.getTestsPositively() && (seenP.getAge() <= 60 || seenP.getAge() > 60)) {
				seenP.setNotification(Person.NotificationType.HighRiskNotification);
			}
		}
	}
	
	public Person getById(int Id) {
	    return people.stream()
	                 .filter(p -> p.getUsedIds().contains(Id))
	                 .findFirst()
	                 .orElse(null);
	}


	
	
	
	
	
	
	
}
