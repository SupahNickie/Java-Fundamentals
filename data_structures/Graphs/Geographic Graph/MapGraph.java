// Word of warning: this data structure does not guard against nulls and bad inputs

import java.util.Queue;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class MapGraph {
  private int numVertices;
  private int numEdges;
  private List<GraphNode> nodes;
  private Map<String, GraphNode> nodesByName;

  MapGraph() {
    this.numVertices = 0;
    this.numEdges = 0;
    this.nodes = new ArrayList<GraphNode>();
    this.nodesByName = new HashMap<String, GraphNode>();
  }

  public int getNumVertices() { return this.numVertices; }
  public int getNumEdges() { return this.numEdges; }
  public List<GraphNode> getNodes() { return this.nodes; }
  public Map<String, GraphNode> getNodesByName() { return this.nodesByName; }

  public void addNode(double x, double y, String name) {
    GraphNode newNode = new GraphNode(x, y, name);
    getNodes().add(newNode);
    getNodesByName().put(name, newNode);
    numVertices++;
  }

  public void addEdge(GraphNode start, GraphNode end, double length, String name) {
    GraphEdge newEdge = new GraphEdge(start, end, length, name);
    start.addEdge(newEdge);
    numEdges++;
  }

  public List<GraphNode> iterativeBFS(GraphNode start, GraphNode goal) {
    Queue<GraphNode> queue = new LinkedList<GraphNode>();
    HashSet<GraphNode> visited = new HashSet<GraphNode>();
    Map<GraphNode,GraphNode> parents = new HashMap<GraphNode,GraphNode>();
    GraphNode curr;

    queue.add(start);
    visited.add(start);
    while (!queue.isEmpty()) {
      curr = queue.remove();
      if (curr == goal) { break; }
      List<GraphNode> neighbors = curr.getNeighbors();
      for (GraphNode n : neighbors) {
        if (visited.contains(n)) {
          continue;
        } else {
          visited.add(n);
          parents.put(n, curr);
          queue.add(n);
        }
      }
    }

    if (parents == null) {
      return null;
    } else {
      return packagePath(start, goal, parents);
    }
  }

  public List<GraphNode> dijkstra(GraphNode startNode, GraphNode goalNode) {
    if (startNode == null || goalNode == null) {
      throw new NullPointerException("Cannot find route from or to null node");
    }

    HashMap<GraphNode,GraphNode> parents = new HashMap<GraphNode,GraphNode>();
    PriorityQueue<GraphNode> queue = new PriorityQueue<GraphNode>();
    HashSet<GraphNode> visited = new HashSet<GraphNode>();
    for (GraphNode n : getNodes()) {
      n.setPredictedDistance(Double.POSITIVE_INFINITY);
    }
    startNode.setPredictedDistance(0);
    queue.add(startNode);
    GraphNode curr = null;
    int count = 0;

    while (!queue.isEmpty()) {
      curr = queue.remove();
      if (!visited.contains(curr)) {
        visited.add(curr);
        count++;
        if (curr.equals(goalNode)) { break; }
        List<GraphNode> neighbors = curr.getNeighbors();
        for (GraphNode neighbor : neighbors) {
          if (!visited.contains(neighbor)) {
            double actualDistance = curr.getActualDistance() + curr.getDistanceTo(neighbor);
            if ( actualDistance < neighbor.getPredictedDistance()) {
              neighbor.setPredictedDistance(actualDistance);
              parents.put(neighbor, curr);
              queue.add(neighbor);
            }
          }
        }
      }
    }

    if (!curr.equals(goalNode)) {
      System.out.println("No path found from " + startNode + " to " + goalNode);
      return null;
    }

    System.out.println("A Star search visited " + count + " nodes.");

    if (parents == null) {
      return null;
    } else {
      return packagePath(startNode, goalNode, parents);
    }
  }

  public List<GraphNode> aStar(GraphNode startNode, GraphNode goalNode) {
    if (startNode == null || goalNode == null) {
      throw new NullPointerException("Cannot find route from or to null node");
    }

    HashMap<GraphNode,GraphNode> parents = new HashMap<GraphNode,GraphNode>();
    PriorityQueue<GraphNode> queue = new PriorityQueue<GraphNode>();
    HashSet<GraphNode> visited = new HashSet<GraphNode>();
    for (GraphNode n : getNodes()) {
      n.setPredictedDistance(Double.POSITIVE_INFINITY);
      n.setActualDistance(Double.POSITIVE_INFINITY);
    }
    startNode.setPredictedDistance(0);
    startNode.setActualDistance(0);
    queue.add(startNode);
    GraphNode curr = null;
    int count = 0;

    while (!queue.isEmpty()) {
      curr = queue.remove();
      if (!visited.contains(curr)) {
        visited.add(curr);
        count++;
        if (curr.equals(goalNode)) { break; }
        List<GraphNode> neighbors = curr.getNeighbors();
        for (GraphNode neighbor : neighbors) {
          if (!visited.contains(neighbor)) {
            double actualDistance = curr.getActualDistance() + curr.getDistanceTo(neighbor);
            double xDistance = Math.abs(neighbor.getX() - goalNode.getX());
            double yDistance = Math.abs(neighbor.getY() - goalNode.getY());
            double predictedDistance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
            if ( actualDistance + predictedDistance < neighbor.getPredictedDistance()) {
              neighbor.setPredictedDistance(actualDistance + predictedDistance);
              neighbor.setActualDistance(actualDistance);
              parents.put(neighbor, curr);
              queue.add(neighbor);
            }
          }
        }
      }
    }

    if (!curr.equals(goalNode)) {
      System.out.println("No path found from " + startNode + " to " + goalNode);
      return null;
    }

    System.out.println("A Star search visited " + count + " nodes.");

    if (parents == null) {
      return null;
    } else {
      return packagePath(startNode, goalNode, parents);
    }
  }

  private List<GraphNode> packagePath(GraphNode start, GraphNode goal, Map<GraphNode, GraphNode> rawPath) {
    if (!rawPath.isEmpty()) {
      List<GraphNode> path = new LinkedList<GraphNode>();
      path.add(0, goal);
      GraphNode curr = goal;
      while (!path.contains(start)) {
        path.add(0, rawPath.get(curr));
        curr = rawPath.get(curr);
      }
      return path;
    }
    return null;
  }

}
