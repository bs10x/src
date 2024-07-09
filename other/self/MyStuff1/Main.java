package bs10x.var;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
//import java.util.stream.Collector;
import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {

	public static void main(String[] args) {
		
		OptionalGenerics og = new OptionalGenerics();
//		System.out.println(OptionalGenerics.getInstanceCount());

		List<Optional<Integer>> listOfOptionalIntegers1 = Arrays.asList(
				Optional.empty(),
				Optional.empty(),
				Optional.empty(),
				Optional.of(4)
				);
		
		List<Optional<Integer>> listOfOptionalIntegers2 = Arrays.asList(
				Optional.of(1),
				Optional.empty(),
				Optional.empty(),
				Optional.empty(),
				Optional.of(2)	
				);
		
		int four = og.findFirstNonEmptyOptional(listOfOptionalIntegers1).orElse(null);
		System.out.println(four);
		
		int one = og.findFirstNonEmptyOptional(listOfOptionalIntegers2).orElse(null);
		System.out.println(one);
		
//		List<Integer> listOfIntegers = Arrays.asList(1, 2, 3);
//		og.printListOfThings(listOfIntegers);
		
		List<String> listOfStrings = Arrays.asList("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot");
		StreamLambdas sl = new StreamLambdas();
		sl.doStuff(listOfStrings);
		
		Product.makeProductList(7, 13).stream()
									  .filter(p -> p.isPresent())
									  .mapToDouble(o -> o.orElseThrow().getPrice())
									  .average()
									  .ifPresentOrElse(
											  avgPrice -> System.out.println("Average price: " + avgPrice),
											  () -> System.out.println("No products in list.")
									  );

//		List<Product> productListTEST = Product.makeProductList(7, 13).stream()
//																	.filter(p -> p.isPresent())
//																	.map(p -> p.orElseThrow())
//																	.collect(Collectors.toList());
//
//		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
//		numbers.stream()
//			   .mapToInt(Integer::intValue)
//			   .average()
//			   .ifPresent(avg -> System.out.println(avg));
		
		

		LinkedList<Integer> linkedListInteger0 = new LinkedList<>(new Integer[]{1, 2, 3});
//		System.out.println(linkedListInteger0.get(0));
		
		LinkedList<Integer> linkedListInteger1 = new LinkedList<>(Arrays.asList(1, 2, 3));
//		System.out.println(linkedListInteger1.get(0));
		
		LinkedList<Integer> linkedListInteger2 = new LinkedList<>();
		linkedListInteger2.add(3);
		linkedListInteger2.add(3);
		linkedListInteger2.add(3);
		linkedListInteger2.add(3);
//		System.out.println(linkedListInteger2.get(3));
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		Supplier<Integer> rng = () -> new Random().nextInt(100);
		Consumer<String> printer = s -> System.out.println(s);
		Predicate<String> hasMoreThanFiveChars = s -> s.length() > 5;
		
//		System.out.println(hasMoreThanFiveChars.test("abc"));

		
		
		
		
		
		
		
		
		
		Random rand = new Random();
		int[] numbers = new int[100000000];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = rand.nextInt(1000000);
		}
		System.out.println("Before: ");
//		printArray(numbers);
		mergeSort(numbers);
		System.out.println("\nAfter: ");
//		printArray(numbers);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	} // end of main method
	
	
	
	
	
	
	
	
	
	private static void mergeSort(int[] inputArray) {
		int inputLength = inputArray.length;
		
		if (inputLength < 2) {
			return;
		}
		
		int midIndex = inputLength / 2;
		int[] leftHalf = new int[midIndex];
		int[] rightHalf = new int[inputLength - midIndex];
		
		for (int i = 0; i < midIndex; i++) {
			leftHalf[i] = inputArray[i];
		}
		
		for (int i = midIndex; i < inputLength; i++) {
			rightHalf[i - midIndex] = inputArray[i];
		}
		
		mergeSort(leftHalf);
		mergeSort(rightHalf);
		
		merge(inputArray, leftHalf, rightHalf);
	}
	
	private static void merge(int[] inputArray, int[] leftHalf, int[] rightHalf) {
		int leftLength = leftHalf.length;
		int rightLength = rightHalf.length;
		
		int i = 0, j = 0, k = 0;
		
		while (i < leftLength && j < rightLength) {
			if (leftHalf[i] <= rightHalf[j]) {
				inputArray[k] = leftHalf[i];
				i++;
			}
			else {
				inputArray[k] = rightHalf[j];
				j++;
			}
			k++;	
		}
		
		while (i < leftLength) {
			inputArray[k] = leftHalf[i];
			i++;
			k++;
		}
		while (j < rightLength) {
			inputArray[k] = rightHalf[j];
			j++;
			k++;
		}
	}
	
	private static void printArray(int[] inputArray) {
		for (int i : inputArray) {
			System.out.println(i);
		}
	}
}
