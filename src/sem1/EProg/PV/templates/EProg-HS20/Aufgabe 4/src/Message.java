
import java.util.List;
import java.util.Set;

// Aendern Sie diese Datei nicht!

public interface Message {
	/** Gibt den Pfad der Nachricht zurueck. */
	List<Set<Integer>> getPath();
	/** Gibt eine Kopie der Nachricht zurueck, welche 'path' als neuen Pfad hat. */
	Message withPath(List<Set<Integer>> path);
}
