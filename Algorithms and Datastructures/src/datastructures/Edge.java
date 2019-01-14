/**
* Autor: Sven Pfiffner
* svenp@student.ethz.ch
* ------------------------------
* Bsc-Computer Science
* @ETH Zurich - 14.01.2019
*/

package datastructures;

/**
 * A class which represents the edge datastructure which can be used to store start-/endpoints 
 * as well as weights of an edge in a graph
 */
public class Edge implements Comparable<Edge> {
	
	private int v; 			//Head of the Edge
	private int w; 			//Tail of the Edge
	private double weight; 	//Weight of the Edge
	
	public Edge(int v, int w, double weight) {
		//Set fields
		this.v = v;
		this.w = w;
		this.weight = weight; 
	}
	
	public double getWeight() {
		return weight;
	}
	
	/**
	 * Returns the head vertex of the edge 
	 */
	public int either() {
		return v;
	}
	
	/**
	 * Returns the vertex that is connected to a given vertex v by this edge
	 */
	public int other(int v) {
		if(v == this.v) { 									//If head was given as argument
			return this.w; 									//Return tail
		}
		else if(v == this.w) { 								//If tail was given as argument
			return this.v; 									//Return head
		}
		else { 												//If argument is invalid
			throw new RuntimeException("Illegal edge!"); 	//Throw exception
		}
	}
	
	/**
	 * Compares this edges weight to the weight of an other edge.
	 * @return
	 * -1: if weight is smaller
	 * +1: if weight is bigger
	 *  0: if weight is equal
	 */
	public int compareTo(Edge other) {
		if(this.getWeight() < other.getWeight()) { //Compare weights
			return -1;
		}
		else if(this.getWeight() > other.getWeight()) { //Compare weights
			return +1;
		}
		else {
			return 0;
		}
	}
	
}
