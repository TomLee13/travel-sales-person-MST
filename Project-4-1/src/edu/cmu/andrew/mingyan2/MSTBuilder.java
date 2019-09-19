package edu.cmu.andrew.mingyan2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// The purposes of this class is:
// 1. to generate a complete undirected graph based on the date that the user input
// 2. to use Prim's algorithm to build a MST
public class MSTBuilder {

	// search in the .csv file for the records based on the date entered by the user
	List<String> findRecords(String startDate, String endDate) {
		Scanner fileInput = null;
		try {
			fileInput = new Scanner(new File("CrimeLatLonXY1990.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		fileInput.nextLine();
		List<String> records = new LinkedList<>();
		while (fileInput.hasNextLine()) {
			String line = fileInput.nextLine();
			String[] crime = line.split(",");

			String date = crime[5];
			String[] splitDate = date.split("/"); // get the month, day of a record
			int month = Integer.parseInt(splitDate[0]);
			int day = Integer.parseInt(splitDate[1]);
			int searchDateInDay = month * 30 + day;

			String[] splitStartDate = startDate.split("/"); // get the month, day of the start date
			int startMonth = Integer.parseInt(splitStartDate[0]);
			int startDay = Integer.parseInt(splitStartDate[1]);
			int startDateInDay = startMonth * 30 + startDay;

			String[] splitEndDate = endDate.split("/"); // get the month, day of the end date
			int endMonth = Integer.parseInt(splitEndDate[0]);
			int endDay = Integer.parseInt(splitEndDate[1]);
			int endDateInDay = endMonth * 30 + endDay;

			if (searchDateInDay >= startDateInDay && searchDateInDay <= endDateInDay) {
				records.add(line);
			}

			if (searchDateInDay > endDateInDay) { // prevent extra iteration of the loop
				break;
			}

		}
		return records;
	}

	// build a distance matrix between each pair
	double[][] generateDistMatrix(List<String> records) {
		// create a new 2D array to store the distance between each pair.
		int size = records.size();
		double[][] distMatrix = new double[size][size];

		// convert the record linked list to an array.
		String[] recordsArr = records.toArray(new String[size]);

		// compute the distances between each pair and store them in a matrix
		for (int i = 0; i < recordsArr.length; i++) {
			String[] recordI = recordsArr[i].split(",");
			double xi = Double.parseDouble(recordI[0]);
			double yi = Double.parseDouble(recordI[1]);
			for (int j = 0; j < recordsArr.length; j++) {
				String[] recordJ = recordsArr[j].split(",");
				double xj = Double.parseDouble(recordJ[0]);
				double yj = Double.parseDouble(recordJ[1]);
				double dist = Math.sqrt(Math.pow(xi - xj, 2) + Math.pow(yi - yj, 2));
				distMatrix[i][j] = dist;
			}
		}
		// test
		/*
		 * for (int i = 0; i < size; i++) { for (int j = 0; j < size; j++) {
		 * System.out.println("Dist between " + i + " and " + j + ": " +
		 * distMatrix[i][j]); } }
		 */
		return distMatrix;
	}

	// build a graph which contains an array of vertices and a 2D array of distance
	Graph buildGraph(List<String> records, double[][] distMatrix) {
		// convert the record linked list to an array.
		int size = records.size();
		String[] recordsArr = records.toArray(new String[size]);

		// create a vertex array which stores the information of interest of each point
		Vertex[] vertices = new Vertex[size];
		int index = 0;
		for (int i = 0; i < recordsArr.length; i++) {
			String[] record = recordsArr[i].split(",");
			double x = Double.parseDouble(record[0]);
			double y = Double.parseDouble(record[1]);
			double lat = Double.parseDouble(record[7]);
			double lon = Double.parseDouble(record[8]);
			Vertex vertex = new Vertex(index, x, y, lat, lon);
			vertices[i] = vertex;
			index++;
		}
		return new Graph(vertices, distMatrix);
	}

	// comput the MST using Prim's algorithm and print out the MST
	int[] prim(Graph g) {
		// an array storing the parent index of each vertex
		int[] parent = new int[g.vertices.length];

		// store the vertices in a min heap
		MinHeap keys = new MinHeap(g.vertices);

		keys.setKey(0, 0);

		parent[0] = -1; // use -1 as a sentinal signal for null

		while (!keys.isEmpty()) {
			Vertex u = keys.extractMin();

			for (int i = 0; i < g.vertices.length; i++) {
				if (u.index != g.vertices[i].index) {
					Vertex v = g.vertices[i];

					int n = keys.countains(v);
					if (n >= 0 && g.edges[u.index][v.index] < keys.get(n).key) {
						parent[v.index] = u.index;
						keys.setKey(n, g.edges[u.index][v.index]);
					}
				}
			}
		}

		// for (int i = 0; i < parent.length; i++) {
		// System.out.println(parent[i]);
		// }
		return parent;
	}

	// a recursive DFS method to load the preorder traversal of the MST
	void mstPreOrder(List<Integer> preorder, int[] parent, int n) {
		if (n == 0) {
			preorder.add(n);
		}
		for (int i = 1; i < parent.length; i++) {
			if (parent[i] == n) {
				preorder.add(i);
				mstPreOrder(preorder, parent, i);
			}
		}
	}

	// create a list storing the node in a hamilton cycle
	List<Integer> hamiltonCycle(List<Integer> preorder) {
		preorder.add(0);
		return preorder;
	}

	double computeLengthOfCycle(Graph g, List<Integer> hamilton) {
		Integer[] hamiltonArr = hamilton.toArray(new Integer[hamilton.size()]);
		double length = 0;
		for (int i = 0; i < hamiltonArr.length - 1; i++) {
			length += g.edges[hamiltonArr[i]][hamiltonArr[i + 1]];
		}
		return length * 0.00018939;
	}

}
