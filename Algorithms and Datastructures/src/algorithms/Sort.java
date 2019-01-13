/*
* Author: svenp
* 13.01.2019
*/

package algorithms;

import java.util.Arrays;

public class Sort {
	
	/**
	 * Sorts a given array with bubblesort
	 */
	public static void BubbleSort(int[] array) {
		for(int i = 0; i< array.length; i++) { //Repeat n times
			for(int j = 0; j < array.length - (1+i); j++) { //"Bubble" biggest element of subarray [0: array.length - i] to top
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
	
		for(int i = 0; i< array.length; i++) { //Repeat n times
			int newKeyPos = i; //New position of the key
			while(newKeyPos>0 && array[newKeyPos-1] > array[newKeyPos]) { //Swap back until final destination is reached
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
	public static void HeapSort(int[] array) {
		
		for(int i = array.length / 2 - 1; i>= 0; i--) { //Build a maxHeap out of array
			restoreMaxHeapCondition(array, i, array.length);
		}
		
		for(int i = array.length -1; i>=0; i--) { //Extract root and rearrange heap n times
			swapKeys(array, 0, i);
			restoreMaxHeapCondition(array, 0, i);
		}
	}
	
	/**
	 * Sorts an array with merge sort
	 */
	public static int[] MergeSort(int[] array) {
		//Abort condition for recursion: If array is of size <= 1 it is already sorted
		if(array.length <= 1) {
			return array;
		}
		
		int[] array1 = Arrays.copyOfRange(array, 0, array.length/2); //Left subarray
		int[] array2 = Arrays.copyOfRange(array, array.length/2, array.length); //Right subarray

		return merge(MergeSort(array1), MergeSort(array2)); //Merge sorted left and right subarray
		
	}

	/**
	 * Sorts a given array with quick sort
	 * 
	 * !IT IS COMMON TO USE RANDOM PIVOT ELEMENTS. HOWEVER, TO CLARIFY THE PROCESS THIS IMPLEMENTATION
	 * ALWAYS CHOOSES THE RIGHTMOST KEY AS PIVOT!
	 */
	public static void QuickSort(int[] array) {
		recursiveQuickSort(array, 0, array.length-1); //Starts recursive quicksort on the array
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
		int placeHolder = array[index2]; //Store temp value
		array[index2] = array[index1]; //Swap 2 with 1
		array[index1] = placeHolder; //Set 1 to temp value
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
		
		while(array1Pointer < array1.length && array2Pointer < array2.length) { //While both arrays are not fully traversed
			
			if(array1[array1Pointer] <= array2[array2Pointer]) { //Choose element of array 1 if bigger than that of array 2
				mergedArray[index] = array1[array1Pointer];
				array1Pointer++;
			}
			else { //Choose element of array 2 if bigger than that of array 1
				mergedArray[index] = array2[array2Pointer]; 
				array2Pointer++;
			}
			
			index++; //Increase index of mergedArray
		}
		
		while(array1Pointer < array1.length) { //While array 1 is not fully traversed
			mergedArray[index] = array1[array1Pointer]; //Add all remaining elements
			array1Pointer++;
			index++;
		}
		
		while(array2Pointer < array2.length) { //While array 2 is not fully traversed
			mergedArray[index] = array2[array2Pointer]; //Add all remaining elements
			array2Pointer++;
			index++;
		}
		
		return mergedArray; //Return fully merged array
	}

	/**
	 * Quicksort recursion is called from public quicksort function
	 */
	private static void recursiveQuickSort(int[] array, int from, int to) {
		//Abort condition for recursion: Array with 1 element is sorted
		if(from >= to) {
			return;
		}
		
		//Pointers used to traverse array
		int leftPointer = from;
		int rightPointer = to - 1;
		
		int pivot = array[to]; //Pivot is last element
		
		do { //Repeat while left pointer has not overlapped with right pointer
			
			while(array[leftPointer] <= pivot && leftPointer < to) { //Move left pointer until pivot is reached or element greather than pivot was found
				leftPointer++;
			}
			
			while(array[rightPointer] > pivot && rightPointer > from) { //Move right pointer until first element is reached or element smaller than pivot was found
				rightPointer--;
			}
			
			if(leftPointer < rightPointer) { //If pointers did not overlap in the process, swap keys
				swapKeys(array, leftPointer, rightPointer);
			}
			
		} while(leftPointer < rightPointer);
		
		if(array[leftPointer] > pivot) { //If after movement of pointers left pointer is bigger than pivot
			swapKeys(array, leftPointer, to); //Swap
		}
		
		recursiveQuickSort(array, from, leftPointer - 1); //Sort subarray left from pivot
		recursiveQuickSort(array, leftPointer + 1, to); //Sort subarray right from pivot
		
	}
	
	/**
	 * Swaps keys in the array such that it fulfills the conditions of a max heap between from[inclusive] and to[exclusive]
	 */
	private static void restoreMaxHeapCondition(int[] array, int from, int to) {
		
		//Used to traverse tree
		int largestElement = from;
		int leftChild = 2*from + 1;
		int rightChild = 2*from + 2;
		
		
		if(leftChild < to && array[leftChild] > array[largestElement]) { //If left child is bigger than biggest element
			largestElement = leftChild; //Set as biggest element
		}
		
		if(rightChild < to && array[rightChild] > array[largestElement]) { //If right child is bigger than biggest element
			largestElement = rightChild; //Set as biggest element
		}
		
		if(largestElement != from) { //If root is not biggest
			swapKeys(array, from, largestElement); //Swap so it is
			restoreMaxHeapCondition(array, largestElement, to); //restore heap condition on broken subtree
		}
		
	}
}
