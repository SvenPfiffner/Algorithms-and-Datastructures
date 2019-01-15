/*
* Author: svenp
* 15.01.2019
*/

package dp;

/*
 * You are working at the cash counter at a fun-fair, and you have different types of coins
 * available to you in infinite quantities. The value of each coin is already given. 
 * Can you determine the number of ways of making change for a particular number of units using the given types of coins?
 * 
 * SOURCE: https://www.hackerrank.com/challenges/coin-change/problem
 */
public class CoinChange {

	/**
	 * Calculates and returns the number of ways of making change for value with coins c
	 */
	public static int getChangeNum(int[] c, int value) {
		int cache[][] = new int[c.length][value+1]; //Create cache
		
		//Fill in base cases
		for(int row = 0; row < c.length; row++) {
			cache[row][0] = 1; //There is always 1 way to make change for 0
		}
		for(int column = 0; column <= value; column++) {
			if(column % c[0] == 0) { 	//There is exactly 1 way to make change for a value if it is divisible by the first coin
				cache[0][column] = 1;
			}
			else {
				cache[0][column] = 0; 	//No way otherwise
			}
		} 
		
		for(int row = 1; row < c.length; row++) { 				//Go trough all rows
			for(int column = 1; column <= value; column++) { 	//Go trough all columns
				
				if(c[row] > column) { 							//If the new coin is too big
					cache[row][column] = cache[row-1][column];	//We cant take it, value does not change
				}
				else { 													//The new coin is not too big, we can consider taking it
					int amountTakeCoin = cache[row][column - c[row]];	//Amount of possibilities to reach 'column' if we take coin 'row'
					int amountDontTakeCoin = cache[row-1][column];		//Amount of possibilities to reach 'column' if we dont take 'row'
					
					cache[row][column] = amountTakeCoin + amountDontTakeCoin; //Those possibilities combined is our entry here
				}
			}
		}
		
		return cache[c.length -1][value]; //Our solution is at bottom right corner
	}
	
}
