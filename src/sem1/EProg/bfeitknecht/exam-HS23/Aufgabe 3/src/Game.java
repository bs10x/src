import java.util.ArrayList;


public class Game {
	
	public ArrayList<Human> participants;
	public ArrayList<ArrayList<ActionSource>> actions;
	public int turn;
	
	
	public Game() {
		this.participants  = new ArrayList<>();
		this.actions = new ArrayList<>();
		actions.add(new ArrayList<>());
		this.turn = 0;
	}
	
	void advanceTurn() {
//		printActions();
		for (ActionSource a : actions.get(this.turn)) {
			processAction(a);
		}
		actions.add(new ArrayList<>());
		turn++;

	}
	
	public void printActions() {
		for (ArrayList<ActionSource> aL : actions) {
			for (ActionSource a : aL) {
				System.out.println(a.action.toString());
				System.out.println(a.source);
				System.out.println();
			}
		}
	}
	
	public void processAction(ActionSource a) {
		if (a.action.toString().equals("WARRIOR_ATTACK")); {
			
			for (Human p : a.source.game.participants) {
				if (p.getPosition() + 1 == a.source.position || p.getPosition() - 1 == a.source.position) p.health -= 10;
			}
		}
		if (a.action.toString().equals("WARRIOR_SUMMON")); {
//			System.out.println("test");
			a.source.health -= 5;
		}
//		if (a.action.toString().equals("CLERIC_ATTACK")); {
//			for (Human p : a.source.game.participants) {
//				if (p.getPosition() + 1 == a.source.position || p.getPosition() - 1 == a.source.position) p.health -= 3;
////				System.out.println("c A");
//			}
//		}
//		if (a.action.toString().equals("CLERIC_SUMMON")); {
////			System.out.println("test");
////			a.source.health -= 5;
//		}
		
		
	}
	
	public void addAction(int turn, ActionSource a) {
		if (actions.size() <= turn) {
			actions.add(turn, new ArrayList<>());
			actions.get(turn).add(a);
//			System.out.println("test3");
		}
		else actions.get(turn).add(a);
	}
	
	public void setParticipant(Human participant) {
		participant.setGame(this);
		participants.add(participant);
	}
	
	Human createHuman(int health, int position) {
		Human human = new Human(health, position);
		setParticipant(human);
		return human;
	}
	
	Human createWarrior(int health, int position) {
		Warrior warrior = new Warrior(health, position);
		setParticipant(warrior);
		return warrior;
	}
	
	Human createCleric(int health, int position) {
		Cleric cleric = new Cleric(health, position);
		setParticipant(cleric);
		return cleric;
	}
	
	Human createGeneral(int health, int position) {
		General general = new General(health, position);
		setParticipant(general);
		return general;
	}
}
