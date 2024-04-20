
public class QueryMessage implements Message {
	
	public QueryMessage() { }

	@Override
	public String getPayload() {
		return "query";
	}
}
