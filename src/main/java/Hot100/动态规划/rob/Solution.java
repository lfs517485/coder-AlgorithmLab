package Hot100.动态规划.rob;

public class Solution {
    public static int rob(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        if(nums.length == 1) return dp[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < nums.length; i++){
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++){
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        int ans = rob(nums);
        System.out.println(ans);
    }
}