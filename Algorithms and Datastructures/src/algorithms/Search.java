package algorithms;

import java.util.Arrays;

public class Search {
	
	/**
	 * Performs a linear-search on a given array.
	 * @return Position of found element; -1 if element was not found
	 */
	public static int LinearSearch(int[] array, int keyToSearch) {
		for(int i = 0; i < array.length; i++) { //Foreach position in array
			if(array[i] == keyToSearch) { //Check if key is at position
				return i; //Key was found at position i
			}
		}
		return -1; //Key was not found, return -1
	}
	
	/**
	 * Performs a binary search on a given array.
	 * @return Position of found element; -1 if element was not found
	 */
	public static int BinarySearch(int[] array, int keyToSearch) {
		//Set initial pointers
		int leftPointer = 0;
		int rightPointer = array.length-1;
		int cutPosition = rightPointer / 2;
		
		//Repeatedly cut the sequence
		while(true) {
			if(array[cutPosition] > keyToSearch) { //Move right pointer if needed
				rightPointer = cutPosition;
			}
			else if(array[cutPosition] < keyToSearch) { //Move left pointer if needed
				leftPointer = cutPosition;
			}
			else if(array[cutPosition] == keyToSearch){ //If key is at cut position
				return cutPosition; //return cutposition
			}
			
			if(rightPointer - leftPointer == 1) { //As soon as no cuts can be made anymore
				if (array[leftPointer] == keyToSearch) { //If key is at left pointer
					return leftPointer; //return left pointer
				}
				else if (array[rightPointer] == keyToSearch) { //If key is at right pointer
					return rightPointer; //return right pointer
				}
				else {
					return -1; //Key not found, return 0
				}
			}
			
			cutPosition = (leftPointer + rightPointer) / 2; //Update cut position
		}
	}
	
	/**
	 * Performs an exponential search on a given array.
	 * @return Position of found element; -1 if element was not found
	 */
	public static int ExponentialSearch(int[] array, int keyToSearch) {
		//Set initial pointers
		int leftPointer = 0;
		int rightPointer = 0;
		int index = 1;
		
		while(true) {
			rightPointer = Math.min(array.length-1, (int) Math.pow(2, index)); //Set right pointer to 2 power of index
			
			if(array[rightPointer] > keyToSearch) { //If key to search lies in between
				int solutionInSubArray = BinarySearch(Arrays.copyOfRange(array, leftPointer, rightPointer), keyToSearch);
				
				if(solutionInSubArray == -1) { //No Solution was found in sub array
					return -1;
				}
				else {
					return leftPointer + solutionInSubArray;
				}
			} 
			else if(array[rightPointer] < keyToSearch) { //if not
				leftPointer = rightPointer;
				index ++;
			}
			else {
				return rightPointer;
			}
		}
	}
}
