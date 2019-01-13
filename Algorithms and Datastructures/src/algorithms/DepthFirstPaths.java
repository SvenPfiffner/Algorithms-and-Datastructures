/*
* Author: svenp
* 13.01.2019
*/

package algorithms;

import java.util.Stack;

import datastructures.Graph;

/**
 * A class which implements depth first search and the possibility to find paths with depth first search
 */
public class DepthFirstPaths {
	private boolean[] marked; 	//Keeps track of each vertex's marked status
	private int[] edgeTo; 		//Last vertex on known path to this vertex
	private int source; 		//Vertex where dfs started, this will be the root of resulting span-tree
	
	/**
	 * Constructor; finds paths in a given graph from a given source
	 */
	public DepthFirstPaths(Graph graph, int source) {
		//Set fields
		this.marked = new boolean[graph.getVertexCount()];
		this.edgeTo = new int[graph.getVertexCount()];
		this.source = source;
		
		dfs(graph, this.source); 	//Perform a depth first search
	}
	
	/**
	 * Performs a recursive step of depth first search in a given graph from a given vertex
	 */
	private void dfs(Graph graph, int v) {
		marked[v] = true; 					//Mark current vertex as visited
		for(int w : graph.adj(v)) { 		//For all vertices adjacent to current vertex
			if(!marked[w]) { 				//If it was not marked, we reach it for the first time
				edgeTo[w] = v; 				//save current vertex as last visited vertex in the path to new vertex
				dfs(graph, w); 				//Perform depth first search from this vertex
			}
		}
	}
	
	/**
	 * Checks whether a path from source to a given vertex exists
	 * @return
	 * true if such a path exists
	 */
	public boolean pathDoesExist(int v) {
		return marked[v];
	}
	
	/**
	 * Finds the path from source to a given vertex
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
