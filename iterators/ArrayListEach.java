import java.util.ArrayList;
import java.util.Random;

public class ArrayListEach {
  ArrayList<Integer> nums;

  interface Iterator {
    ArrayList<Integer> each(ArrayListEach ale);
  }

  ArrayListEach() {
    this.nums = new ArrayList<Integer>();

    Random rand = new Random();
    for (int i = 0; i < 10; i++) {
      this.nums.add(rand.nextInt(100));
    }
  }

  private static ArrayList<Integer> apply(ArrayListEach ale, Iterator op) {
    return op.each(ale);
  }

  public static void main(String[] args) {
    ArrayListEach aleInstance = new ArrayListEach();
    Iterator addSeven = (ale) -> {
      ArrayList<Integer> retArray = new ArrayList<Integer>();
      for (int i = 0; i < ale.nums.size(); i++) {
        int x = ale.nums.get(i);
        x = x + 7;
        retArray.add(x);
      }
      return retArray;
    };
    System.out.println("Here's the original array: " + aleInstance.nums);
    System.out.println("Here's the array after iteration: " + apply(aleInstance, addSeven));
  }
}
