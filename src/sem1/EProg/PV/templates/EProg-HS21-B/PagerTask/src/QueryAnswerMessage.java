
public class QueryAnswerMessage implements Message {

	private String destination;
	private int numberOfMessages;
	
	public QueryAnswerMessage(String destination, int numberOfMessages) {
		this.destination = destination;
		this.numberOfMessages = numberOfMessages;
	}

	@Override
	public String getPayload() {
		return "Benutzer \"" + destination + "\" hat " + numberOfMessages + " Nachrichten an Sie verschickt.";
	}
	
	@Override 
	public boolean equals(Object other) {
		if (other instanceof QueryAnswerMessage) {
			QueryAnswerMessage otherAnswer = (QueryAnswerMessage) other;
			return destination.equals(otherAnswer.destination) && numberOfMessages == otherAnswer.numberOfMessages;
		}
		return false;
	}
	
	
}
