import java.util.ArrayList;
import java.util.List;

public class PagerObject implements Pager {
	Hospital h;
	String name;
	String role;
	
	List<Message> inbox;

	int slowCounter;
	ArrayList<Command> slowCache;	
	
	boolean paused;
	
	public PagerObject(Hospital h) {
		this.h = h;
		this.inbox = new ArrayList<Message>();
		this.paused = false;
	}
	
	public void assignRole(String role) {
		this.role = role;
		if (role.equals("slow")) {
			this.slowCounter = 0;
			this.slowCache = new ArrayList<>();
		}
	}
	public String getName() { return name; }

	@Override
	public void register(String name) {
		if (h.alreadyRegisteredName(name)) throw new IllegalArgumentException();
		h.registeredNames.add(name);
		this.name = name;
	}

	@Override
	public List<Message> inbox() {
		return inbox;
	}
	
	public void executeCommand(Command cmd) {
		if (!h.alreadyRegisteredName(cmd.destination)) inbox.add(new PagerNotRegisteredMessage(cmd.destination));
		else h.getPagerByName(cmd.destination).addMessage(cmd.msg);
	}
	
	public void addMessage(Message msg) {
		inbox.add(msg);
	}

	@Override
	public void command(String destination, Message msg) {
		if (role.equals("normal") && !paused) {
			executeCommand(new Command(destination, msg));
		}
		if (role.equals("slow") && !paused) {
			if (slowCounter == 2) {
				slowCounter = 0;
				slowCache.add(new Command(destination, msg));
				for (Command c : slowCache) executeCommand(c);
			}
			else {
				slowCounter++;
				slowCache.add(new Command(destination, msg));
			}
		}
		if (role.equals("admin") && !paused) {
			if (msg instanceof TextMessage) for (PagerObject p : h.pagerObjects) p.addMessage(msg);
			if (msg instanceof PauseMessage) {
				/*
				 * WIP
				 * map of names and boolean (paused)?
				 */
			}
		}
		
	}
	
	public void printInbox() {
		for (Message m : inbox) System.out.println(m);
		
	}
}