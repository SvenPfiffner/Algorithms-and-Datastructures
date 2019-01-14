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
 * A class which allows to check any DiGraph for cycles (e.g if a certain digraph is acyclic)
 */
public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	private boolean[] onStack;
	
	public DirectedCycle(DiGraph graph) {
		//Set fields
		onStack = new boolean[graph.getVertexCount()];
		edgeTo = new int[graph.getVertexCount()];
		
		for(int v = 0; v < graph.getVertexCount(); v++) { //Perform dfs on all non-marked vertices
			if(!marked[v]) {
				dfs(graph, v);
			}
		}
	}
	
	private void dfs(DiGraph graph, int v) {
		onStack[v] = true; 			//Mark v as part of the stack
		marked[v] = true; 			//Mark v as visited
		
		for(int w: graph.adj(v)) { 	//Go trough all vertices adjacent to v
			if(this.hasCycle()) { 	//If cycle has occured in the meantime
				return; 			//We are done, return
			}
			else if(!marked[w]) { 	//If vertex adjacent to v was not yet marked
				edgeTo[w] = v; 		//Set v as precessor of w
				dfs(graph, w); 		//Perform dfs on w
			}
			else if(onStack[w]) { 							//If w was marked and is on recursion stack
				cycle = new Stack<Integer>(); 				//There is a cycle
				for(int x = v; x != w; x = edgeTo[x]) { 	//Push all vertices of the cycle to cycle Stack
					cycle.push(x);
				}
				cycle.push(w); 		//Push w to cycle stack
				cycle.push(v); 		//Push v to cycle stack
			}
			onStack[v] = false; 	//Recursion done, v is not on stack anymore
		}
	}
	
	/**
	 * Checks whether a cycle exists or not 
	 * @return
	 * True if there is one, false if not
	 */
	public boolean hasCycle() {
		return cycle != null;
	}
	
	/**
	 * Returns first found cycle
	 * @return
	 * Stack-Path of first found cycle in the graph, null if there is no cycle
	 */
	public Iterable<Integer> cycle() {
		return cycle;
	}
}
