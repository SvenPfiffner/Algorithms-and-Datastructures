/**
* Autor: Sven Pfiffner
* svenp@student.ethz.ch
* ------------------------------
* Bsc-Computer Science
* @ETH Zurich - 14.01.2019
*/

package datastructures;
public interface Graph {
	public int getVertexCount();
	public int getEdgeCount();
	public void addEdge(int v, int w);
	public Iterable<Integer> adj(int v);
}
