package Hot100.多维动态规划.uniquePaths;

class Solution {
    public static int uniquePaths(int m, int n) {
        // 创建动态规划数组，dp[i][j]表示到达位置(i,j)的路径数
        int[][] dp = new int[m][n];

        // 动态规划初始化：第一行和第一列的路径数都是1
        // 因为只能一直向右或一直向下走
        for(int i = 0; i < m; i++) {
            dp[i][0] = 1; // 第一列所有格子都只有1条路径（一直向下）
        }
        for(int j = 0; j < n; j++) {
            dp[0][j] = 1; // 第一行所有格子都只有1条路径（一直向右）
        }

        // 动态规划递推：计算其他格子的路径数
        // 动态转移方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
        // 含义：到达(i,j)的路径数 = 从上面来的路径数 + 从左面来的路径数
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // 返回右下角格子的路径数
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {

        // 测试用例1：3行7列的网格
        int m = 3, n = 7;
        int result = uniquePaths(m, n);

        System.out.println(m + "行" + n + "列的网格中，从左上角到右下角的唯一路径数: " + result);
    }
}