package Hot100.多维动态规划.minDistance;

class Solution {

    /**
     * 状态转移分析：
     * 1. 如果 word1[i-1] == word2[j-1]：
     *    当前字符相等，无需额外操作，直接继承 dp[i-1][j-1]
     *    dp[i][j] = dp[i-1][j-1]
     * <p>
     * 2. 如果 word1[i-1] != word2[j-1]：
     *    三种操作取最小值：
     *    - 替换：将 word1[i-1] 替换为 word2[j-1] → dp[i-1][j-1] + 1
     *    - 删除：删除 word1[i-1] → dp[i-1][j] + 1
     *    - 插入：在 word1[i] 位置插入 word2[j-1] → dp[i][j-1] + 1
     */
    public int minDistance(String word1, String word2) {
        // 如果有任意一个字符串为空，编辑距离就是另一个字符串的长度
        if (word1.length() * word2.length() == 0) {
            return word1.length() + word2.length();
        }

        int m = word1.length();
        int n = word2.length();

        // dp[i][j]：word1 的前 i 个字符转换到 word2 的前 j 个字符的最少操作次数
        int[][] dp = new int[m + 1][n + 1];

        // ----- 初始化边界条件 -----
        // dp[i][0] = i：将 word1 的前 i 个字符全部删除，变成空串
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        // dp[0][j] = j：将空串插入 j 个字符，变成 word2 的前 j 个字符
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // ----- 动态规划填表 -----
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 情况1：当前字符相等，无需额外操作
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // 情况2：当前字符不相等，需要在三种操作中选择最小值
                else {
                    int replace = dp[i - 1][j - 1] + 1; // 替换操作
                    int delete = dp[i - 1][j] + 1;     // 删除操作
                    int insert = dp[i][j - 1] + 1;     // 插入操作
                    dp[i][j] = Math.min(replace, Math.min(delete, insert));
                }
            }
        }

        return dp[m][n];
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        String word1 = "horse";
        String word2 = "ros";
        int result = solution.minDistance(word1, word2);

        System.out.println(result);

    }
}
