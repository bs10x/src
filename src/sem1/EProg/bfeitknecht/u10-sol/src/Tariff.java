
public class Tariff {
	private int[] definition;
	
	public Tariff(int[] definition) {
		this.definition = definition;
	}
	
	public int calculateDebt(int consumption) {
		long sum = 0;
		int remainder = consumption;
		for (int i = 0; i+1 < definition.length && remainder > 0; i += 2) {
			int applied = Math.min(definition[i], remainder);
			sum += applied * definition[i+1];
			remainder -= applied;
		}
		return (int) Math.round(sum / 100.0);
	}
}
