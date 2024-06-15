
public class Main {
	
	public static void main(String[] args) {

		Hospital h = new Hospital();
		Pager anna = h.createPager("normal");
		anna.register("Anna");
		Pager bernd = h.createPager("normal");
		bernd.register("Bernd");
		
		anna.command("Bernd", new TextMessage("Hello World"));
		bernd.command("Max", new TextMessage("Max?"));
		bernd.command("Anna", new TextMessage("Anna!"));

		bernd.printInbox();	
		anna.printInbox();
		
		
	}

}
