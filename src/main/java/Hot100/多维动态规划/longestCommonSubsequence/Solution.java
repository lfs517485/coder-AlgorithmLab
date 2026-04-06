package Hot100.多维动态规划.longestCommonSubsequence;

class Solution {

    /**
     * 核心思路：
     * 动态规划 - 二维DP
     * dp[i][j] 表示 text1[0:i] 和 text2[0:j] 的最长公共子序列的长度
     * 其中 text1[0:i] 表示 text1 的前 i 个字符，即下标范围 [0, i-1]
     * <p>
     * 状态转移：
     * 1. 如果 text1[i-1] == text2[j-1]（当前字符相等）
     *    dp[i][j] = dp[i-1][j-1] + 1
     * 2. 如果 text1[i-1] != text2[j-1]（当前字符不相等）
     *    dp[i][j] = max(dp[i-1][j], dp[i][j-1])

     */
    public int longestCommonSubsequence(String text1, String text2) {
        // dp[i][j] 表示 text1[0:i] 和 text2[0:j] 的最长公共子序列的长度
        // 多开一行一列，方便处理空串情况
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        // ----- 初始化 -----
        // dp[0][j] = 0：text1为空串，与text2[0:j]的LCS长度为0
        for (int j = 0; j <= text2.length(); j++) {
            dp[0][j] = 0;
        }
        // dp[i][0] = 0：text2为空串，与text1[0:i]的LCS长度为0
        for (int i = 0; i <= text1.length(); i++) {
            dp[i][0] = 0;
        }

        // ----- 动态规划填表 -----
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // 当前字符相等，LCS长度+1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 当前字符不相等，取两种情况的最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 返回整个字符串的LCS长度
        return dp[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String text1 = "abcde";
        String text2 = "ace";
        int result = solution.longestCommonSubsequence(text1, text2);

        System.out.println(result);

    }
}
