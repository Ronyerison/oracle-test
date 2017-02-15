/**
 * 
 */
package br.ufpi.loes.oracleTest.core.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ronyerison
 *
 */
public class Node<E, V> {
	private Node<E, V> parent = null;
	private V data = null;
	private List<Node<E, V>> children = new ArrayList<Node<E, V>>();
	private List<Edge<E, V>> edges = new ArrayList<Edge<E ,V>>();

	public Node(V data) {
		this.data = data;
	}

	public Node(V data, Node<E, V> parent, E edgeValue) {
		this.data = data;
		this.parent = parent;
		this.parent.addEdge(new Edge<E, V>(edgeValue, parent, this));
	}

	public List<Node<E, V>> getChildren() {
		return children;
	}

	public void setParent(Node<E, V> parent, E edgeValue) {
		parent.addChild(this, edgeValue);
		this.parent = parent;
	}

	public void addChild(V data, E edgeValue) {
		Node<E, V> child = new Node<E, V>(data);
		this.children.add(child);
		this.addEdge(new Edge<E, V>(edgeValue, this, child));
	}

	public void addChild(Node<E, V> child, E edgeValue) {
		this.children.add(child);
		this.edges.add(new Edge<E, V>(edgeValue, this, child));
	}

	public V getData() {
		return this.data;
	}

	public void setData(V data) {
		this.data = data;
	}

	public boolean isRoot() {
		return (this.parent == null);
	}

	public boolean isLeaf() {
		if (this.children.size() == 0)
			return true;
		else
			return false;
	}

	public void removeParent() {
		this.parent = null;
	}

	/**
	 * @return the edges
	 */
	public List<Edge<E, V>> getEdges() {
		return edges;
	}

	/**
	 * @param edges the edges to set
	 */
	public void setEdges(List<Edge<E, V>> edges) {
		this.edges = edges;
	}
	
	public void addEdge(Edge<E, V> edge){
		this.edges.add(edge);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return data+"";
	}

	
}
