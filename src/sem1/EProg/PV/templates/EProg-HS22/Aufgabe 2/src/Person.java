import java.util.List;
import java.lang.Integer;

public interface Person {
	/** Die verschiedenen Warnstufen */
	public enum NotificationType {
		NoNotification,
		HighRiskNotification,
		LowRiskNotification
	}
	
	/**
	 *  Diese Methode gibt die Liste aller IDs zurück, die für diese Person als 
	 *  frische ID verwendet wurden, um eine Begegnung zu protokollieren. Nach Hinzufügen 
	 *  einer ID in diese Liste, muss die selbe ID in die jeweilige Person.getSeenIds()-Liste 
	 *  des Gegenübers eingetragen sein.
	 */
	public List<Integer> getUsedIds();
	
	/** 
	 * Diese Methode gibt die Liste aller IDs zurück, die 
	 * diese Person als die frische ID des jeweiligen Gegenübers bei einer Begegnung 
	 * protokolliert hat. Nach Hinzufügen einer ID in diese Liste, muss die selbe ID in 
	 * die jeweilige Person.getUsedIds()-Liste des Gegenübers eingetragen sein.
	 */
	public List<Integer> getSeenIds();
	
	/** Gibt die aktuelle Art von Benachrichtigung dieser Person zurück. */
	public NotificationType getNotification();
	
	/** Markiert diese Person als positiv getestet und benachrichtigt alle relevanten Kontakte. */
	public void setTestsPositively();
}
