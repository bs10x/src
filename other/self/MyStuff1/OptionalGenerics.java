package bs10x.var;
import java.util.List;
import java.util.Optional;

public class OptionalGenerics {
	protected static int instanceCount = 0;
	
	OptionalGenerics() {
		instanceCount++;
	}
	
	public static int getInstanceCount() {
		return instanceCount;
	}
		
	public <T> Optional<T> findFirstNonEmptyOptional(List<Optional<T>> listOfOptionals) {
		return listOfOptionals.stream()
							  .filter(o -> o.isPresent())
					   		  .findFirst()
							  .orElse(Optional.empty());	
	}
	
	public void printListOfThings(List<?> listOfThings) {
		for (var thing : listOfThings) {
			System.out.println(thing);
		}
	}
}

