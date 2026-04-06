package Hot100.普通数组.maxSubArray;

public class Solution {
    public static int maxSubArray(int[] nums) {

        int[] dp = new int[nums.length];//dp[i]：以i结尾的最大连续子数组和

        dp[0] = nums[0];//dp数组初始化
        //dp状态转移
        for(int i = 1; i < nums.length; i++){
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }

        //迭代答案
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++){
            ans = Math.max(ans, dp[i]);
        }

        return ans;

    }

    public static void main(String[] args) {
        int[] sums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(sums));
    }
}
