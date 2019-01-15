/**
* Autor: Sven Pfiffner
* svenp@student.ethz.ch
* ------------------------------
* Bsc-Computer Science
* @ETH Zurich - 15.01.2019
*/

package algorithms;

import java.util.PriorityQueue;

import datastructures.DistanceVertex;
import datastructures.Edge;
import datastructures.WeightedGraph;

/**
 * A class that implements shortes path search in positively-weighted graphs using Dijkstras algorithm
 */
public class Dijkstra {

	private Edge[] edgeTo;
	private double[] distTo;
	private PriorityQueue<DistanceVertex> pq;
	
	public Dijkstra(WeightedGraph graph, int s) {
		//Set fields
		edgeTo = new Edge[graph.getVertexCount()];
		distTo = new double[graph.getVertexCount()];
		pq = new PriorityQueue<DistanceVertex>();
		
		//Set all distances to infinit
		for(int v = 0; v < graph.getVertexCount(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0; //Set distance to root to 0

		pq.add(new DistanceVertex(s, 0.0));
		
	}
	
	
}
