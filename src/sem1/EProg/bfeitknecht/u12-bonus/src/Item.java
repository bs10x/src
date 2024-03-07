
public class Item {
	
	private Level level;
	private int id;
	private int age;
	private int health;

	public Item(Level level, int id, int age, int health) {
		this.level = level;
		this.id = id;
		this.age = age;
		this.health = health;
	}

	public Level getLevel() { return level; }
	public int getID() { return id; }
	public int getAge() { return age; }
	public int getHealth() { return health; }

	public void setHealth(int newHealth) { health = newHealth; }
}
