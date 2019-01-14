/**
* Autor: Sven Pfiffner
* svenp@student.ethz.ch
* ------------------------------
* Bsc-Computer Science
* @ETH Zurich - 14.01.2019
*/

package datastructures;
public interface WeightedGraph {
	public int getVertexCount();
	public int getEdgeCount();
	public void addEdge(Edge e);
	public Iterable<Edge> adj(int v);
	public Iterable<Edge> edges();
}
