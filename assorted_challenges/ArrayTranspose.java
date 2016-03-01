import java.util.List;
import java.util.ArrayList;

public class ArrayTranspose {

  public static List<ArrayList<Integer>> transpose(int[][] args) {
    int height = args.length;
    int width = args[0].length;
    List<ArrayList<Integer>> container = new ArrayList<ArrayList<Integer>>();

    for(int i = 0; i < width; i++) {
      ArrayList<Integer> subContainer = new ArrayList<Integer>();
      container.add(subContainer);
    }

    for(int i = 0; i < width; i++) {
      List<Integer> sub = container.get(i);
      for(int j = 0; j < height; j++) {
        sub.add(args[j][i]);
      }
    }

    return container;
  }

  public static void main(String[] args) {
    int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    int[][] array2 = {{1, 2, 3}, {4, 5, 6}};
    System.out.println(transpose(array));
    System.out.println(transpose(array2));
  }
}
