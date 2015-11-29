import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Radixsort {
  ArrayList<Integer> nums;

  Radixsort() {
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
    ArrayList<Integer> arrayCopy = new ArrayList<Integer>(array);
    int amountOfPasses = findLongestNumber(arrayCopy);
    for(int i = 0; i < amountOfPasses; i++) {
      ArrayList<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>();
      for(int n = 0; n < 20; n++) {
        buckets.add(new ArrayList<Integer>());
      }
      int base = (int) Math.pow(10, i);
      for(int j = 0; j < arrayCopy.size(); j++) {
        int bucketDigit = Math.floorMod((arrayCopy.get(j) / base), 10);
        if (arrayCopy.get(j) >= 0) {
          bucketDigit += 10;
        } else if ((String.valueOf(arrayCopy.get(j)).length() < (i + 1)) && arrayCopy.get(j) < 0) {
          bucketDigit += 9;
        }
        buckets.get(bucketDigit).add(arrayCopy.get(j));
      }
      arrayCopy = flatten(buckets);
    }
    return arrayCopy;
  }

  public ArrayList<Integer> flatten(ArrayList<ArrayList<Integer>> buckets) {
    ArrayList<Integer> ret = new ArrayList<Integer>();

    for(int i = 0; i < buckets.size(); i++) {
      ret.addAll(buckets.get(i));
    }
    return ret;
  }

  public int findLongestNumber(ArrayList<Integer> array) {
    int min = 0;
    int max = 0;
    for(int i = 0; i < array.size(); i++) {
      if (array.get(i) > max) {
        max = array.get(i);
      } else if (array.get(i) < min) {
        min = array.get(i);
      }
    }
    int minLength = String.valueOf(min).length();
    int maxLength = String.valueOf(max).length();
    if (minLength > maxLength) {
      return minLength;
    } else {
      return maxLength;
    }
  }

  public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in);
    System.out.print("How many random numbers would you like generated? ");
    int n = Integer.parseInt(userInput.next());
    System.out.print("What upper bound to the numbers would you like to use? ");
    int max = Integer.parseInt(userInput.next());
    Radixsort r = new Radixsort();
    r.setupSort(n, max);
    System.out.println("K, sorting");
    System.out.println(r.sort(r.nums));
  }
}


