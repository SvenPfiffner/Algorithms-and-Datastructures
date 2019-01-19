/**
* Autor: Sven Pfiffner
* svenp@student.ethz.ch
* ------------------------------
* Bsc-Computer Science
* @ETH Zurich - 19.01.2019
*/

package algorithms;

import java.util.LinkedList;

/**
 * Implements a factorisation algorithm which finds the prime factorisation of a given number
 */
public class PrimeFactorisation {
	
	/**
	 * Computes the prime factorisation of a given value as an integer array
	 */
	public static Integer[] primeFac(int value) {
		LinkedList<Integer> factors = new LinkedList(); //Stores prime factors of value
		
		while(value % 2 == 0) { //While value can be divided by 2
			factors.add(2); //Add 2 to primefactors
			value /= 2; //Divide by 2
		}
		
		//Now we can be certain that value is odd
		
		for(int i = 3; i <= Math.sqrt(value); i += 2 ) { //Loop from 3 to square root of n (only odd numbers)
			while(value % i == 0) { // While value can be divided by 2
				factors.add(i); //Add i to primefactors
				value /= i; //Divide by i
			}
		}
		
		if(value > 1) { //If value now is greater than 1 it must be a prime
			factors.add(value); //Add to primefactors
		}
		
		Integer[] array = factors.toArray(new Integer[factors.size()]);
		return array;
	}
	
}
