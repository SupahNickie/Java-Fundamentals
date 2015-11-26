import java.math.BigInteger;
import java.util.ArrayList;

class RecursiveFibonacci {
  int[] nums;
  boolean memoized;
  ArrayList<Integer> arrayNums;

  RecursiveFibonacci(int[] n, boolean memoize) {
    this.nums = n;
    this.memoized = memoize;
    this.arrayNums = new ArrayList<Integer>();

    if (this.memoized == false) {
      ArrayList<BigInteger> ret = new ArrayList<BigInteger>();
      for (int i = 0; i < this.nums.length; i++) {
        ret.add(calc(this.nums[i], new ArrayList<BigInteger>()));
      }
      System.out.println("Here are your Fibonacci numbers: " + ret);
    } else {
      arrayNums = new ArrayList<Integer>();
      for (int num : this.nums) {
        arrayNums.add(num);
      }
      System.out.println("Here are your Fibonacci numbers: " + memoizedCalc(arrayNums));
    }
  }

  public BigInteger calc(int num, ArrayList<BigInteger> memo) {
    if (num < 2) {
      return BigInteger.valueOf(num);
    } else if (num < memo.size()) {
      return memo.get(num);
    } else {
      BigInteger ret = calc(num - 1, memo).add(calc(num - 2, memo));
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
          BigInteger result = calc(i, memo);
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
