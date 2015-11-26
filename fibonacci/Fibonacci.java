import java.util.Scanner;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fibonacci {
  public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in);
    System.out.print("Please enter any amount of numbers that you would like to have their position in the Fibonacci sequence calculated: ");

    String rawValue = userInput.nextLine();
    String[] arrayOfNums = rawValue.split(" ");
    int[] arrayOfInts = new int[arrayOfNums.length];

    for (int i = 0; i < arrayOfNums.length; i++) {
      arrayOfInts[i] = Integer.parseInt(arrayOfNums[i]);
    }

    System.out.print("Would you like to use a recursive or iterative approach to determining your Fibonacci number? (enter Recursive or Iterative) ");
    String approachInput = userInput.next();

    Pattern approachPtn = Pattern.compile("(?i)recursive");
    Matcher approachM = approachPtn.matcher(approachInput);

    System.out.print("Would you like to use a memoized approach or not? (enter Yes or No) ");
    String memoInput = userInput.next();

    Pattern memoPtn = Pattern.compile("(?i)yes");
    Matcher memoM = memoPtn.matcher(memoInput);
    boolean memoized = memoM.matches();

    if (approachM.matches()) {
      RecursiveFibonacci fib = new RecursiveFibonacci(arrayOfInts, memoized);
    } else {
      IterativeFibonacci fib = new IterativeFibonacci(arrayOfInts, memoized);
    }

    System.out.println("K, Done!");
  }
}
