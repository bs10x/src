package bs10x.var;

import java.util.List;
import java.util.stream.Collectors;

public class StreamLambdas {
		
	public void doStuff(List<String> listOfStrings) {
		System.out.println(listOfStrings.stream()
					 					.filter(s -> !s.toLowerCase().startsWith("a"))
					 					.map(s -> s.toUpperCase())
					 					.sorted((s1, s2) -> s2.length() - s1.length())
					 					.collect(Collectors.toList())
										);
	}
}
