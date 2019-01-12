package algorithms;

public class Sort {
	
	/**
	 * Sorts a given array with bubblesort
	 * @return array sorted in ascending order
	 */
	public static int[] BubbleSort(int[] array) {
		return null;
	}
	
	/**
	 * Sorts a given array with insertion sort
	 * @return array sorted in ascending order
	 */
	public static int[] InsertionSort(int[] array) {
		return null;
	}
	
	/**
	 * Sorts a given array with selection sort
	 * @return array sorted in ascending order
	 */
	public static void SelectionSort(int[] array) {
		for(int i = 0; i<array.length; i++) { //Go trough array
			int smallest = i;
			for(int j = i; j<array.length; j++) { //Find smallest in remaining subarray
				if(array[j] < array[smallest]) {
					smallest = j;
				}
			}
			
			swapKeys(array, smallest, i); //Swap keys so smallest of subarray is at beginning
		}
	}
	
	/**
	 * Sorts a given array with heap sort
	 * @return array sorted in ascending order
	 */
	public static int[] HeapSort(int[] array) {
		return null;
	}
	
	/**
	 * Sorts a given array with merge sort
	 * @return array sorted in ascending order
	 */
	public static int[] MergeSort(int[] array) {
		return null;
	}
	
	/**
	 * Sorts a given array with quick sort
	 * @return array sorted in ascending order
	 */
	public static int[] QuickSort(int[] array) {
		return null;
	}
	
	/**
	 * Swaps keys at index 1 and index 2 in a given array
	 */
	private static void swapKeys(int[] array, int index1, int index2) {
		int placeHolder = array[index2];
		array[index2] = array[index1];
		array[index1] = placeHolder;
	}
}
