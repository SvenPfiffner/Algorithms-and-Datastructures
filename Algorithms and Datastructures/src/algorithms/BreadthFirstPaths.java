/**
* Autor: Sven Pfiffner
* 13.01.2019
*/

package algorithms;

import java.util.LinkedList;
import java.util.Stack;

import datastructures.Graph;

/**
 * A class which implements breadth first search and the possibility to find shortest paths with breadth first search
 */
public class BreadthFirstPaths {
	private boolean[] marked; 
	private int[] edgeTo;
	private int source;
	
	/**
	 * Constructor; finds shortest paths in a given graph from a given source
	 */
	public BreadthFirstPaths(Graph graph, int source) {
		//Set fields
		this.marked = new boolean[graph.getVertexCount()];
		this.edgeTo = new int[graph.getVertexCount()];
		this.source = source;
		
		bfs(graph, this.source); 	//Perform a breadth first search
	}
	
	/**
	 * Performs a breadth first search in a given graph from a given vertex
	 */
	private void bfs(Graph graph, int v) {
		LinkedList<Integer> queue = new LinkedList<>(); 	//Used to store visited vertices
		marked[v] = true; 									//Mark first vertex as visited
		queue.addLast(v);									//Add first vertex to queue
		
		while(!queue.isEmpty()) { 			//While there are vertices in the queue	
			int w = queue.removeFirst(); 	//Get element at head of queue
			for(int c: graph.adj(w)) { 		//Go through all adjacent vertices
				if(!marked[c]) { 			//If adjacent vertex not yet marked
					edgeTo[c] = w; 			//Set v as precessor in the shortest path
					marked[c] = true; 		//Mark as visited
					queue.addLast(c); 		//Add to queue
				}
			}
		}
		
	}
	
	/**
	 * Checks whether a shortest path from source to a given vertex exists
	 * @return
	 * true if such a path exists
	 */
	public boolean pathDoesExist(int v) {
		return marked[v];
	}
	
	/**
	 * Finds the shortest path from source to a given vertex
	 * @return
	 * A stack containing all vertices on the path from source to v (In order such that source is first element)
	 * null if no such path exist
	 */
	public Iterable<Integer> pathTo(int v) {
		if(!pathDoesExist(v)) { //If no path exists we can abort
			return null;
		}
		
		Stack<Integer> path = new Stack<Integer>(); 	//Create a stack that will contain vertices of the path
		for(int x = v; x != source; x = edgeTo[x]) { 	//Trace the path from v to source using last visited vertices
			path.push(x);
		}
		path.push(source); 	//Source is part of path, push to stack
		return path; 		//Return stack containing path
	}
	
}
