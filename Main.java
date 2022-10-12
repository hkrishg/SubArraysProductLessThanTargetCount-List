import java.util.*;

class Main {
  // Given an array with positive numbers and a target number, find all of its
  // contiguous subarrays whose product is less than the target number.

  // Example 1:

  // Input: [2, 5, 3, 10], target=30
  // Output: [2], [5], [2, 5], [3], [5, 3], [10]
  // Explanation: There are six contiguous subarrays whose product is less than
  // the target.
  // Example 2:

  // Input: [8, 2, 6, 5], target=50
  // Output: [8], [2], [8, 2], [6], [2, 6], [5], [6, 5]
  // Explanation: There are seven contiguous subarrays whose product is less than
  // the target.


//   Input: nums = [10,5,2,6], k = 100
// Output: 8
// Explanation: The 8 subarrays that have product less than 100 are:
// [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
// Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
  
//   The idea is always keep an max-product-window less than K;
// Every time shift window by adding a new number on the right(j), if the product is greater than k, then try to reduce numbers on the left(i), until the subarray product fit less than k again, (subarray could be empty);
// Each step introduces x new subarrays, where x is the size of the current window (j + 1 - i);
// example:
// for window (5, 2), when 6 is introduced, it add 3 new subarray: (5, (2, (6)))

  public static int numSubarrayProductLessThanK(int[] nums, int k) {
    if (k == 0)
      return 0;
    int cnt = 0;
    int pro = 1;
    int windowStart = 0;
    for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
      pro *= nums[windowEnd];
      while (windowStart <= windowEnd && pro >= k) {
        pro /= nums[windowStart++];
      }
      cnt += windowEnd - windowStart + 1;
    }
    return cnt;
  }

  public static List<List<Integer>> listSubarrayProductLessThanK(int[] nums, int k) {
    List<List<Integer>> list = new ArrayList<>();

    int pro = 1;
    int windowStart = 0;
    for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
      pro *= nums[windowEnd];
      while (windowStart < nums.length && pro >= k) {
        pro /= nums[windowStart++];
      }
      List<Integer> tempList = new LinkedList<>();
      for (int i = windowEnd; i >= windowStart; i--) {
        tempList.add(0, nums[i]);
        list.add(new ArrayList<>(tempList));
      }
    }
    return list;
  }

  public static void main(String[] args) {
    System.out.println(Main.numSubarrayProductLessThanK(new int[] { 2, 5, 3, 10
    }, 30));
    System.out.println(Main.numSubarrayProductLessThanK(new int[] { 8, 2, 6, 5 },
    50));
    System.out.println(Main.listSubarrayProductLessThanK(new int[] { 2, 5, 3, 10 }, 30));
    System.out.println(Main.listSubarrayProductLessThanK(new int[] { 8, 2, 6, 5 }, 50));

  }
}