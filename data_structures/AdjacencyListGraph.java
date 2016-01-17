import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class AdjacencyListGraph extends Graph {
  private Map<Integer,ArrayList<Integer>> adjListsMap;

  AdjacencyListGraph() {
    super();
    this.adjListsMap = new HashMap<Integer,ArrayList<Integer>>();
  }

  public void implementAddVertex() {
    int v = getNumVertices();
    ArrayList<Integer> neighbors = new ArrayList<Integer>();
    adjListsMap.put(v, neighbors);
  }

  public void implementAddEdge(int v, int w) {
    (adjListsMap.get(v)).add(w);
  }

  public List<Integer> getInNeighbors(int v) {
    List<Integer> neighbors = new ArrayList<Integer>();
    for (int i : adjListsMap.keySet()) {
      for (int j : adjListsMap.get(i)) {
        if (v == j) {
          neighbors.add(i);
        }
      }
    }
    return neighbors;
  }

  public List<Integer> getOutNeighbors(int v) {
    return new ArrayList<Integer>(adjListsMap.get(v));
  }
}
