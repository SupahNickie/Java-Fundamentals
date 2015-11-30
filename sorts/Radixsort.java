import java.util.ArrayList;

public class Radixsort {

  Radixsort() { }

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
        int num = arrayCopy.get(j);
        int bucketDigit = ((Math.abs(num) / base) % 10);
        if (num >= 0) {
          bucketDigit += 10;
        } else if (num < 0) {
          bucketDigit = Math.abs(bucketDigit -= 10);
        }
        buckets.get(bucketDigit).add(num);
      }
      arrayCopy = flatten(buckets);
    }
    return arrayCopy;
  }

  private ArrayList<Integer> flatten(ArrayList<ArrayList<Integer>> buckets) {
    ArrayList<Integer> ret = new ArrayList<Integer>();

    for(int i = 0; i < buckets.size(); i++) {
      ret.addAll(buckets.get(i));
    }
    return ret;
  }

  private int findLongestNumber(ArrayList<Integer> array) {
    int min = 0;
    int max = 0;
    for(int i = 0; i < array.size(); i++) {
      if (array.get(i) > max) {
        max = array.get(i);
      } else if (array.get(i) < min) {
        min = array.get(i);
      }
    }
    int minLength = String.valueOf(Math.abs(min)).length();
    int maxLength = String.valueOf(max).length();
    if (minLength > maxLength) {
      return minLength;
    } else {
      return maxLength;
    }
  }

}


