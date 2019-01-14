/**
* Autor: Sven Pfiffner
* svenp@student.ethz.ch
* ------------------------------
* Bsc-Computer Science
* @ETH Zurich - 14.01.2019
*/

package algorithms;

import java.util.LinkedList;
import java.util.PriorityQueue;

import datastructures.Edge;
import datastructures.WeightedGraph;

/**
* A class that allows to find Minimal span trees in Weighted graphs using Kruskals algorithm
*/
public class Kruskals {
	
	private boolean[] marked; //Vertices are marked if they are part of MST
	private LinkedList<Edge> mst; //Contains all edges of the MST
	private PriorityQueue<Edge> pq; //Contains all edges that are candidates for the MST
	
	/**
	 * Constructor; finds MST with kruskals algorithm
	 */
	public Kruskals(WeightedGraph graph) {
		//Set fields
		marked = new boolean[graph.getVertexCount()];
		mst = new LinkedList<Edge>();
		pq = new PriorityQueue<Edge>();
		
		//Populate priorityQueue with all edges
		for(Edge e: graph.edges()) {
			pq.add(e);
		}
		
		while(!pq.isEmpty() && mst.size() < graph.getVertexCount() -1) {
			Edge e = pq.remove(); 			//Retrieve unused edge with minimum weight
			int v = e.either(); 			//Get head of edge
			int w = e.other(v); 			//Get tail of edge
			
			if(marked[v] && marked[w]) { 	//Edge would not add any vertices -> edge is not necessary
				continue;
			}
			
			marked[v] = true; 				//Mark head as part of MST
			marked[w] = true; 				//Mark tail as part of MST
			
			mst.add(e); 					//Add edge to MST
			
		}	
	}
	
	
	/**
	 * Returns all edges that are part of the MST as a LinkedList
	 */
	public Iterable<Edge> edges() {
		return mst;
	}
	
	/**
	 * Returns the total weight of the span tree
	 */
	public double weight() {
		double weightSum = 0;
		for(Edge e: mst) {
			weightSum+= e.getWeight();
		}
		return weightSum;
	}
	
}
