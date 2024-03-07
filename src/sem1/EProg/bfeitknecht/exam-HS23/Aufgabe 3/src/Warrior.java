
public class Warrior extends Human {
	
	public Warrior(int health, int position) {
		super(health, position);
		this.summonDelay = 1;
	}
	
	@Override
	public void scheduleAction(Action action) {
		if (action.toString().equals("SUMMON")) {
			this.game.addAction(this.game.turn + this.summonDelay, new ActionSource(Action.WARRIOR_SUMMON, this));
//			System.out.println("attack added");
		}
		else if (action.toString().equals("ATTACK")) {
			this.game.addAction(this.game.turn, new ActionSource(Action.WARRIOR_ATTACK, this));
			
		}
		
	}
}
