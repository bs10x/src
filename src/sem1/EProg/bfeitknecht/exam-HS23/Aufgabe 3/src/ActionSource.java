import java.util.ArrayList;

public class ActionSource {
	public Action action;
	public Human source;
	public ArrayList<Human> snapshot;
	
	public ActionSource(Action action, Human source) {
		this.action = action;
		this.source = source;
		
//		this.snapshot = (ArrayList<Human>) source.game.participants.clone();
		
	}
	
	

}
