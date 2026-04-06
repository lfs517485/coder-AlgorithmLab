package Hot100.动态规划.canPartition;

class Solution {

    /**
     * 问题本质：
     * 0-1背包问题的变形——在数组中挑选若干个数，使其和等于总和的一半。
     * <p>
     * 核心思路：
     * 1. 如果数组总和为奇数，直接返回false（无法平分整数）
     * 2. 如果最大值 > 总和/2，直接返回false（这个数无法放入任何子集）
     * 3. 定义dp[i][j]：从数组前i个元素（[0,i-1]）中能否选出若干个数的和为j
     */
    public static boolean canPartition(int[] nums) {
        // ----- 步骤1：边界条件判断 -----
        // 数组长度小于2，无法分割成两个非空子集
        if (nums.length < 2) {
            return false;
        }

        // ----- 步骤2：计算总和与最大值 -----
        int sum = 0;
        int maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }

        // ----- 步骤3：剪枝优化 -----
        // 如果总和是奇数，不可能平分成两个整数和
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;  // 目标值：需要凑出的和

        // 如果最大值超过了目标值，那么其他数的和必然小于目标值
        // 这个最大值无处安放，无法平分
        if (maxNum > target) {
            return false;
        }

        // ----- 步骤4：初始化DP数组 -----
        // dp[i][j]：前i个元素（下标0到i-1）能否选出若干数，使其和为j
        boolean[][] dp = new boolean[nums.length][target + 1];

        // 初始化第0行：只考虑第一个元素nums[0]
        // 如果第一个元素值不超过目标值，那么可以选它来凑出nums[0]
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        // 初始化所有行的第0列：不选任何元素时，和一定是0
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }

        // ----- 步骤5：状态转移 -----
        // 遍历每个元素（从第二个元素开始，下标1）
        for (int i = 1; i < nums.length; i++) {
            // 遍历所有可能的目标和j（1到target）
            for (int j = 1; j <= target; j++) {
                if (nums[i] > j) {
                    // 当前数字太大，放不进背包
                    // 只能选择不取当前元素，状态继承自前i-1个元素
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 当前数字可以放进背包，有两种选择：
                    // 1. 不取当前元素：dp[i-1][j]
                    // 2. 取当前元素：dp[i-1][j-nums[i]]
                    // 两种选择只要有一种为true，则dp[i][j]为true
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }

        // ----- 步骤6：返回结果 -----
        // 看所有元素中能否选出若干数，使和等于target
        return dp[nums.length - 1][target];
    }

    public static void main(String[] args) {

        int[] nums1 = {1, 5, 11, 5};
        boolean result1 = canPartition(nums1);
        System.out.println(result1);


    }
}
