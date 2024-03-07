import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
				
		Database database = new Database();
		ItemFactory factory = database.getItemFactory();
		
		database.add(factory.createItem(new Level(Set.of(18,19)),     2,    55,   64)); //
		database.add(factory.createItem(new Level(Set.of(12)),        3,    74,   17));
		database.add(factory.createItem(new Level(Set.of(1,12,2,15)), 5,    51,   39));
		database.add(factory.createItem(new Level(Set.of(1,18,5)),    7,    48,   32)); //
		database.add(factory.createItem(new Level(Set.of(4,5)),       11,   49,   49));
		database.add(factory.createItem(new Level(Set.of(6,8,9)),     13,   51,   28));		
		database.add(factory.createItem(new Level(Set.of(28)),        17,   38,   29)); //
		database.add(factory.createItem(new Level(Set.of(22,23)),     19,   38,   27)); //
		database.add(factory.createItem(new Level(Set.of(1,2,16)),    23,   23,   64)); 
		database.add(factory.createItem(new Level(Set.of(4,6,7,9)),   34,   32,   75));
		database.add(factory.createItem(new Level(Set.of(3,5)),       45,   34,   84));
		database.add(factory.createItem(new Level(Set.of(100,200)),   56,   61,   17)); //
		
		Map<Integer, Integer> res = database.summary(new Level(Set.of(10,17,2,12)));
		
		System.out.println(res.toString());
	}

}
