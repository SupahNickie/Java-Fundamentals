import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sorts {
  ArrayList<Integer> nums;

  Sorts() {
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

  public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in);
    System.out.print("How many random numbers would you like generated? ");
    int n = Integer.parseInt(userInput.next());
    System.out.print("What upper bound to the numbers would you like to use? ");
    int max = Integer.parseInt(userInput.next());
    Sorts s = new Sorts();
    s.setupSort(n, max);
    System.out.print("Which sorting algorithm would you like to use? ");
    String input = userInput.next();

    Pattern mergePtn = Pattern.compile("(?i)merge");
    Matcher mergeM = mergePtn.matcher(input);
    Pattern quickPtn = Pattern.compile("(?i)quick");
    Matcher quickM = quickPtn.matcher(input);
    Pattern radixPtn = Pattern.compile("(?i)radix");
    Matcher radixM = radixPtn.matcher(input);

    if (mergeM.matches()) {
      Mergesort x = new Mergesort();
      System.out.println("K, sorting with Mergesort");
      System.out.println(x.sort(s.nums));
    } else if (quickM.matches()) {
      Quicksort x = new Quicksort();
      System.out.println("K, sorting with Quicksort");
      System.out.println(x.sort(s.nums));
    } else if (radixM.matches()) {
      System.out.println("K, sorting with Radixsort");
      Radixsort x = new Radixsort();
      System.out.println(x.sort(s.nums));
    } else {
      System.out.println("Sorry, didn't catch that. Please run me again.");
      System.exit(0);
    }
  }

}
