package edu.cmu.andrew.mingyan2;

public class MinHeap {
	private Vertex[] vs;
	private int size;

	// constructor
	public MinHeap(Vertex[] vertices) {
		vs = vertices;
		size = vertices.length;
		// initialize the heap with positive infinite number.
		for (int i = 0; i < vertices.length; i++) {
			vs[i].key = Double.POSITIVE_INFINITY;
		}
	}

	// check whether the heap is empty
	public boolean isEmpty() {
		return size == 0;
	}

	// check if a vertex is contained in the heap
	// if yes, return the its index in the heap
	// if no, return -1
	public int countains(Vertex v) {
		for (int i = 0; i < vs.length; i++) {
			if (v.index == vs[i].index) {
				return i;
			}
		}
		return -1;
	}

	// get the ith vertex
	public Vertex get(int i) {
		return vs[i];
	}

	// a public method that set the key of an element at index i
	public void setKey(int index, double key) {
		vs[index].key = key;
		for (int i = size / 2; i >= 0; i--) {
			heapify(i);
		}
	}

	// Extract the min element in the heap, which is the first element of the array.
	// Then set the last element in the array as root, and heapify the array.
	// Return the Vertex with the minimum key.
	public Vertex extractMin() {
		if (size == 0) { // handle the special case when the heap is empty.
			System.out.println("The heap is empty now.");
			return null;
		} else if (size == 1) { // handle the special case when the heap has only one element
			Vertex min = vs[0];
			vs = new Vertex[0];
			size--;
			return min;
		} else {
			Vertex min = vs[0];
			Vertex last = vs[size - 1];
			vs[0] = last; // set the last element as the new root

			// copy the heap from the first element to the second last element
			Vertex[] temp = new Vertex[size - 1];
			for (int i = 0; i < size - 1; i++) {
				temp[i] = vs[i];
			}
			// reset the minHeap
			vs = temp;
			size--;
			// maintain the heap properties.
			heapify(0);

			return min;
		}
	}

	// heapify the minHeap
	private void heapify(int i) {
		int leftChild = leftChild(i);
		int rightChild = rightChild(i);
		int smallest = -1;

		// find the index of the node with the smallest key
		if (leftChild < size) {
			if (vs[leftChild].key < vs[i].key) {
				smallest = leftChild;
			} else {
				smallest = i;
			}
		}
		if (rightChild < size) {
			if (vs[rightChild].key < vs[smallest].key) {
				smallest = rightChild;
			}
		}

		// if the node with the smallest key if not the current node
		if (smallest != i && smallest >= 0) {
			swap(smallest);
			heapify(smallest);
		}
	}

	// return the index of the left child of node i
	private int leftChild(int i) {
		return 2 * i + 1;
	}

	// return the index of the right child of node i
	private int rightChild(int i) {
		return 2 * i + 2;
	}

	// return the index of the parent of node i
	private int parent(int i) {
		if (i % 2 == 1) {
			return i / 2;
		} else {
			return (i - 1) / 2;
		}
	}

	// swap the node i with its parent
	private void swap(int i) {
		if (i >= 1) {
			int parent = parent(i);
			Vertex temp = vs[i];
			vs[i] = vs[parent];
			vs[parent] = temp;
		}
	}

	public void printHeap() {
		for (int i = 0; i < size; i++) {
			System.out.println("Key of " + i + " is " + vs[i].key);
		}
	}
}
