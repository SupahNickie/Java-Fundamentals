// Word of warning: this data structure does not guard against nulls and bad inputs

import java.util.List;
import java.util.ArrayList;

public class GraphNode {
  private double x;
  private double y;
  private String name;
  private List<GraphEdge> edges;

  GraphNode(double x, double y, String name) {
    this.x = x;
    this.y = y;
    this.name = name;
    this.edges = new ArrayList<GraphEdge>();
  }

  public double getX() { return this.x; }
  public double getY() { return this.y; }
  public String getName() { return this.name; }
  public List<GraphEdge> getEdges() { return this.edges; }

  public void addEdge(GraphEdge edge) {
    getEdges().add(edge);
  }

  public List<GraphNode> getNeighbors() {
    List<GraphNode> neighbors = new ArrayList<GraphNode>();
    for (GraphEdge edge : getEdges()) {
      if (!neighbors.contains(edge.getEnd())) {
        neighbors.add(edge.getEnd());
      }
    }
    return neighbors;
  }
}
