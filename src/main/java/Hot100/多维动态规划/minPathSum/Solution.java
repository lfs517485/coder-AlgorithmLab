package Hot100.多维动态规划.minPathSum;

class Solution {

    public int minPathSum(int[][] grid) {
        // 获取网格的行数和列数
        int rows = grid.length;
        int cols = grid[0].length;

        // 创建动态规划数组，dp[i][j]表示从左上角到达位置(i,j)的最小路径和
        int[][] dp = new int[rows][cols];

        // 动态规划初始化：左上角起点的路径和就是格子本身的值
        dp[0][0] = grid[0][0];

        // 初始化第一列：每个格子只能从上方格子到达
        for(int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // 初始化第一行：每个格子只能从左方格到达
        for(int j = 1; j < cols; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // 动态规划递推：计算其他格子的最小路径和
        // 动态转移方程：dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
        // 含义：到达(i,j)的最小路径和 = 从上方或左方来的最小路径和 + 当前格子的值
        for(int i = 1; i < rows; i++) {
            for(int j = 1; j < cols; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        // 返回右下角格子的最小路径和
        return dp[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // 网格：[[1,3,1],[1,5,1],[4,2,1]]
        // 最小路径：1→3→1→1→1 = 7
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        int result = solution.minPathSum(grid);
        System.out.println("\n从左上角到右下角的最小路径和: " + result);
    }
}
