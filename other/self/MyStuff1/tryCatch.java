package bs10x.var;

public class tryCatch {

	public static void main(String[] args) {
		
		try {
			
		}
		
		catch (Exception e) {
			
		}
		// TODO Auto-generated method stub

	}

}



class myException extends Exception {
	private static final long serialVersionUID = 1L;
	String info = "this is \"myException\". something went wrong.";

	@Override
	public String toString() {
		return "myException [info=" + info + "]";
	}
	
}
