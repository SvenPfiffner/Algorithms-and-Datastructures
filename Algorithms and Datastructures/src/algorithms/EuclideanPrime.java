/**
* Autor: Sven Pfiffner
* svenp@student.ethz.ch
* ------------------------------
* Bsc-Computer Science
* @ETH Zurich - 19.01.2019
*/

package algorithms;

/**
 * An implementation of the euclidean algorithm
 */
public class EuclideanPrime {

	/**
	 * Finds and returns the greatest common divisor of two integers
	 */
	public static int euclidGCD(int value1, int value2) {
		
		//Get smaller and bigger number of entry
		int big = Math.max(value1, value2);
		int small = Math.min(value1, value2);
		
		if(small == 0) { //We have found the gcd with value1 (Given that any number divides zero)
			return big;
		}
		
		return euclidGCD(small, big % small); //Repeat recursively with remainder
	}
	
	/**
	 * Returns true if two integers are coPrime, false if not
	 */
	public static boolean areCoPrime(int value1, int value2) {
		return (euclidGCD(value1,value2) == 1); //Two numbers are coprime exactly if their gcd equals 1
	}
	
}
