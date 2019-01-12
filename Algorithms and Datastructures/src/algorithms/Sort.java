package algorithms;

import java.util.Arrays;

public class Sort {
	
	/**
	 * Sorts a given array with bubblesort
	 */
	public static void BubbleSort(int[] array) {
		for(int i = 0; i< array.length; i++) {
			for(int j = 0; j < array.length - (1+i); j++) {
				if(array[j] > array[j+1]) {
					swapKeys(array, j, j+1);
				}
			}
		}
	}
	
	/**
	 * Sorts a given array with insertion sort
	 */
	public static void InsertionSort(int[] array) {
	
		for(int i = 0; i< array.length; i++) {
			int newKeyPos = i;
			while(newKeyPos>0 && array[newKeyPos-1] > array[newKeyPos]) {
				swapKeys(array, newKeyPos, newKeyPos-1);
				newKeyPos --;
			}
		}
	}
	
	/**
	 * Sorts a given array with selection sort
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
	 */
	public static int[] HeapSort(int[] array) {
		return null;
	}
	
	/**
	 * Sorts a given array with merge sort
	 */
	public static int[] MergeSort(int[] array) {
		return null;
	}
	
	/**
	 * Sorts a given array with quick sort
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
