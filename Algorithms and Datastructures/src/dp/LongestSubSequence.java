/**
* Autor: Sven Pfiffner
* svenp@student.ethz.ch
* ------------------------------
* Bsc-Computer Science
* @ETH Zurich - 21.01.2019
*/

package dp;

import java.util.Arrays;

/**
 * An implementation of a dp solution to the longest ascending/descending subsequence in an array
 */
public class LongestSubSequence {

	/**
	 * Finds the longest ascending subsequence in a given array
	 */
	public static int[] longestAscending(int[] arr) {
		int[] lengthCache = new int[arr.length+1]; 		//This cache stores the last element of the ideal subsequence of length l [Used to find max l]
		int[] elementCache = new int[arr.length]; 		//This cache stores the precessor of each element in a subsequence [Used to backtrack solution]
		
		//Fill in base-cases
		lengthCache[0] = Integer.MIN_VALUE; 			//For l=0 there is no element so we set this to -infinity
		for(int i = 1; i<lengthCache.length; i++) { 	//All other values are not yet computed, so we set them to infinity
			lengthCache[i] = Integer.MAX_VALUE;
		}
		
		//Now fill the dp tables dynamically
		for(int i = 0; i<arr.length; i++) { 
						
			int lastSmaller	= lastElementSmallerThan(lengthCache, arr[i]);	//Find last element of lengthCache which is smaller than arr[i] [biggest subsequence that can be extended by arr[i]]
			lengthCache[lastSmaller+1] = arr[i];								//Extend this subsequence
			elementCache[i] = lengthCache[lastSmaller];							//Store precessor of added object in element cache
		}
		
		//Retrieve lenght of LAS from length cache
		int lengthOfLas = 0;
		for(int i = lengthCache.length-1; i>=0; i--) { 	//Go trough the length cache from end to start
			if(lengthCache[i] < Integer.MAX_VALUE) { 	//As soon as an element occurs which is smaller than infinity [there exists a subsequence with this length]
				lengthOfLas = i; 						//Retrive length [This is the longest subsequence found]
				break; 									//We dont need to look at the remaining entries of the cache
			}
		}
		
		
		//Retrieve LAS from element cache by backtracking
		int[] las = new int[lengthOfLas];					//This will contain the LAS
		int index = -1;										//This stores indices that we need within the loop
		int lastElement = lengthCache[lengthOfLas];			//This is the last element of the LAS
		las[lengthOfLas-1] = lastElement;					//So we store it
		
		
		for(int l = lengthOfLas-2; l>=0 ; l--) {			//We know how many elements the LAS has, we now find them one by one with backtracking
			for(int i = arr.length-1; i >=0; i--) {				//First, we search index of the last non considered element of LAS in arr [traverse from the right since we search for the last element]
				if(arr[i] == lastElement) {						//When found
					index = i;									//Store
					break;										//Exit loop
				}
			}
			lastElement = elementCache[index];				//This is the last non considered element of the LAS
			las[l] = lastElement;							//So we store it
		}
		
		
		return las; 										//Return solution
	}
	
	/**
	 * Finds the longest descending subsequence in a given array
	 */
	public static int[] longestDescending(int[] arr) {
		/*
		 * This is basically the inverse of the longest ascending subsequence problem. If we'd only be interested in this, we could
		 * modify the longestAscending algorithm and use the same logic. However, we can re-use the code by inverting arr and finding
		 * the longest ascending subsequence, then all we have to do is to invert the found LAS, This adds a total of O(n) to the
		 * total runtime of longestAscending, which runs in O(n log(n)), so this solution has no impact on the runtime
		 */
		
		int[] localArray = new int[arr.length]; 			//We do this so we dont mess up the array which was given as an argument
		System.arraycopy(arr, 0, localArray, 0, arr.length);
		
		for(int i = 0; i < localArray.length / 2; i++) { 	//We invert the array by swapping elements up until the midpoint
		    int temp = localArray[i];
		    int newPos = localArray.length -i -1;
		    localArray[i] = localArray[newPos];
		    localArray[newPos] = temp;
		}
		
		int[] invertedSol = longestAscending(localArray); 	//This is the LAS
		
		for(int i = 0; i < invertedSol.length / 2; i++) { 	//We invert the LAS by swapping elements up until the midpoint
		    int temp = invertedSol[i];
		    int newPos = invertedSol.length -i -1;
		    invertedSol[i] = invertedSol[newPos];
		    invertedSol[newPos] = temp;
		}
		
		return invertedSol; 								//Now we have the LDS which we can return
	}
	
	/**
	 * -----------------------------------
	 * INTERNAL USE ONLY
	 * -----------------------------------
	 */
	
	/**
	 * Finds the position of the last element in a given (ordered) array which is smaller than a given reference value
	 * using a modified version of binary-search
	 * Returns 0 if no element in the array is smaller than referenceVal
	 */
	public static int lastElementSmallerThan(int[] arr, int referenceVal) {
		
		int leftPointer = 0; 				//Points to the left border of subarray
		int rightPointer = arr.length-1; 	//Points to the right border of subarray
		int cutPos = 0;						//Points to the current cut position
		
		while(true) {
			cutPos = (leftPointer+rightPointer) /2; 	//Find cut position
			if(cutPos == rightPointer) { 				//If cut position is at right border
				if(arr[cutPos] < referenceVal) {		//And element at cut position is smaller than reference value
					return cutPos; 						//Cut position is answer
				} else { 								//If it is bigger
					return 0; 							//There is no answer
				}
			}
			
			if(arr[cutPos] >= referenceVal) { 			//If element at cut position is not smaller than reference value
				rightPointer = cutPos;					//The element must be left of it
				continue;								//Continue loop
			}
			
			if(arr[cutPos] < referenceVal) {			//If element at cut position is smaller than reference value
				if(arr[cutPos+1] >= referenceVal) {		//And next element is not
					return cutPos;						//Cut position is answer
				} else {								//If it is also smaller
					leftPointer = cutPos;				//The element must be right of it
					continue;							//Continue loop
				}
			}
		}
	}
}
