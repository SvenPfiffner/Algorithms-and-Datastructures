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
* A class that allows to find Minimal span trees in Weighted graphs using Prims algorithm
* NOTE: This class assumes that any processed graph is fully connected. If there are disconnected parts it will always
* return the MST that contains the first vertex of the graph. As a consequence, this class can be used to check if a graph is fully connected. To do so,
* use the isConnected method which checks if found MST contains all vertices
*/
public class Prims {

	private boolean[] marked; 			//Vertices are marked if they are part of MST
	private LinkedList<Edge> mst; 		//Stores Edges that are part of MST
	private PriorityQueue<Edge> pq; 	//Used to find edges of minimal weight (Finds smallest edge in O(log n))
	
	/**
	 * Constructor; finds MST with prims algorithm
	 */
	public Prims(WeightedGraph graph) {
		//Set fields
		pq = new PriorityQueue<Edge>();
		marked = new boolean[graph.getVertexCount()];
		mst = new LinkedList<Edge>();
		
		visit(graph, 0); 					//Visit the first vertex of the graph (according to adjacency list)
		
		while(!pq.isEmpty()) { 				//While queue is not empty (there are still edges to choose)
			Edge e = pq.remove(); 			//Retrieve edge with smallest weight (PQ sorts based on compareTo of the Edge class)
			int v = e.either(); 			//Get head of edge
			int w = e.other(v); 			//Get tail of edge
			if(marked[v] && marked[w]) { 	//If both are already marked (this means that both vertices are already in our mst)
				continue;					//This edge is not necessary, continue loop from beginning
			}
			mst.addLast(e);					//Add edge to mst
			
			if(!marked[v]) {				//If head of edge was not visited yet
				visit(graph, v);			//Visit
			}
			
			if(!marked[w]) {				//If tail of edge was not visited yet
				visit(graph, w);			//Visit
			}
		}
		
	}
	
	/**
	 * Visits a vertex in the graph. This means that we add this vertex to the MST and add all its incident edges to the
	 * edges that are considered candidates for the MST.
	 */
	private void visit(WeightedGraph graph, int v) {
		marked[v] = true; 				//Mark vertex as visited
		for(Edge e: graph.adj(v)) { 	//For all incident edges
			if(!marked[e.other(v)]) {	//If it does not lead to an already visited vertex
				pq.add(e);				//Add to priority queue (gets sorted automatically during insertion)
			}
		}
			
		}
	
	/**
	 * Checks whether the graph is fully connected by analysing the computed MST
	 * @return
	 * True if the graph is fully connected, false if not
	 */
	public boolean isConnected() {
		int markedVertices = 0;
		
		for(boolean b : marked) { //Count how many vertices were marked
			if(b) {
				markedVertices++;
			}
		}
		
		return markedVertices == marked.length; //The graph is fully connected exactly if all vertices are part of the MST
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
