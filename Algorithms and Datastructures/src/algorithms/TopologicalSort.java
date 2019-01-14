/**
* Autor: Sven Pfiffner
* svenp@student.ethz.ch
* ------------------------------
* Bsc-Computer Science
* @ETH Zurich - 14.01.2019
*/

package algorithms;

import java.util.Stack;

import datastructures.DiGraph;

/**
 * A class which allows to find a topological ordering in a graph
 */
public class TopologicalSort {
	private DiGraph graph;
	private boolean[] marked; 				//Keeps track of each vertex's marked status
	private int[] edgeTo; 					//Last vertex on known path to this vertex
	private Stack<Integer> topOrderStack; 	//Stores found topological ordering
	
	/**
	 * Constructor; finds topological sorting of given graph
	 */
	public TopologicalSort(DiGraph graph) {
		//Set fields
		this.graph = graph;
		this.marked = new boolean[graph.getVertexCount()];
		this.edgeTo = new int[graph.getVertexCount()];
		
		if(topOrderDoesExist()) { 								//Check if topological order does exist
			for(int i = 0; i < graph.getVertexCount(); i++) { 	//Iterate trough all vertices of graph
				if(!marked[i]) { 								//If vertex was not marked yet
					dfs(graph, i); 								//Perform dfs from this vertex
				}
			}
		}
	}
	
	/**
	 * Performs a recursive step of modified (topological) depth first search in a given graph from a given vertex
	 */
	private void dfs(DiGraph graph, int v) {
		marked[v] = true; 					//Mark current vertex as visited
		for(int w : graph.adj(v)) { 		//For all vertices adjacent to current vertex
			if(!marked[w]) { 				//If it was not marked, we reach it for the first time
				edgeTo[w] = v; 				//save current vertex as last visited vertex in the path to new vertex
				dfs(graph, w); 				//Perform depth first search from this vertex
			}
		}
		topOrderStack.push(v);				//Add current vertex to topological order stack		
	}
	
	/**
	 * Checks whether a topological order exists
	 * @return
	 * true if such an order exists
	 */
	public boolean topOrderDoesExist() {
		return !(new DirectedCycle(graph).hasCycle()); //Topological Order exists if no cycle exists
	}
	
	/**
	 * Finds the topological order
	 * @return
	 * A stack containing all vertices in topological order
	 * null if no such order exist
	 */
	public Iterable<Integer> pathTo(int v) {
		if(!topOrderDoesExist()) { //If no order exists we can abort
			return null;
		}
		return topOrderStack; 		//Return stack containing order
	}
}
