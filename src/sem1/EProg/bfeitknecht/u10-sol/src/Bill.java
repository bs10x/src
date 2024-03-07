
public class Bill {

	private int customerId;
	private int debt;
	
	public Bill(int customerId, int debt) {
		this.customerId = customerId;
		this.debt = debt;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public int getDebt() {
		return debt;
	}
}
