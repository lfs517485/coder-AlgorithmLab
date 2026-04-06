package Hot100.技巧.nextPermutation;

import java.util.Arrays;

class Solution {
    /**
     * 下一个排列
     * 算法步骤：
     * 1. 从后向前找到第一个相邻升序对 (nums[i] < nums[i+1])
     * 2. 在[i+1, end)区间从后向前找到第一个大于nums[i]的元素nums[j]
     * 3. 交换nums[i]和nums[j]
     * 4. 将[i+1, end)区间反转（使其升序排列）
     */
    public void nextPermutation(int[] nums) {
        if (nums.length == 1)
            return;

        int i = nums.length - 2; // 选择倒数第二个元素「用于与后面元素交换」

        // 1. 从后向前找，找到第一个相邻的升序元素对
        // 即找到第一个打破降序趋势的位置
        while (0 <= i && nums[i] >= nums[i + 1])
            i--; // 结束while的情况：i = -1 或者 nums[i] < nums[i+1]

        // 2. 如果找到了这样的位置（不是整个数组都是降序的）
        if (0 <= i) {
            int j = nums.length - 1; // 选择倒数第一个元素
            // 从后向前找到第一个大于nums[i]的元素
            while (nums[i] >= nums[j])
                j--; // 结束while的情况：nums[i] < nums[j]

            // 3. 交换nums[i]和nums[j]
            swap(nums, i, j);
        }

        // 4. 将数组i+1位置之后的元素逆置（使其升序排列）
        reverse(nums, i + 1, nums.length - 1);
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试用例：常规情况
        int[] nums = {1, 2, 3};

        solution.nextPermutation(nums);

        System.out.println(Arrays.toString(nums));
    }
}