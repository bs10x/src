import java.util.ArrayList;
import java.util.List;

public class PersonObject /* aka woman */ implements Person {
	
	private boolean testsPositively = false;
	private int age;
	private List<Integer> usedIds;
    private List<Integer> seenIds;
	private NotificationType notification;
	private ContactTracer tracer;
	

	public PersonObject(int age) {
		this.age = age;
        this.usedIds = new ArrayList<>();
        this.seenIds = new ArrayList<>();
        this.notification = NotificationType.NoNotification;
	}
	
	public void setTracer(ContactTracer tracer) {
		this.tracer = tracer;
	}

	@Override
	public List<Integer> getUsedIds() {
		return usedIds;
	}

	@Override
	public List<Integer> getSeenIds() {
		return seenIds;
	}

	@Override
	public Person.NotificationType getNotification() {
		return notification;
	}

	@Override
	public int getAge() {
		return age;
	}
	
	@Override
	public void setSeenId(int seenId) {
		seenIds.add(seenId);
	}
	
	@Override
	public void setUsedId(int usedId) {
		usedIds.add(usedId);
	}
	
	public boolean getTestsPositively() {
		return testsPositively;
	}
	
	@Override
	public void setNotification(NotificationType notification) {
		this.notification = notification;
	}
	
	@Override
	public void setTestsPositively() {
		testsPositively = true; 
		tracer.updateNotifications(seenIds); // update notification type of all indirect contacts, then direct contacts
		notification = NotificationType.NoNotification; // update self notification type to not be affected by being "smellt"	
	}
}
