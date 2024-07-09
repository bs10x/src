import java.util.HashSet;	// is this allowed
import java.util.Set;	// is this allowed

public class Item {
	
	private Level level;
	private int id;
	private int age;
	private int health;
	
	private Set<Item> link;
	private boolean isDeclass;
	private Level declassItemLevel;

	public Item(Level level, int id, int age, int health) {
		this.level = level;
		this.id = id;
		this.age = age;
		this.health = health;
		this.link = new HashSet<>();
		link.add(this);
		isDeclass = false;
		declassItemLevel = null;
	}

	public Level getLevel() { return level; }
	public int getID() { return id; }
	public int getAge() { return age; }
	public int getHealth() { return health; }
	public void setHealth(int newHealth) { health = newHealth; }
	public boolean isDeclass() { return isDeclass; }
	public void makeDeclass() { isDeclass = true; }
	public void setDeclassItemLevel(Level newDeclassItemLevel) { declassItemLevel = newDeclassItemLevel; }
	public Level getDeclassItemLevel() { return declassItemLevel; }
	
	public boolean isWeakerThan(Level argLevel) {
		boolean isWeaker = true;
		for (int other : argLevel.getPoints()) {
			for (int self : this.getLevel().getPoints()) {
				if (other > self) isWeaker = false;
			}
		}
		return isWeaker;
	}
	
	public Set<Item> getLink() { return link; }
	public void setLink(Set<Item> newLink) { link = newLink; }
	
}
