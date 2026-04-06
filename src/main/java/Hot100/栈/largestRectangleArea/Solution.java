package Hot100.栈.largestRectangleArea;

class Solution {
    /**
     * 计算柱状图中最大矩形面积
     * 使用枚举法：对于每个柱子，向左右扩展直到遇到更低的柱子
     * 时间复杂度：O(n²)，空间复杂度：O(1)
     */
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0; // 记录最大面积

        // 枚举每个柱子作为矩形的高度基准
        for (int i = 0; i < heights.length; i++) {
            int currentHeight = heights[i]; // 当前柱子的高度

            int left = i, right = i; // 初始化左右边界

            // 向左扩展：找到最后一个高度不小于当前柱子的位置
            while (left > 0 && currentHeight <= heights[left - 1]) {
                left--;
            }

            // 向右扩展：找到最后一个高度不小于当前柱子的位置
            while (right < heights.length - 1 && currentHeight <= heights[right + 1]) {
                right++;
            }

            // 计算以当前柱子为高的矩形面积：高度 × 宽度
            int width = right - left + 1;
            int area = currentHeight * width;

            // 更新最大面积
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }


    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] heights = {2, 1, 5, 6, 2, 3};
        int result = sol.largestRectangleArea(heights);

        System.out.println(result); // 输出: 10
    }
}
