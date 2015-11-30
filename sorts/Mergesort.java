import java.util.ArrayList;

public class Mergesort {

  Mergesort() { }

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

}
