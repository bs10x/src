public class ItemFactory {
	private Database db;
	
	public ItemFactory(Database db) {
		this.db = db;
		
	}

	public Item createItem(Level level, int id, int age, int health) { 
		return new Item(level, id, age, health); 
	}

	public Item createDeclass(Level level, int id, int targetId) {
		boolean noTargetItem = !db.targetItemExists(targetId);
		boolean related1stDeg = db.getItemByID(targetId).getLevel().isRelated(level);
		boolean related2ndDeg = db.getItemByID(targetId).isDeclass()
							 && db.getItemByID(targetId).getDeclassItemLevel().isRelated(level);
		if (noTargetItem || related1stDeg || related2ndDeg) throw new IllegalArgumentException();
		
		Item targetItem = db.getItemByID(targetId);
		int targetAge = targetItem.getAge();
		int targetHealth = targetItem.getHealth();
		Item declassItem = createItem(level, id, targetAge, targetHealth);
		declassItem.makeDeclass();
		declassItem.setDeclassItemLevel(targetItem.getLevel());
		return declassItem;
	}
}
