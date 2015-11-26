import java.math.BigInteger;

public class RecursiveFibonacci {
  int[] nums;

  RecursiveFibonacci(int[] n) {
    nums = n;

    for (int i = 0; i < nums.length; i++) {
      System.out.println("Here's your Fibonacci number for position " + nums[i] + ": " + calc(nums[i]));
    }
  }

  public BigInteger calc(int num) {
    BigInteger ret = BigInteger.valueOf(0);

    if (num < 2) {
      return BigInteger.valueOf(num);
    } else {
      ret = calc(num - 1).add(calc(num - 2));
      return ret;
    }
  }

}
