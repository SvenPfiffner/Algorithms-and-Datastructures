/**
* Autor: Sven Pfiffner
* svenp@student.ethz.ch
* ------------------------------
* Bsc-Computer Science
* @ETH Zurich - 15.01.2019
*/

package datastructures;

/**
 * Datastructure for a knapsack item used for the dynamic programming knapsack problem
 */
public class KnapsackItem {
	private int weight; //Weight of the item
	private int value;	//Value of the item
	
	public KnapsackItem(int value, int weight) {
		//Set fields
		this.weight = weight;
		this.value = value;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int getValue() {
		return value;
	}
}