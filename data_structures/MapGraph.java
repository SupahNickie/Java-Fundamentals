// Word of warning: this data structure does not guard against nulls and bad inputs

import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
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
    Map<GraphNode, GraphNode> rawPath = performBFS(start, goal);

    if (rawPath == null) {
      return null;
    } else {
      return packageBFSPath(start, goal, rawPath);
    }
  }

  private Map<GraphNode, GraphNode> performBFS(GraphNode start, GraphNode goal) {
    Queue<GraphNode> queue = new LinkedList<GraphNode>();
    HashSet<GraphNode> visited = new HashSet<GraphNode>();
    Map<GraphNode,GraphNode> parents = new HashMap<GraphNode,GraphNode>();
    GraphNode curr;

    queue.add(start);
    visited.add(start);
    while (!queue.isEmpty()) {
      curr = queue.remove();
      if (curr == goal) { return parents; }
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
    return null;
  }

  private List<GraphNode> packageBFSPath(GraphNode start, GraphNode goal, Map<GraphNode, GraphNode> rawPath) {
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

  public List<GraphNode> dijkstra(GraphNode start, GraphNode goal) {
    return null;
  }

  public List<GraphNode> aStar(GraphNode start, GraphNode goal) {
    return null;
  }
}
