/**
* Autor: Sven Pfiffner
* svenp@student.ethz.ch
* ------------------------------
* Bsc-Computer Science
* @ETH Zurich - 15.01.2019
*/

package dp;

/**
 * This class implements some dynamic programming solutions for computing fibonacci numbers
 */
public class dpFibonacci {

	/**
	 * Computes the i-th fibonacci number using a trivial recursive solution
	 * NOTE: Very slow, use for comparison purposes only!
	 */
	public static int trivialFibonacci(int i) {
		
		//Abort condition for recursion: Fib(1) and Fib(2) is 1
		if(i == 1 || i == 2) {
			return 1;
		}
		
		return trivialFibonacci(i-1) + trivialFibonacci(i-2);
	}
	
	/**
	 * Computes the i-th fibonacci number using a dynamic-cache (Top->Down) solution
	 */
	public static int topDownFibonacci(int i) {
		return topDownFibonacci(i, new int[i+1]);
	}
	
	/**
	 * Computes the i-th fibonacci number using a dynamic-cache (Bottom->Up) solution
	 */
	public static int bottomUpFibonacci(int i) {
		int[] memo = new int[i+1]; //Cache stores computed fib numbers
		
		memo[1] = 1;	//Fib(1) is trivial
		memo[2] = 1;	//Fib(2) is trivial
		
		for(int j = 3; j<= i; j++) { 			//Go trough all fib numbers up to i
			memo[j] = memo[j-1] + memo[j-2]; 	//Compute and store fib number with cache data
		}
		
		return memo[i]; //Desired fib number is last element of cache
		
	}
	
	/**
	 * --------------------------------
	 * INTERNAL USE ONLY
	 * --------------------------------
	 */
	
	/**
	 * Recursive topDown Fibonacci
	 */
	private static int topDownFibonacci(int i, int[] memo) {
		
		//Abort condition for recursion: Fib(1) and Fib(2) is 1
		if(i == 1 || i == 2) {
			return 1;
		}
		
		int fib1;	//Stores first fib number
		int fib2;	//Stores second fib number
		int result; //Stores result
		
		if(memo[i-1] != 0) { 						//Previous fib number is memoized
			fib1 = memo[i-1]; 						//Use memoized number
		}
		else { 										//Previous fib number is not memoized
			fib1 = topDownFibonacci(i-1, memo); 	//Calculate previous fib number
		}
		
		if(memo[i-2] != 0) { 						//Previous fib number is memoized
			fib2 = memo[i-2]; 						//Use memoized number
		}
		else { 										//Previous fib number is not memoized
			fib2 = topDownFibonacci(i-2, memo); 	//Calculate previous fib number
		}
			
		result = fib1 + fib2; 		//Compute result
		memo[i] = result; 			//Memoize result in cache
		return result; 				//Return result
	}
	
}
