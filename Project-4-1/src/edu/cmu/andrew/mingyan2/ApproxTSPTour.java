package edu.cmu.andrew.mingyan2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ApproxTSPTour {
	MSTBuilder mst = new MSTBuilder();

	public static void main(String[] args) {
		ApproxTSPTour tour = new ApproxTSPTour();
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

		System.out.println("Length of cycle: " + tour.mst.computeLengthOfCycle(g, hamilton) + " miles");

		scan.close();
	}
}
