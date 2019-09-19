package edu.cmu.andrew.mingyan2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// this class is used to generate a Google Earth .kml file
public class KMLFiler {
	public void toKML(Graph g, Integer[] tour1, int[] tour2) {
		String kmlStart = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
				+ "<kml xmlns=\"http://earth.google.com/kml/2.2\">\n" + "<Document>\n"
				+ "<name>Pittsburgh TSP</name><description>TSP on Crime</description><Style id=\"style6\">\n"
				+ "<LineStyle>\n" + "<color>73FF0000</color>\n" + "<width>5</width>\n" + "</LineStyle>\n" + "</Style>\n"
				+ "<Style id=\"style5\">\n" + "<LineStyle>\n" + "<color>507800F0</color>\n" + "<width>5</width>\n"
				+ "</LineStyle>\n" + "</Style>\n";
		String kmlEnd = "</Document>\n" + "</kml>\n";
		try {
			File out = new File("PGHCrimes" + ".kml");
			if (!out.exists()) {
				out.createNewFile();
			}
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(out));
				bw.write(kmlStart);

				bw.write("<Placemark>\n" + "<name>TSP Path</name>\n" + "<description>TSP Path</description>\n"
						+ "<styleUrl>#style6</styleUrl>\n" + "<LineString>\n" + "<tessellate>1</tessellate>\n");
				bw.write("<coordinates>\n");
				for (int i = 0; i < tour1.length; i++) {
					bw.write(String.valueOf(g.getVertices()[tour1[i]].lon) + ","
							+ String.valueOf(g.getVertices()[tour1[i]].lat) + ",0.000000\n");
				}
				bw.write("</coordinates>\n" + "</LineString>\n" + "</Placemark>\n");

				bw.write("<Placemark>\n" + "<name>Optimal Path</name>\n" + "<description>Optimal Path</description>\n"
						+ "<styleUrl>#style5</styleUrl>\n" + "<LineString>\n" + "<tessellate>1</tessellate>\n"
						+ "<coordinates>\n");
				for (int i = 0; i < tour2.length; i++) {
					bw.write(String.valueOf(g.getVertices()[tour2[i]].lon + 0.00001) + ","
							+ String.valueOf(g.getVertices()[tour2[i]].lat + 0.00001) + ",0.000000\n");
				}
				bw.write("</coordinates>\n" + "</LineString>\n" + "</Placemark>\n");

				bw.write(kmlEnd);

				bw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
