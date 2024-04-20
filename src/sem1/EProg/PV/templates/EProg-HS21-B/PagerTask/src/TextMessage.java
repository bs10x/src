
public class TextMessage implements Message {
	
	private String payload;
	
	public TextMessage(String payload) {
		this.payload = payload;
	}

	@Override
	public String getPayload() {
		return payload;
	}
	
	@Override 
	public boolean equals(Object other) {
		if (other instanceof TextMessage) {
			TextMessage otherText = (TextMessage) other;
			return payload.equals(otherText.payload);
		} 
		return false;
	}
	
	
	
	
	public String toString() { return "TextMessage, payload: " + payload; }
}
