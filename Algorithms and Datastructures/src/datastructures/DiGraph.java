/*
* Author: svenp
* 13.01.2019
*/

package datastructures;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;

/**
 * A class which implements graphs as a datastructure and is used by the graph traversal algorithms of the algorithms package
 * NOTE: Implementation via adjacency list
 */
public class DiGraph implements Graph{

	private int vertexCount; 			//Number of vertices in the graph
	private int edgeCount; 				//Number of edges in the graph
	private LinkedList<Integer>[] adj; 	//Adjacency list of the graph
	
	/**
	 * Constructor for a graph with 'vertexCount' vertices and no edges
	 */
	@SuppressWarnings("unchecked")
	public DiGraph(int vertexCount) {
		//Set fields of class
		this.vertexCount = vertexCount;
		this.edgeCount = 0;
		
		//Populate adjacency list with empty linkedlists (no edges exist)
		adj = (LinkedList<Integer>[]) new LinkedList[vertexCount];
		for (int v = 0; v < vertexCount; v++) {
			adj[v] = new LinkedList<Integer>();
		}
	}
	
	/**
	 * Constructor for a graph constructed out of DataInputStream
	 */
	public DiGraph(DataInputStream input) throws IOException {
		this(input.readInt()); 				//Call constructor with first int input as vertexCount
		int edgeCount = input.readInt(); 	//Used for loop
		//Add all edges in input
		for(int i = 0; i < edgeCount; i++) {
			int v = input.readInt();
			int w = input.readInt();
			addEdge(v,w);
		}
		
	}
	
	public int getVertexCount() {
		return vertexCount;
	}
	
	public int getEdgeCount() {
		return edgeCount;
	}

	/**
	 * Connects v and w with an edge in the graph
	 * @param v
	 * @param w
	 */
	public void addEdge(int v, int w) {
		adj[v].add(w); 	//Add w as adjacent to v
		edgeCount++; 	//New edge was created, increase edgeCount
	}
	
	/**
	 * Returns the LinkedList containing all vertices adjacent to v
	 */
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
}