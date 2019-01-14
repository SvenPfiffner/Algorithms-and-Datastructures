/**
* Autor: Sven Pfiffner
* svenp@student.ethz.ch
* ------------------------------
* Bsc-Computer Science
* @ETH Zurich - 14.01.2019
*/

package datastructures;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;

/**
 * A class which implements undirected-weighted graphs as a datastructure and is used by the graph traversal algorithms of the algorithms package
 * NOTE: Implementation via adjacency list and the edge datastructure
 */
public class WeightedMonoGraph implements WeightedGraph{
	
	private int vertexCount; 			//Number of vertices
	private int edgeCount; 				//Number of edges
	private LinkedList<Edge>[] adj; 	//Adjacency list
	
	/**
	 * Constructor for a graph with 'vertexCount' vertices and no edges
	 */
	@SuppressWarnings("unchecked")
	public WeightedMonoGraph(int vertexCount) {
		//Set fields
		this.vertexCount = vertexCount;
		this.edgeCount = 0;
		
		//Populate adjacency list with empty linkedlists (no edges exist)
		adj = (LinkedList<Edge>[]) new LinkedList[vertexCount];
		for(int v = 0; v < vertexCount; v++) {
			adj[v] = new LinkedList<Edge>();
		}
	}
	
	/**
	 * Constructor for a graph constructed out of DataInputStream
	 */
	public WeightedMonoGraph(DataInputStream input) throws IOException {
		this(input.readInt()); 				//Call constructor with first int input as vertexCount
		int edgeCount = input.readInt(); 	//Used for loop
		//Add all edges in input
		for(int i = 0; i < edgeCount; i++) {
			int v = input.readInt();
			int w = input.readInt();
			int weight = input.readInt();
			Edge e = new Edge(v,w,weight); 	//Create new edge out of information
			addEdge(e);						//Add edge to graph
		}
	}
	
	public int getVertexCount() {
		return vertexCount;
	}
	
	public int getEdgeCount() {
		return edgeCount;
	}
	
	/**
	 * Adds an edge (as edge object) to the graph
	 */
	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
	}
	
	/**
	 * Returns the LinkedList containing all edges incident to v
	 */
	public Iterable<Edge> adj(int v) {
		return adj[v];
	}
	
	/**
	 * Returns all edges of the graph (prevents dublicates)
	 */
	public Iterable<Edge> edges() {
		LinkedList<Edge> edges = new LinkedList<Edge>();
		for(int v = 0; v < vertexCount; v++) {
			for(Edge e : adj[v]) {
				if (e.other(v) > v) {
					edges.add(e);
				}
			}
		}
		return edges;
	}
}

