package edu.cmu.andrew.mingyan2;

// this class is to store a graph G = (V, E)
// here, I use the edge matrix approach
public class Graph {
	private Vertex[] vertices;
	double[][] edges;

	public Graph(Vertex[] vertices, double[][] edges) {
		this.vertices = vertices;
		this.edges = edges;
	}

	// return a copy of the vertex array
	public Vertex[] getVertices() {
		int n = vertices.length;
		Vertex[] copy = new Vertex[n];
		for (int i = 0; i < n; i++) {
			copy[i] = vertices[i];
		}
		return copy;
	}
}
