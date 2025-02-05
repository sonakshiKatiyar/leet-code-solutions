package LeetPractices.array;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class ProductOfArrayExceptSelf {

    /**
     * https://leetcode.com/problems/product-of-array-except-self
     * 238, Medium
     * we need to find the prefix product and suffix product and multiply both
     * To solve it in SC:O(1) we need to put the prefix product in result array and to calculate final ans we
     * will traverse from end and keep on storing the suffix product in a var .
     */
    public static void main(String[] args) {
        productExceptSelf(new int[]{1, 2, 3, 4});
    }

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int prefix = 1;
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = prefix * nums[i - 1];
            prefix = res[i];
        }
        prefix = nums[n - 1];
        for (int j = n - 2; j >= 0; j--) {
            res[j] = res[j] * prefix;
            prefix = prefix * nums[j];
        }
        System.out.println(Arrays.stream(res)
                .mapToObj(String::valueOf)
                .collect(joining(",")));
        return res;
    }
}
