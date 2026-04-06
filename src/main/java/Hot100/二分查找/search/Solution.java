package Hot100.二分查找.search;

class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1; // 初始化左右指针

        while (l <= r) {
            int mid = l + ((r - l) >> 1); // 计算中间位置，防止溢出

            // 找到目标值直接返回
            if (nums[mid] == target)
                return mid;

            // 情况1：左半区间 [l, mid] 是有序的
            if (nums[l] <= nums[mid]) {
                // 如果目标值在有序的左半区间内
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1; // 在左半区间继续搜索
                } else {
                    l = mid + 1; // 在右半区间继续搜索
                }
            }
            // 情况2：右半区间 [mid, r] 是有序的
            else {
                // 如果目标值在有序的右半区间内
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1; // 在右半区间继续搜索
                } else {
                    r = mid - 1; // 在左半区间继续搜索
                }
            }
        }

        return -1; // 未找到目标值
    }

    // 测试方法
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums1 = {4, 5, 6, 7};
        int target1 = 5;
        int ans = solution.search(nums1, target1);
        System.out.println(ans);
    }
}
