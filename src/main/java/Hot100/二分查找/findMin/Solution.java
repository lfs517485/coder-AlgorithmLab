package Hot100.二分查找.findMin;

class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;          // 搜索区间为整个数组
        int minValue = nums[0];                        // 初始化最小值为第一个元素

        // 二分查找：不断缩小搜索区间
        while (left <= right) {
            int mid = left + ((right - left) >> 1);    // 计算中点，防止溢出

            if (nums[mid] < minValue) {
                // 当前中点值比记录的最小值小
                // 更新最小值，并在左半部分继续搜索（可能还有更小的值）
                minValue = nums[mid];
                right = mid - 1;
            } else {
                // 当前中点值大于等于记录的最小值
                // 最小值可能在中点的右侧
                left = mid + 1;
            }
        }

        return minValue;      // 返回找到的最小值
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums = {3, 4, 5, 1, 2};
        int min = sol.findMin(nums);

        System.out.println(min);
    }
}