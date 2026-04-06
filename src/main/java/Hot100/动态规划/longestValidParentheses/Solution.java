package Hot100.动态规划.longestValidParentheses;
class Solution {

    /**
     * 核心思路：
     * 动态规划：dp[i] 表示以第 i 个字符结尾的最长有效括号子串的长度
     * <p>
     * 状态转移分析：
     * 1. 如果 s[i] == '('：dp[i] = 0（以 '(' 结尾一定不是有效括号）
     * 2. 如果 s[i] == ')'：
     *    a) 如果 s[i-1] == '('：...() 形式
     *       dp[i] = dp[i-2] + 2
     *    b) 如果 s[i-1] == ')'：...)) 形式
     *       需要找到与当前 ')' 匹配的左括号位置：i - dp[i-1] - 1
     *       如果该位置是 '('，则：
     *       dp[i] = dp[i-1] + 2 + dp[i - dp[i-1] - 2]
     */
    public int longestValidParentheses(String s) {
        // 长度小于2，不可能构成有效括号
        if (s.length() < 2) return 0;

        int ans = 0;
        // dp[i]：以 i 下标字符结尾的最长有效括号长度
        int[] dp = new int[s.length()];

        // 动态规划，从下标1开始（因为dp[0]一定是0）
        for (int i = 1; i < s.length(); i++) {
            // 只有以 ')' 结尾才有可能构成有效括号
            if (s.charAt(i) == ')') {
                // 情况1：...() 形式，直接匹配
                if (s.charAt(i - 1) == '(') {
                    dp[i] = 2 + (i - 2 >= 0 ? dp[i - 2] : 0);
                }
                // 情况2：...)) 形式，需要找到匹配的左括号
                else if (i - dp[i - 1] >= 1 && s.charAt((i - dp[i - 1]) - 1) == '(') {
                    dp[i] = 2 + dp[i - 1] + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                }
            }
            // 更新最大值
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = ")()())";
        int result = solution.longestValidParentheses(s);

        System.out.println(result);

    }
}
