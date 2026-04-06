package Hot100.二分查找.searchRange;

import java.util.Arrays;

class Solution {
    /**
     * 在排序数组中查找目标值的起始和结束位置
     * 如果目标值不存在于数组中，返回 [-1, -1]
     */
    public int[] searchRange(int[] nums, int target) {
        // 首先使用二分查找找到目标值的任意一个位置
        int index = binarySearch(nums, target);

        // 如果目标值不存在，直接返回 [-1, -1]
        if (index == -1) {
            return new int[]{-1, -1};
        }

        // 从找到的位置向左右扩展，确定目标值的起始和结束位置
        int left = index;  // 起始位置指针
        int right = index; // 结束位置指针

        // 向左扩展，找到起始位置
        while (left > 0 && nums[left - 1] == target) {
            left--;
        }

        // 向右扩展，找到结束位置
        while (right < nums.length - 1 && nums[right + 1] == target) {
            right++;
        }

        // 返回起始和结束位置
        return new int[]{left, right};
    }

    private int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试用例
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;

        int[] result = solution.searchRange(nums, target);

        System.out.println(Arrays.toString(result));
    }
}