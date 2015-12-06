import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class HashMapEach {
  ArrayList<Integer> nums;

  interface Iterator {
    HashMap<String, Integer> each(HashMapEach hme);
  }

  HashMapEach() {
    this.nums = new ArrayList<Integer>();

    Random rand = new Random();
    for (int i = 0; i < 10; i++) {
      this.nums.add(rand.nextInt(100));
    }
  }

  private static HashMap<String, Integer> apply(HashMapEach hme, Iterator op) {
    return op.each(hme);
  }

  public static void main(String[] args) {
    HashMapEach hmeInstance = new HashMapEach();
    Iterator addSeven = (hme) -> {
      HashMap<String, Integer> retHashMap = new HashMap<String, Integer>();
      for (int i = 0; i < hme.nums.size(); i++) {
        int x = hme.nums.get(i);
        x = x + 7;
        retHashMap.put(String.valueOf(i), x);
      }
      return retHashMap;
    };
    System.out.println("Here's the original array: " + hmeInstance.nums);
    System.out.println("Here's the resulting HashMap after iteration: " + apply(hmeInstance, addSeven));
  }
}
