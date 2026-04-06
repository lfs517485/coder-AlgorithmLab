package Hot100.动态规划.maxProduct;

public class Solution {
    public static int maxProduct(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int[] maxDP = new int[nums.length];
        int[] minDP = new int[nums.length];

        maxDP[0] = nums[0];
        minDP[0] = nums[0];

        for(int i = 1 ; i < nums.length; i++){
            maxDP[i] = Math.max(maxDP[i - 1] * nums[i], Math.max(minDP[i - 1] * nums[i], nums[i]));
            minDP[i] = Math.min(minDP[i - 1] * nums[i], Math.min(maxDP[i - 1] * nums[i], nums[i]));
        }

        for(int i = 0; i < nums.length; i++){
            ans = Math.max(ans, maxDP[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};
        int ans = maxProduct(nums);
        System.out.println(ans);
    }
}