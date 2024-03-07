import java.util.List;
import java.lang.Integer;

public interface Person {
	
	/** Die verschiedenen Warnstufen */
	public enum NotificationType {
		NoNotification,
		HighRiskNotification,
		LowRiskNotification
	}
	
	public void setTracer(ContactTracer tracer);
	
	public List<Integer> getUsedIds();
	
	public List<Integer> getSeenIds();
	
	public NotificationType getNotification();
	
	public int getAge();
		
	public void setSeenId(int seenID);
	
	public void setUsedId(int usedID);
	
	public boolean getTestsPositively();
	
	public void setNotification(NotificationType notification);
	
	public void setTestsPositively();
}

