package Hot100.技巧.majorityElement;

class Solution {
    /**
     * 使用摩尔投票法找出数组中出现次数超过一半的元素（众数）
     * 前提条件：数组中一定存在众数（出现次数 > n/2）
     */
    public static int majorityElement(int[] nums) {
        // 票数计数器：记录当前候选人的净票数
        int votes = 0;
        // 当前候选的众数值
        int ans = 0;

        // 遍历数组中的每个元素
        for(int i = 0; i < nums.length; i++) {
            // 如果票数为0，说明前面的票数已经正负抵消
            // 我们需要设置新的候选众数
            if(votes == 0) {
                ans = nums[i]; // 将当前元素设为新的候选众数
            }

            // 统计票数
            if(nums[i] == ans) {
                votes++; // 当前元素与候选众数相同，票数加1
            } else {
                votes--; // 当前元素与候选众数不同，票数减1（相当于抵消一票）
            }
        }

        return ans; // 返回最终确定的众数
    }

    public static void main(String[] args) {
        // 测试用例：众数是2
        // 数组：[2, 2, 1, 1, 2, 2, 2]
        // 长度：7，出现次数超过3.5，实际上2出现了5次
        int[] nums = {2, 2, 1, 1, 2, 2, 2};
        int result = majorityElement(nums);

        System.out.println(result);
    }
}