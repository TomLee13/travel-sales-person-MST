package edu.cmu.andrew.mingyan2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import edu.cmu.andrew.mingyan2.PointPermutation.Permutation;

// This class is the driver of the whole project.
public class OptimalTour {
	MSTBuilder mst = new MSTBuilder();

	public static void main(String[] args) {
		OptimalTour tour = new OptimalTour();
		Scanner scan = new Scanner(System.in);
		// ask the user to enter start date and end date
		System.out.println("Enter start date");
		String startDate = scan.nextLine();
		System.out.println("Enter end date");
		String endDate = scan.nextLine();

		List<String> records = tour.mst.findRecords(startDate, endDate);

		System.out.println("Crime records between " + startDate + " and " + endDate);
		System.out.println("Number of records: " + records.size());

		// use an iterator to print every crime record in range.
		Iterator<String> iter = records.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		System.out.println();
		// build the distance matrix
		double[][] distMatrix = tour.mst.generateDistMatrix(records);
		// a graph is built
		Graph g = tour.mst.buildGraph(records, distMatrix);

		// print out the hamilton cycle
		System.out.println("Hamilton Cycle (not neccessarily optimum):");
		List<Integer> preorder = new LinkedList<>();
		int[] parent = tour.mst.prim(g);

		tour.mst.mstPreOrder(preorder, parent, 0);
		List<Integer> hamilton = tour.mst.hamiltonCycle(preorder);

		Iterator<Integer> iter1 = hamilton.iterator();
		while (iter1.hasNext()) {
			System.out.print(iter1.next() + " ");
		}
		System.out.println();

		System.out.println("Length of cycle: " + tour.mst.computeLengthOfCycle(g, hamilton) + " miles\n");

		// print out the optimal cycle
		System.out.println("Looking at every permutation to find the optimal solution");
		System.out.println("The best permutation");

		PointPermutation p = new PointPermutation(g);
		Integer[] hamiltonArr = hamilton.toArray(new Integer[hamilton.size()]);

		Permutation optimalPermutation = p.permute(hamiltonArr, 1, hamiltonArr.length - 2);

		/*
		 * Collections.sort(lengthList, new Comparator<Permutation>() {
		 * 
		 * @Override public int compare(Permutation o1, Permutation o2) { if (o1.length
		 * - o2.length > 0) { return 1; } else if (o1.length - o2.length < 0) { return
		 * -1; } else { return 0; } }
		 * 
		 * });
		 */

		System.out.println("The best permutation");

		double optimalLength = optimalPermutation.getLength() * 0.00018939;

		for (int i = 0; i < optimalPermutation.getTour().length; i++) {
			System.out.print(optimalPermutation.getTour()[i] + " ");
		}
		System.out.println("\n");

		System.out.println("Optimal Cycle Length = " + optimalLength + " miles");

		// generate the .kml file
		KMLFiler kml = new KMLFiler();
		Integer[] tour1 = hamilton.toArray(new Integer[hamilton.size()]);
		int[] tour2 = optimalPermutation.getTour();

		kml.toKML(g, tour1, tour2);
		System.out.println("KML file generated");

		scan.close();
	}
}
