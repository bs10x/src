package bs10x.var;

import java.util.Random;

public class Quicksort {

	public static void main(String[] args) {
		
		int[] nums = randomArray(100000000, 100000000);
		
		System.out.println("Before: \n");
		//printArray(nums);
		
		quicksort(nums);
		System.out.println("\nAfter: \n");
		//printArray(nums);
		
		
		
		
	}

	public static void printArray(int[] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.println(A[i]);
		}
	}
	
	public static int[] randomArray(int size, int bound) {
		Random rng = new Random();
		int[] randomArray = new int[size];
		for (int i = 0; i < randomArray.length; i++) {
			randomArray[i] = rng.nextInt(bound);
		}
		return randomArray;
	}
	
	
	
	
	
	
	
	
	
	public static void quicksort(int[] array) {
		quicksort(array, 0, array.length - 1);
	}
	
	public static void quicksort(int[] array, int low, int high) {
		
		if (low >= high) return;
		
		//int betterPivot = array[low] > array[high] ? array
		
		int pivot = array[high];
		
		int leftPointer = partition(array, low, high, pivot);
		
		quicksort(array, low, leftPointer - 1);
		quicksort(array, leftPointer + 1, high);
		
	}

	private static int partition(int[] array, int low, int high, int pivot) {
		int leftPointer = low; 
		int rightPointer = high;
		
		while (leftPointer < rightPointer) {
			
			while (array[leftPointer] <= pivot && leftPointer < rightPointer) {
				leftPointer++;
			}
			
			while (array[rightPointer] >= pivot && leftPointer < rightPointer) {
				rightPointer--;
			}
			
			swap(array, leftPointer, rightPointer);
		}
		swap(array, leftPointer, high);
		return leftPointer;
	}
	
	private static void swap(int[] array, int index1, int index2) {
		int tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
	}
}
