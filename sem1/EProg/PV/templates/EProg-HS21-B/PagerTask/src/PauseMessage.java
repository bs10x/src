
public class PauseMessage implements Message {
	
	public PauseMessage() { }

	@Override
	public String getPayload() {
		return "pause";
	}
}
