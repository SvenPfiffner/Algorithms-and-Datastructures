/**
* Autor: Sven Pfiffner
* svenp@student.ethz.ch
* ------------------------------
* Bsc-Computer Science
* @ETH Zurich - 15.01.2019
*/

package algorithms;

import java.util.PriorityQueue;
import java.util.Stack;

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

		pq.add(new DistanceVertex(s, 0.0)); 	//Add first vertex with distance 0 to queue
		while(!pq.isEmpty()) { 					//Go trough queue
			relax(graph, pq.remove()); 			//Relax and remove first element
		}
		
	}
	
	private void relax(WeightedGraph graph, DistanceVertex v) {
		
		int vIndex = v.getVertexIndex(); 							//Get Index of DistanceVertex v
		for(Edge e : graph.adj(vIndex)) { 							//Go trough all edges incident to v
			int wIndex = e.other(vIndex);							//Get vertex this edge connects to v
			if(distTo[wIndex] > distTo[vIndex] + e.getWeight()) {	//If distance to w is currently bigger than distance to v + weight of edge
				distTo[wIndex] = distTo[vIndex] + e.getWeight();	//Set v + weight of edge as new distance
				edgeTo[wIndex] = e;									//Set current edge as edge that leads to w on the path
				
				for(DistanceVertex vertex: pq) { 					//Go trough priority queue
					if(vertex.getVertexIndex() == wIndex) { 		//If w is part of queue
						pq.remove(vertex); 							//Remove from queue
						vertex.setDistanceTo(distTo[wIndex]); 		//Change distance
						pq.add(vertex); 							//Re-add to queue
						return; 									//relaxation done
					}
				}
				pq.add(new DistanceVertex(wIndex, distTo[wIndex]));	//Add w to priority queue
			}
			
		}
	}
	
	/**
	 * Returns shortest possible distance from source to any other vertex
	 * If this vertex is not reachable by source, return Double.POSITIVE_INFINITY
	 */
	public double distTo(int v) {
		return distTo[v];
	}
	
	/**
	 * Checks whether a path from source to any other vertex exists
	 * @return
	 * True if path exist, false if not
	 */
	public boolean hasPathTo(int v) {
		return(distTo[v] < Double.POSITIVE_INFINITY);
	}
	
	/**
	 * Returns the shortest path from source to any vertex v as a stack of edges.
	 * If no such path exist, return null
	 */
	public Iterable<Edge> pathTo(int v) {
		if(!hasPathTo(v)) { 											//Check if path does exist
			return null;
		}
		Stack<Edge> path = new Stack<Edge>(); 							//Create stack which contains path-edges
		for(Edge e = edgeTo[v]; e != null; e = edgeTo[e.either()]) { 	//Add all edges of the path to stack
			path.push(e);
		}
		return path;													//Return the path
	}
	
	
}
