package trains;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Graph {
	Map<Character, Node> nodeMap = new HashMap<Character, Node>();
	private int numVertices;
	private int numEdges;
	
	public int getNumVertices() { return this.numVertices; }
	public int getNumEdges() { return this.numEdges; }
	
	public int getDistance(String path) {
		int distance = 0;
		Node curr = null;
		Node next = null;
		
		try {
			for (int i = 0; i < path.length() -1; i++) {
				curr = nodeMap.get(path.toCharArray()[i]);
				next = nodeMap.get(path.toCharArray()[i+1]);
				distance += curr.getDistanceTo(next);
			}

			return distance;			
		} catch (Exception e) {
			System.out.println("NO SUCH ROUTE.");
			return 0;
		}
	}
	
	public int getDistance(List<Node> path) {
		String stringPath = "";
		for (Node node : path) {
			stringPath += node.getName();
		}
		System.out.println(stringPath);
		return getDistance(stringPath);
	}
	
	// Implements Dijkstra's algorithm instead of A* since we can't triangulate exact
	// coordinates of any given node.
	public List<Node> search(char s, char e) throws NullPointerException {
		Node start = null;
		Node end = null;
		if ((start = nodeMap.get(s)) == null || ((end = nodeMap.get(e)) == null)) {
			throw new NullPointerException("The start and/or end nodes cannot be resolved.");
		}

		for (char c : nodeMap.keySet()) {
			nodeMap.get(c).setDistance(Double.POSITIVE_INFINITY);
		}
		
		Map<Node, Node> parentMap = new HashMap<Node, Node>();		
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		Set<Node> visited = new HashSet<Node>();
		start.setDistance(0.0);
		queue.add(start);
		int visitedCount = 0;
		Node curr = null;
		
		while (!queue.isEmpty()) {
			curr = queue.remove();
			if (!visited.contains(curr)) {
				visitedCount++;
				visited.add(curr);
				if (curr.equals(end) && visitedCount > 1) { 
				  break;
				} else {
					List<Node> neighbors = curr.getNeighbors();
					for (Node n : neighbors) {
						if (!visited.contains(n)) {
							double actualDistance = curr.getDistance() + curr.getDistanceTo(n);
							if (actualDistance < n.getDistance()) {
								n.setDistance(actualDistance);
								parentMap.put(n, curr);
								queue.add(n);													
							}
						}
					}
				}
			}
		}
		
		List<Node> path = new LinkedList<Node>();
		while (!curr.equals(start)) {
			path.add(0, curr);
			curr = parentMap.get(curr);
		}
		path.add(0, start);
		
		if (path.isEmpty()) {
			System.out.println("There is no valid path");
			return null;			
		} else { 
			return path;
		}

	}
	
	private void setup() {
		String currentLine = null;
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader("data/input.txt"));
			while ((currentLine = reader.readLine()) != null) {
				char[] chars = currentLine.toCharArray();
				Node start = nodeMap.get(chars[0]);
				Node end = nodeMap.get(chars[1]);
				if (start == null) {
					start = new Node(chars[0]);
					numVertices++;
					nodeMap.put(chars[0], start);
				}
				if (end == null) {
					end = new Node(chars[1]);
					nodeMap.put(chars[1], end);					
				}
				Edge edge = new Edge(start, end, Character.getNumericValue(chars[2]));
				numEdges++;
				start.addEdge(edge);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.setup();
		
		System.out.println("PROBLEM 1: " + graph.getDistance("ABC"));
		System.out.println("PROBLEM 2: " + graph.getDistance("AD"));
		System.out.println("PROBLEM 3: " + graph.getDistance("ADC"));
		System.out.println("PROBLEM 4: " + graph.getDistance("AEBCD"));
		System.out.println("PROBLEM 5: " + graph.getDistance("AED"));
		System.out.println("PROBLEM 6: " + graph.getDistance(graph.search('A', 'C')));		
		
	}
	
}