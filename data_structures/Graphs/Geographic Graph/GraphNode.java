// Word of warning: this data structure does not guard against nulls and bad inputs

import java.util.List;
import java.util.ArrayList;

public class GraphNode implements Comparable {
  private double x;
  private double y;
  private String name;
  private List<GraphEdge> edges;
  private double predictedDistance;
  private double actualDistance;

  GraphNode(double x, double y, String name) {
    this.x = x;
    this.y = y;
    this.name = name;
    this.edges = new ArrayList<GraphEdge>();
    this.predictedDistance = 0.0;
    this.actualDistance = 0.0;
  }

  public double getX() { return this.x; }
  public double getY() { return this.y; }
  public String getName() { return this.name; }
  public List<GraphEdge> getEdges() { return this.edges; }
  public double getPredictedDistance() { return this.predictedDistance; }
  public double getActualDistance() { return this.actualDistance; }

  public void setPredictedDistance(double distance) {
    this.predictedDistance = distance;
  }

  public void setActualDistance(double distance) {
    this.actualDistance = distance;
  }

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

  public double getDistanceTo(GraphNode n) {
    for (GraphEdge e : getEdges()) {
      if (e.getEnd().equals(n)) {
        return e.getLength();
      }
    }
    return (Double)null;
  }

  public boolean equals(Object o) {
    if (!(o instanceof GraphNode) || (o == null)) {
      return false;
    }
    GraphNode node = (GraphNode)o;
    return ((node.getX() == this.getX()) && (node.getY() == this.getY()));
  }

  public int compareTo(Object o) {
    GraphNode m = (GraphNode)o;
    return ((Double)this.getPredictedDistance()).compareTo((Double) m.getPredictedDistance());
  }
}
