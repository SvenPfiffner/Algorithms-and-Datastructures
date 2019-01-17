/**
* Autor: Sven Pfiffner
* svenp@student.ethz.ch
* ------------------------------
* Bsc-Computer Science
* @ETH Zurich - 17.01.2019
*/

package dp;

/**
 * We define a subarray as a contiguous subsequence in an array.
 * Given an array, find the maximum possible sum among all nonempty subarrays.
 * SOURCE: 
 */
public class MaximumSubarray {
	
	/**
	 * Bottom-Up solution finds max subarray through iteration over cache
	 */
	public static int findMaxSub(int[] array) {
		int[] cache = new int[array.length]; 	//Create Cache
		cache[0] = array[0]; 					//Base case. Subarray 0-0 has max sum = array[0]
		
		for(int i = 1; i<array.length; i++) {	//Fill cache
			cache[i] = Math.max(cache[i-1]+ array[i], array[i]);
		}
		
		return cache[array.length-1]; 			//Solution is at end of cache
	}

}
