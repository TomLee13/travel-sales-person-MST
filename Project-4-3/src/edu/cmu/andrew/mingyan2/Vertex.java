package edu.cmu.andrew.mingyan2;

public class Vertex {
	// invariants of the class
	// in this class, index, X, Y, Lat, Lon, and a key for Prim is stored.
	int index;
	double x;
	double y;
	double lat;
	double lon;
	private double key;

	// constructor
	public Vertex(int index, double x, double y, double lat, double lon) {
		this.index = index;
		this.x = x;
		this.y = y;
		this.lat = lat;
		this.lon = lon;
		key = 0;
	}

	public double getKey() {
		return key;
	}

	public void setKey(double key) {
		this.key = key;
	}
}
