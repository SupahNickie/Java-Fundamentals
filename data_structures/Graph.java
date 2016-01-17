import java.util.List;

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

  public abstract void implementAddVertex();
  public abstract void implementAddEdge(int v, int w);
  public abstract List<Integer> getInNeighbors(int v);
  public abstract List<Integer> getOutNeighbors(int v);
}
