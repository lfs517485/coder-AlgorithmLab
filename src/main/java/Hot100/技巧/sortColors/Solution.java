package Hot100.技巧.sortColors;
import java.util.Arrays;

class Solution {
    /**
     * 排序颜色（荷兰国旗问题）：将数组中的0、1、2排序，0在前，1在中，2在后。
     * 使用三指针法：p0指向0的边界，p2指向2的边界，i为当前遍历指针。
     * 算法步骤：
     * 1. 遍历数组，如果当前元素为0，将其与p0交换，递增p0和i
     * 2. 如果当前元素为2，将其与p2交换，递减p2（i不递增，因为交换后需要重新检查新元素）
     * 3. 如果当前元素为1，直接递增i
     *
     * 注意：使用i <= p2作为循环条件，因为当i超过p2时，后面的元素都是2，已经排好序了。
     */
    public void sortColors(int[] nums) {
        int p0 = 0; // 指向下一个0应该放置的位置（0的左边界）
        int p2 = nums.length - 1; // 指向下一个2应该放置的位置（2的右边界）
        int i = 0; // 当前遍历指针

        // 关键：使用while循环，因为i在某些情况下不能自增
        while (i <= p2) {
            if (nums[i] == 0) {
                // 当前元素为0，交换到前面
                swap(nums, i, p0);
                p0++; // p0右移
                i++;   // i右移，继续检查下一个元素
            } else if (nums[i] == 2) {
                // 当前元素为2，交换到后面
                swap(nums, i, p2);
                p2--; // p2左移
                // 注意：这里i不自增，因为交换后的新元素需要重新检查
            } else {
                // 当前元素为1，跳过
                i++;
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums1 = {2, 0, 2, 1, 1, 0};
        solution.sortColors(nums1);
        System.out.println(Arrays.toString(nums1));

    }
}