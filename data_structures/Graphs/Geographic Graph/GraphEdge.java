// Word of warning: this data structure does not guard against nulls and bad inputs

public class GraphEdge {
  private GraphNode start;
  private GraphNode end;
  private double length;
  private String name;

  GraphEdge(GraphNode start, GraphNode end, double length, String name) {
    this.start = start;
    this.end = end;
    this.length = length;
    this.name = name;
  }

  public GraphNode getStart() { return this.start; }
  public GraphNode getEnd() { return this.end; }
  public double getLength() { return this.length; }
  public String getName() { return this.name; }
}
