import java.util.List;
import java.util.ArrayList;

public class AdjacencyMatrixGraph extends Graph {
  private final int defaultNumVertices = 5;
  private int[][] adjMatrix;

  AdjacencyMatrixGraph() {
    super();
    this.adjMatrix = new int[defaultNumVertices][defaultNumVertices];
  }

  AdjacencyMatrixGraph(int size) {
    super();
    this.adjMatrix = new int[size][size];
  }

  public void implementAddVertex() {
    int v = getNumVertices();
    if (v >= adjMatrix.length) {
      int[][] newAdjxMatrix = new int[v*2][v*2];
      for (int i = 0; i < adjMatrix.length; i++) {
        for (int j = 0; j < adjMatrix.length; j++) {
          newAdjxMatrix[i][j] = adjMatrix[i][j];
        }
      }
    adjMatrix = newAdjxMatrix;
    }
    for (int i = 0; i < adjMatrix[v].length; i++) {
      adjMatrix[v][i] = 0;
    }
  }

  public void implementAddEdge(int v, int w) {
    adjMatrix[v][w] += 1;
  }

  public List<Integer> getInNeighbors(int v) {
    List<Integer> neighbors = new ArrayList<Integer>();
    for (int i = 0; i < getNumVertices(); i++) {
      for (int j = 0; j< adjMatrix[i][v]; j++) {
        neighbors.add(i);
      }
    }
    return neighbors;
  }

  public List<Integer> getOutNeighbors(int v) {
    List<Integer> neighbors = new ArrayList<Integer>();
    for (int i = 0; i < getNumVertices(); i++) {
      for (int j = 0; j< adjMatrix[v][i]; j++) {
        neighbors.add(i);
      }
    }
    return neighbors;
  }
}
