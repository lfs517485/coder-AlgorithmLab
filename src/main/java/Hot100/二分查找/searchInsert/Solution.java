package Hot100.二分查找.searchInsert;

class Solution {
    /**
     * 在排序数组中查找目标值，如果找到返回索引，否则返回它应该被插入的位置
     * 使用二分查找算法，时间复杂度 O(log n)，空间复杂度 O(1)
     */
    public int searchInsert(int[] nums, int target) {
        // 初始化左右指针：使用闭区间 [l, r]
        int l = 0, r = nums.length - 1;

        // 二分查找循环：当左指针不大于右指针时继续查找
        while (l <= r) {
            // 计算中间位置：防止整数溢出的写法
            // 使用位运算 >> 1 代替除以2，效率更高
            int mid = l + ((r - l) >> 1);

            // 情况1：找到目标值，直接返回索引
            if (target == nums[mid]) {
                return mid;
            }

            // 情况2：目标值大于中间值，搜索右半部分
            if (target > nums[mid]) {
                l = mid + 1;  // 调整左边界
            }
            // 情况3：目标值小于中间值，搜索左半部分
            else {
                r = mid - 1;  // 调整右边界
            }
        }

        // 循环结束时，l指针的位置就是目标值应该插入的位置
        // l指向第一个大于等于target的元素位置，或数组末尾
        // 当target不存在时，l = r + 1
        return l;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 3, 5, 6};
        int target = 5;

        // 执行查找
        int result = solution.searchInsert(nums, target);
        System.out.println("\n输出: " + result);

    }

}
