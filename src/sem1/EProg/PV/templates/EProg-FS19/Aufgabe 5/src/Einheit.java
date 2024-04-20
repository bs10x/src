import java.util.ArrayList;
import java.util.List;

/**
 * Eine Einheit eines {@link Rezept}s. Eine Einheit besteht aus
 * mehreren Arbeitsschritten und hat möglicherweise einige
 * Vorbedingungen, d.h. eine Liste von anderen Einheiten, welche
 * vor dieser Einheit erledigt werden müssen.
 * 
 * Diese Klasse darf nicht verändert werden.
 */
public class Einheit {

	List<String> schritte = new ArrayList<String>();
	List<Einheit> vorbedingungen = new ArrayList<Einheit>();

}
