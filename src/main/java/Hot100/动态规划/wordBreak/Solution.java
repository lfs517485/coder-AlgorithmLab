package Hot100.动态规划.wordBreak;

import java.util.*;

class Solution {

    /**
     * 核心思路：
     * 动态规划：dp[i] 表示字符串 s 的前 i 个字符（s[0..i-1]）能否拆分成字典中的单词
     * <p>
     * 状态转移：
     * dp[i] = dp[j] && check(s[j..i-1])，其中 0 <= j < i
     * 只要存在一个 j 使得 dp[j] 为 true 且 s[j..i-1] 在字典中，则 dp[i] 为 true

     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // 将字典转换为 HashSet，提高查询效率 O(1)
        HashSet<String> set = new HashSet<>(wordDict);

        // dp[i]：字符串的前 i 个字符（下标 0 到 i-1）能否被拆分成字典中的单词
        boolean[] dp = new boolean[s.length() + 1];

        // 初始化：空字符串可以被拆分
        dp[0] = true;

        // 遍历所有子串长度 i（从 1 到字符串长度）
        for (int i = 1; i <= s.length(); i++) {
            // 枚举分割点 j，将 [0,i) 分割成 [0,j) 和 [j,i)
            for (int j = 0; j < i; j++) {
                // 状态转移：dp[j] 为 true 且 s[j..i-1] 在字典中
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;  // 找到一种分割方式即可，无需继续枚举
                }
            }
        }

        // 返回整个字符串 s 是否可以被拆分
        return dp[s.length()];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String s1 = "leetcode";
        List<String> wordDict1 = Arrays.asList("leet", "code");
        boolean result1 = solution.wordBreak(s1, wordDict1);

        System.out.println(result1);
    }
}
