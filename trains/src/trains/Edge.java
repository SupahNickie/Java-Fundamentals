package trains;

public class Edge {
	private Node start;
	private Node end;
	private int length;
	
	Edge(Node start, Node end, int length) {
		this.start = start;
		this.end = end;
		this.length = length;
	}
	
	public Node getStart() { return this.start; }
	public Node getEnd() { return this.end; }
	public int getLength() { return this.length; }
}