import java.util.ArrayList;

public class Quicksort {

  Quicksort() { }

  public ArrayList<Integer> sort(ArrayList<Integer> array) {
    return this.sort(array, 0, array.size() - 1);
  }

  private ArrayList<Integer> sort(ArrayList<Integer> array, int leftInd, int rightInd) {
    if (leftInd < rightInd) {
      int midpoint = (leftInd + (rightInd - leftInd)/2);
      PivotReturn pr = partitionArray(array, leftInd, rightInd, midpoint);
      int newPivotValue = pr.ind;
      array = pr.array;
      sort(array, leftInd, newPivotValue - 1);
      sort(array, newPivotValue + 1, rightInd);
    }
    return array;
  }

  private PivotReturn partitionArray(ArrayList<Integer> array, int leftInd, int rightInd, int pivotInd) {
    int pivotVal = array.get(pivotInd);
    int rightVal = array.get(rightInd);
    array.set(pivotInd, rightVal);
    array.set(rightInd, pivotVal);
    int storedInd = leftInd;
    for (int i = leftInd; i < rightInd; i++) {
      if (array.get(i) < array.get(rightInd)) {
        int storedVal = array.get(storedInd);
        int n = array.get(i);
        array.set(i, storedVal);
        array.set(storedInd, n);
        storedInd++;
      }
    }
    rightVal = array.get(rightInd);
    int storedVal = array.get(storedInd);
    array.set(storedInd, rightVal);
    array.set(rightInd, storedVal);
    return new PivotReturn(storedInd, array);
  }

  private class PivotReturn {
    public int ind;
    public ArrayList<Integer> array;

    PivotReturn(int ind, ArrayList<Integer> array) {
      this.ind = ind;
      this.array = array;
    }
  }

}
