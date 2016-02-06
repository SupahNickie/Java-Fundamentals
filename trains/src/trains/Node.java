package trains;

import java.util.List;
import java.util.ArrayList;

public class Node implements Comparable<Node> {
	private char name;
	private List<Edge> edges;
	private double distance;
	
	Node(char name) {
		this.name = name;
		this.edges = new ArrayList<Edge>();
		this.distance = 0.0;
	}
	
	public char getName() { return this.name; }		
	public List<Edge> getEdges() { return this.edges; }
	public double getDistance() { return this.distance; }
	
	public void setDistance(double distance) { 
		this.distance = distance;
	}
		
	public void addEdge(Edge e) throws NullPointerException {
		if (e == null) { throw new NullPointerException("Edge cannot be null"); }
		if (getEdges().contains(e)) { 
			System.out.println("Edge already added!"); 
		} else {
			getEdges().add(e);
		}
	}

	public List<Node> getNeighbors() {
		List<Node> neighbors = new ArrayList<Node>();
		for (Edge e : getEdges()) {
			if (!neighbors.contains(e.getEnd())) {
				neighbors.add(e.getEnd());
			}
		}
		return neighbors;
	}
	
	@SuppressWarnings("null")
	public double getDistanceTo(Node n) {
		for (Edge e : getEdges()) {
			if (n.equals(e.getEnd())) {
				return e.getLength();
			}
		}
		return (Double)null;
	}

	public boolean equals(Node n) {
		if (this.name == n.getName()) {
			return true;
		} else {
			return false;
		}
	}
	
	public int compareTo(Node n) {
		return ((Double)this.getDistance()).compareTo((Double)n.getDistance());
	}

}