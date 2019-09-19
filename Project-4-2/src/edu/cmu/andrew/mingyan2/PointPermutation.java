package edu.cmu.andrew.mingyan2;

// this class is used to compute every possible permutation of the tour
// store its order of tour and the length of cycle in a Permutation object
public class PointPermutation {
	private Graph g;
	private Permutation optimalTourSoFar;
	private Permutation optimalTour = new Permutation();

	public PointPermutation(Graph g) {
		this.g = g;
	}

	// inner class Permutation
	class Permutation {
		int[] tour;
		double length;

		// default constructor
		public Permutation() {

		}

		// non-default constructor
		public Permutation(int[] tour, double length) {
			this.tour = tour;
			this.length = length;
		}

		void setTour(int[] tour) {
			this.tour = tour;
		}

		void setLength(double length) {
			this.length = length;
		}

		double getLength() {
			return this.length;
		}

		int[] getTour() {
			int[] tour1 = tour;
			return tour1;
		}

	}

	// this method basically performs the swap recursively
	// l is the start position of the swap, r is the end position of the swap.
	/*
	 * public List<Permutation> permute(Integer[] arr, int l, int r) { if (l == r) {
	 * int[] tour = new int[arr.length]; for (int i = 0; i < arr.length; i++) {
	 * tour[i] = arr[i]; }
	 * 
	 * double length = g.edges[0][arr[0]]; for (int i = 0; i < arr.length - 1; i++)
	 * { length += g.edges[arr[i]][arr[i + 1]]; } length += g.edges[arr[arr.length -
	 * 1]][arr[0]];
	 * 
	 * list.add(new Permutation(tour, length)); } else { for (int i = l; i <= r;
	 * i++) { arr = swap(arr, l, i); permute(arr, l + 1, r); arr = swap(arr, l, i);
	 * } } return list; }
	 */

	// this method basically performs the swap recursively
	// l is the start position of the swap, r is the end position of the swap.
	// it update the optimalTourSoFar if the new length is shorter
	// it returns the optimal permutation
	public Permutation permute(Integer[] arr, int l, int r) {
		if (l == r) { // when the recursion hit the situation when l equals r
			// record this tour
			int[] tour = new int[arr.length];
			for (int i = 0; i < arr.length; i++) {
				tour[i] = arr[i];
			}
			// compute this length
			double length = g.edges[0][arr[0]];
			for (int i = 0; i < arr.length - 1; i++) {
				length += g.edges[arr[i]][arr[i + 1]];
			}
			length += g.edges[arr[arr.length - 1]][arr[0]];

			if (optimalTourSoFar == null) { // if optimalTourSoFar hasn't been initialized
				optimalTourSoFar = new Permutation(tour, length);
			} else {
				if (length < optimalTourSoFar.getLength()) {
					// if this length is shorter than the length of optimalTourSoFar
					// update optimalTourSoFar
					optimalTourSoFar.setTour(tour);
					optimalTourSoFar.setLength(length);
				}
			}
		} else {
			for (int i = l; i <= r; i++) {
				arr = swap(arr, l, i);
				permute(arr, l + 1, r);
				arr = swap(arr, l, i);
			}
		}
		// return optimalTour
		optimalTour.setTour(optimalTourSoFar.getTour());
		optimalTour.setLength(optimalTourSoFar.getLength());
		return optimalTour;
	}

	// swap element at index i and j
	private Integer[] swap(Integer[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		return a;
	}

}
