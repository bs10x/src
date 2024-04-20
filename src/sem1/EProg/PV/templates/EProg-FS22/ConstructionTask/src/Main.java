import java.util.Set;

public class Main {
	
	public static void main(String[] args) {
		// Sie koennen die main Methode beliebig aendern.

		var company = new CCompany();
		CSite site = company.createCSite(Set.of(1), 1);
		
		Resource r1 = new IdResourceMain(1);
		Resource r2 = new IdResourceMain(2);
		
		System.out.println(site.resources().size());
		
		System.out.println(site.canAdd(r1));
		
		site.add(r1);
		
		System.out.println(site.resources().size());
		
		site.use(r1);
		
		System.out.println(site.resources().size());
		
		System.out.println(site.canAdd(r2));
	}

}

class IdResourceMain implements Resource {
	int typeId;
	IdResourceMain(int typeId) {
		this.typeId = typeId;
	} 
	
	@Override
	public int type() {
		return typeId;
	}
}

