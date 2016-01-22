import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Graph {
  private int numVertices;
  private int numEdges;

  public Graph() {
    this.numVertices = 0;
    this.numEdges = 0;
  }

  public int getNumVertices() { return this.numVertices; }
  public int getNumEdges() { return this.numEdges; }

  public void addVertex() {
    implementAddVertex();
    numVertices++;
  }

  public void addEdge(int v, int w) {
    implementAddEdge(v, w);
    numEdges++;
  }

  public Map<Integer,Integer> iterativeBFS(int s, int g) {
    Queue<Integer> queue = new LinkedList<Integer>();
    HashSet<Integer> visited = new HashSet<Integer>();
    Map<Integer,Integer> parents = new HashMap<Integer,Integer>();
    int curr;

    queue.add(s);
    visited.add(s);
    while (!queue.isEmpty()) {
      curr = queue.remove();
      if (curr == g) { return parents; }
      List<Integer> neighbors = getOutNeighbors(curr);
      for (int n : neighbors) {
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

  public Map<Integer,Integer> iterativeDFS(int s, int g) {
    Stack<Integer> stack = new Stack<Integer>();
    HashSet<Integer> visited = new HashSet<Integer>();
    Map<Integer,Integer> parents = new HashMap<Integer,Integer>();
    int curr;

    stack.push(s);
    visited.add(s);
    while (!stack.isEmpty()) {
      curr = stack.pop();
      if (curr == g) { return parents; }
      List<Integer> neighbors = getOutNeighbors(curr);
      for (int n : neighbors) {
        if (visited.contains(n)) {
          continue;
        } else {
          visited.add(n);
          parents.put(n, curr);
          stack.push(n);
        }
      }
    }
    return null;
  }

  public Map<Integer,Integer> recursiveDFS(int s, int g) {
    HashSet<Integer> visited = new HashSet<Integer>();
    Map<Integer,Integer> parents = new HashMap<Integer,Integer>();

    return recursiveDFS(s, g, visited, parents);
  }

  private Map<Integer,Integer> recursiveDFS(int s, int g, HashSet<Integer> visited, Map<Integer,Integer> parents) {
    if (s == g) {
      return parents;
    }

    List<Integer> neighbors = getOutNeighbors(s);
    for (int n : neighbors) {
      if (!visited.contains(n)) {
        visited.add(n);
        parents.put(n, s);
        return recursiveDFS(n, g, visited, parents);
      }
    }
    return null;
  }

  public abstract void implementAddVertex();
  public abstract void implementAddEdge(int v, int w);
  public abstract List<Integer> getInNeighbors(int v);
  public abstract List<Integer> getOutNeighbors(int v);
}
