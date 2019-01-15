/**
* Autor: Sven Pfiffner
* svenp@student.ethz.ch
* ------------------------------
* Bsc-Computer Science
* @ETH Zurich - 15.01.2019
*/

package dp;

import java.util.Arrays;

import datastructures.KnapsackItem;

/**
 *	This class implements a dynamic programming solution to the 0/1 knapsack problem
 */
public class Knapsack {
	
	/**
	 * Calculates and returns highest possible value of items contained in the knapsack.
	 */
	public static int highestKnapsackValue(int capacity, KnapsackItem[] availableItems) {
		
		int[][] cache = new int[availableItems.length + 1][capacity + 1]; 											//Create dp cache
		
		for(int row = 1; row <= availableItems.length; row++) { 													//Go trough each row
			for(int column = 1; column <= capacity; column++) { 													//Go trough each column
				KnapsackItem itemToConsider = availableItems[row-1]; 												//This item could be part of optimal solution
				int dontAddValue = cache[row-1][column];															//Calc Value of knapsack if we don't add item
				
				if(itemToConsider.getWeight() <= column) {															//If new item would fit
					int addValue = itemToConsider.getValue() + cache[row-1][column - itemToConsider.getWeight()]; 	//Calc Value of knapsack if we add item
					cache[row][column] = Math.max(dontAddValue, addValue);											//Add higher value to cache
				}
				else { 																								//If it doesn't fit
					cache[row][column] = dontAddValue;																//add don't add value to cache
				}
			}
		}
		
		for(int[] row : cache) {
			System.out.println(Arrays.toString(row));
		}
		
		return cache[availableItems.length][capacity]; 															//Solution is at bottom right corner of cache table
	}
}
