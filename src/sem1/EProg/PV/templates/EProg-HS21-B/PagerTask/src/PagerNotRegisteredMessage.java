
public class PagerNotRegisteredMessage implements Message {
	
	private String destination;
	
	public PagerNotRegisteredMessage(String destination) {
		this.destination = destination;
	}

	@Override
	public String getPayload() {
		return "Es ist kein Pager auf den Namen \"" + destination + "\" registriert.";
	}
	
	@Override 
	public boolean equals(Object other) {
		if (other instanceof PagerNotRegisteredMessage) {
			PagerNotRegisteredMessage otherNotFound = (PagerNotRegisteredMessage) other;
			return destination.equals(otherNotFound.destination);
		}
		return false;
	}
	
	public String toString() { return "PagerNotRegisteredMessage, destination: " + destination; }
} 
