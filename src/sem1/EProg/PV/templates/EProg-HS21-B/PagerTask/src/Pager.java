import java.util.List;

public interface Pager {

	public void register(String name);
	
	public List<Message> inbox(); 
	
	public void command(String destination, Message msg);
	
	public String toString();

	public void printInbox();
}

