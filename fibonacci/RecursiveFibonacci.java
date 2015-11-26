import java.math.BigInteger;
import java.util.ArrayList;

class RecursiveFibonacci {
  int[] nums;
  ArrayList<Integer> arrayNums;
  boolean memoized;

  RecursiveFibonacci(int[] n, boolean memoize) {
    nums = n;
    memoized = memoize;

    if (memoized == false) {
      ArrayList<BigInteger> ret = new ArrayList<BigInteger>();
      for (int i = 0; i < nums.length; i++) {
        ret.add(calc(nums[i]));
      }
      System.out.println("Here are your Fibonacci numbers: " + ret);
    } else {
      arrayNums = new ArrayList<Integer>();
      for (int num : n) {
        arrayNums.add(num);
      }
      System.out.println("Here are your Fibonacci numbers: " + memoizedCalc(arrayNums));
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

  public ArrayList<BigInteger> memoizedCalc(ArrayList<Integer> nums) {
    ArrayList<BigInteger> ret = new ArrayList<BigInteger>();
    ArrayList<BigInteger> memo = new ArrayList<BigInteger>();
    memo.add(BigInteger.valueOf(0));
    memo.add(BigInteger.valueOf(1));

    for (int n : nums) {
      if (n < 2) {
        ret.add(BigInteger.valueOf(n));
      } else {
        for (int i = 2; i <= n; i++) {
          BigInteger result = calc(n - 1).add(calc(n - 2));
          if (i >= memo.size()) {
            memo.add(result);
          }
          if (i == n) {
            ret.add(result);
          }
        }
      }
    }
    return ret;
  }
}
