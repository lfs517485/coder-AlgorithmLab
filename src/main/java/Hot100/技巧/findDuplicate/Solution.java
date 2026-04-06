package Hot100.技巧.findDuplicate;

class Solution {
    public static int findDuplicate(int[] nums) {
        int ans = 0;

        // 原地哈希（鸽巢原理/索引映射法）：
        // 将每个数字放到它应该在的位置（数值x应该放在索引x-1的位置）
        // 例如：数值3应该放在索引2的位置，数值1应该放在索引0的位置
        for (int i = 0; i < nums.length; i++) {
            // 持续交换，直到当前元素满足以下条件之一：
            // 1. nums[i] == i + 1（元素已在正确位置）
            // 2. 发现重复元素（要交换的位置已经有相同的值）
            while (nums[i] != nums[nums[i] - 1]) {
                // 将当前元素 nums[i] 交换到它应该在的位置 nums[i]-1
                swap(nums, i, nums[i] - 1);
            }
        }

        // 遍历数组，找出不在正确位置的元素
        for (int i = 0; i < nums.length; i++) {
            // 如果元素不在其值对应的索引位置，说明它是重复项
            // 因为正确的索引应该是 nums[i]-1
            if (nums[i] - 1 != i) {
                return nums[i];
            }
        }

        return ans;
    }

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {

        // 测试用例1：数组 [1,3,4,2,2]
        int[] nums = {1, 3, 4, 2, 2};
        int result = findDuplicate(nums);

        System.out.println(result);

    }
}