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
	 * Sorts an array with merge sort
	 */
	public static int[] MergeSort(int[] array) {
		
		if(array.length <= 1) { //If array is of size <= 1 it is already sorted
			return array;
		}
		
		int[] array1 = Arrays.copyOfRange(array, 0, array.length/2);
		int[] array2 = Arrays.copyOfRange(array, array.length/2, array.length);

		return merge(MergeSort(array1), MergeSort(array2));
		
	}
	N
	/**
	 * Sorts a given array with quick sort
	 */
	public static int[] QuickSort(int[] array) {
		return null;
	}
	
	/*
	 * ---------------------------------------------
	 * INTERNAL USE ONLY
	 * ---------------------------------------------
	 */
	
	/**
	 * Swaps keys at index 1 and index 2 in a given array
	 */
	private static void swapKeys(int[] array, int index1, int index2) {
		int placeHolder = array[index2];
		array[index2] = array[index1];
		array[index1] = placeHolder;
	}
	
	/**
	 * Merges two sorted arrays into a new sorted array
	 */
	private static int[] merge(int[] array1, int[] array2) {
		int[] mergedArray = new int[array1.length + array2.length]; //Stores merged array
		
		//Used to go trough each array
		int array1Pointer = 0;
		int array2Pointer = 0;
		int index = 0;
		
		while(array1Pointer < array1.length && array2Pointer < array2.length) {
			
			if(array1[array1Pointer] <= array2[array2Pointer]) {
				mergedArray[index] = array1[array1Pointer];
				array1Pointer++;
			}
			else {
				mergedArray[index] = array2[array2Pointer];
				array2Pointer++;
			}
			
			index++;
		}
		
		while(array1Pointer < array1.length) {
			mergedArray[index] = array1[array1Pointer];
			array1Pointer++;
			index++;
		}
		
		while(array2Pointer < array2.length) {
			mergedArray[index] = array2[array2Pointer];
			array2Pointer++;
			index++;
		}
		
		return mergedArray;
	}
}
