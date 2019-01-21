/**
* Autor: Sven Pfiffner
* svenp@student.ethz.ch
* ------------------------------
* Bsc-Computer Science
* @ETH Zurich - 19.01.2019
*/

package algorithms;

/**
 * An implementation of the euler function of abstract algebra
 */
public class EulerGroups {
	
	/**
	 * Computes the amount of generators of a cyclic group with
	 * given cardinality using the euler function
	 */
	public static int eulerCardinality(int cardinality) {
		Integer[] primeFac = algorithms.PrimeFactorisation.primeFac(cardinality); //Compute prime factorisation of cardinality
		int eulerCard = 1; //Stores the euler cardinality
		
		for(int i = 0; i < primeFac.length;) { 						//Iterate through all prime factors
			
			int amountOfThisFac = 0; 								//Counts occurencies of current factor (potences)
			int factor = primeFac[i];								//Stores the current factor
			do {
				amountOfThisFac++; 									//Add to amount of occurencies
				i++;												//Move forward in array
			} while(i < primeFac.length && primeFac[i] == factor); 	//Repeat as long as end of array is not reached and same factor follows
			
			eulerCard *= Math.pow(factor, amountOfThisFac-1) * (factor-1); //Add computation to result based on euler function
		}
		
		return eulerCard;
	}
}