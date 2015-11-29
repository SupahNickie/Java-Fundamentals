import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Quicksort {
  ArrayList<Integer> nums;

  Quicksort() {
    this.nums = new ArrayList<Integer>();
  }

  private void addToNums(int n) {
    this.nums.add(n);
  }

  public void setupSort(int n, int max) {
    Random rand = new Random();
    for (int i = 1; i <= n; i++) {
      int answer = rand.nextInt((max * 2) + 1) - max;
      addToNums(answer);
    }
  }

  public ArrayList<Integer> sort(ArrayList<Integer> array) {
    return this.sort(array, 0, array.size() - 1);
  }

  public ArrayList<Integer> sort(ArrayList<Integer> array, int leftInd, int rightInd) {
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

  public PivotReturn partitionArray(ArrayList<Integer> array, int leftInd, int rightInd, int pivotInd) {
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

  public class PivotReturn {
    public int ind;
    public ArrayList<Integer> array;

    public PivotReturn(int ind, ArrayList<Integer> array) {
      this.ind = ind;
      this.array = array;
    }
  }

  public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in);
    System.out.print("How many random numbers would you like generated? ");
    int n = Integer.parseInt(userInput.next());
    System.out.print("What upper bound to the numbers would you like to use? ");
    int max = Integer.parseInt(userInput.next());
    Quicksort q = new Quicksort();
    q.setupSort(n, max);
    System.out.println("K, sorting");
    System.out.println(q.sort(q.nums));
  }
}
