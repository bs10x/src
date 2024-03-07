
public class Cleric extends Human {
	public Cleric(int health, int position) {
		super(health, position);
		this.summonDelay = 2;
	}
	
	@Override
	public void scheduleAction(Action action) {
//		if (action.toString().equals("SUMMON") && this instanceof Cleric) {
//			this.game.addAction(this.game.turn + this.summonDelay, new ActionSource(Action.CLERIC_SUMMON, this));
//		}
//		else if (action.toString().equals("ATTACK") && this instanceof Cleric) {
//			for (Human p : this.game.participants) {
//				if (p.getPosition() + 1 == this.position || p.getPosition() - 1 == this.position) p.health -= 3;
//			}
//		}
	}
}
