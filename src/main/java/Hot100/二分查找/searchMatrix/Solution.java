package Hot100.二分查找.searchMatrix;

class Solution {
    /**
     * 解题思路：
     * 1. 将二维矩阵视为一个虚拟的一维升序数组
     * 2. 使用标准的二分查找算法
     * 3. 通过数学映射将一维索引转换为二维坐标
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // 获取矩阵的行数和列数
        int m = matrix.length;     // 行数
        int n = matrix[0].length;  // 列数

        // 将二维矩阵映射为一维数组：总元素个数 = m * n
        // l指向第一个元素索引(0)，r指向最后一个元素索引(m*n-1)
        int l = 0, r = m * n - 1;

        // 标准的二分查找循环
        while (l <= r) {
            // 计算中间索引：防止整数溢出的写法
            int mid = l + ((r - l) >> 1);

            // 关键步骤：将一维索引 mid 映射回二维坐标
            // mid / n = 行索引（每行有n个元素）
            // mid % n = 列索引（在当前行中的位置）
            int row = mid / n;
            int col = mid % n;
            int value = matrix[row][col];

            // 比较中间值与目标值
            if (value == target) {
                return true;  // 找到目标值
            } else if (value > target) {
                // 目标值在左半部分
                r = mid - 1;
            } else {
                // 目标值在右半部分
                l = mid + 1;
            }
        }

        // 循环结束未找到目标值
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试用例
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target = 3;

        boolean result = solution.searchMatrix(matrix, target);
        System.out.println("结果: " + result);
    }
}