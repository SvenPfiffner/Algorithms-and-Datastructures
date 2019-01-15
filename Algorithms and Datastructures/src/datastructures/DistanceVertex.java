/**
* Autor: Sven Pfiffner
* svenp@student.ethz.ch
* ------------------------------
* Bsc-Computer Science
* @ETH Zurich - 15.01.2019
*/

package datastructures;

/**
 * This class is a datastructure that stores the index of a vertex as well as its distance to a source
 */
public class DistanceVertex implements Comparable<DistanceVertex>{

	private int vertexIndex; 	//The index of this vertex
	private double distanceTo; 	//The distance to this vertex
	
	/**
	 * Constructor
	 */
	public DistanceVertex(int vertexIndex, double distanceTo) {
		//Set fields
		this.vertexIndex = vertexIndex;
		this.distanceTo = distanceTo;
	}
	
	public int getVertexIndex() {
		return vertexIndex;
	}
	
	public void setDistanceTo(double distanceTo) {
		this.distanceTo = distanceTo;
	}
	
	public double getDistanceTo() {
		return distanceTo;
	}
	
	/**
	 * Compares distance to this vertex with distance to other vertex. Mainly used to sort this datastructure by distance in a
	 * PriorityQueue
	 */
	public int compareTo(DistanceVertex other) {
		if(this.distanceTo < other.distanceTo) {
			return -1;
		}
		else if(this.distanceTo > other.distanceTo) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
}
