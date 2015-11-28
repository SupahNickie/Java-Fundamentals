import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Mergesort {
  ArrayList<Integer> nums;

  Mergesort() {
    this.nums = new ArrayList<Integer>();
  }

  private void addToNums(int n) {
    this.nums.add(n);
  }

  public void setupSort(int n, int max) {
    Random rand = new Random();
    for (int i = 1; i <= n; i++) {
      int answer = rand.nextInt(max + 1);
      addToNums(answer);
    }
  }

  public ArrayList<Integer> sort(ArrayList<Integer> array) {
    if (array.size() == 1) {
      return array;
    } else {
      int midpoint = (array.size() / 2);
      ArrayList<Integer> left = new ArrayList<Integer>(array.subList(0, midpoint));
      ArrayList<Integer> right = new ArrayList<Integer>(array.subList(midpoint, array.size()));
      return merge(sort(left), sort(right));
    }
  }

  public ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    while (left.size() > 0 || right.size() > 0) {
      if (left.size() > 0 && right.size() > 0) {
        if (left.get(0) > right.get(0)) {
          result.add(right.remove(0));
        } else {
          result.add(left.remove(0));
        }
      } else {
        break;
      }
    }

    result.addAll(left);
    result.addAll(right);
    return result;
  }

  public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in);
    System.out.print("How many random numbers would you like generated? ");
    int n = Integer.parseInt(userInput.next());
    System.out.print("What upper bound to the numbers would you like to use? ");
    int max = Integer.parseInt(userInput.next());
    Mergesort m = new Mergesort();
    m.setupSort(n, max);
    System.out.println("K, sorting");
    System.out.println(m.sort(m.nums));
  }
}
