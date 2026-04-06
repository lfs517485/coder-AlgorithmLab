package Hot100.二分查找.findMedianSortedArrays;

class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 保证 nums1 是较短的数组，减少二分查找的范围
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;  // 较短数组的长度
        int n = nums2.length;  // 较长数组的长度
        int totalLeft = (m + n + 1) / 2;  // 左半部分应包含的元素个数

        // 在 nums1 的 [0, m] 范围内进行二分查找，寻找合适的分割线
        int left = 0;
        int right = m;
        while (left < right) {
            int i = left + (right - left + 1) / 2;  // nums1 中左半部分的元素个数
            int j = totalLeft - i;                  // nums2 中左半部分的元素个数

            if (nums1[i - 1] > nums2[j]) {
                // nums1 左半部分最大值太大，需要减少 i
                right = i - 1;
            } else {
                // 当前分割线合理，尝试增加 i 以寻找最优分割点
                left = i;
            }
        }

        int i = left;          // nums1 中分割线左侧的元素个数
        int j = totalLeft - i; // nums2 中分割线左侧的元素个数

        // 处理分割线两侧四个元素的边界情况
        int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
        int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
        int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
        int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];

        // 根据总长度的奇偶性计算中位数
        if ((m + n) % 2 == 1) {
            // 奇数长度：中位数是左半部分的最大值
            return Math.max(nums1LeftMax, nums2LeftMax);
        } else {
            // 偶数长度：中位数是左右两部分中间值的平均数
            return (Math.max(nums1LeftMax, nums2LeftMax) +
                    Math.min(nums1RightMin, nums2RightMin)) / 2.0;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};

        double median = sol.findMedianSortedArrays(nums1, nums2);
        System.out.println("中位数: " + median);  // 输出: 2.5
    }
}