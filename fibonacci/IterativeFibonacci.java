import java.math.BigInteger;
import java.util.ArrayList;

public class IterativeFibonacci {
  int[] nums;
  ArrayList<Integer> arrayNums;
  boolean memoized;

  IterativeFibonacci(int[] n, boolean memoize) {
    nums = n;
    memoized = memoize;

    if (memoized == false) {
      for (int i = 0; i < nums.length; i++) {
        System.out.println("Here's your Fibonacci number for position " + nums[i] + ": " + calc(nums[i]));
      }
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
      BigInteger index0 = BigInteger.valueOf(0);
      BigInteger index1 = BigInteger.valueOf(1);

      for (int i = 2; i <= num; i++) {
        ret = index0.add(index1);
        index0 = index1;
        index1 = ret;
      }
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
          BigInteger result = memo.get(i - 1).add(memo.get(i - 2));
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
