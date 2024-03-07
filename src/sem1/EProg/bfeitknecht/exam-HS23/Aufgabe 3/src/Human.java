
public class Human {
	
	public int health, position;
	public Game game;
	public int summonDelay;
	
	public Human(int health, int position) {
		this.health = health;
		this.position = position;
		
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getPosition() {
		return position;
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	public void scheduleAction(Action action) {
		this.game.addAction(this.game.turn, new ActionSource(action, this));
	}
}
