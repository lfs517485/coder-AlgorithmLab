package Hot100.回溯.partition;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // 存储所有回文分割方案的结果
    List<List<String>> ans;
    // 存储当前正在构建的回文子串组合
    List<String> level;

    public List<List<String>> partition(String s) {
        ans = new ArrayList<>();
        level = new ArrayList<>();

        // 从索引0开始深度优先搜索
        dfs(0, s);

        return ans;
    }

    /**
     * 深度优先搜索函数，使用回溯法生成所有回文分割方案
     * @param index 当前处理的字符串起始索引
     * @param s 原始字符串
     */
    private void dfs(int index, String s) {
        // 终止条件：已处理完整个字符串
        if (index == s.length()) {
            ans.add(new ArrayList<>(level));
            return;
        }

        // 从当前索引开始，尝试不同长度的子串
        for (int i = index; i < s.length(); i++) {
            // 截取子串 s[index:i]
            String substring = s.substring(index, i + 1);

            // 如果当前子串是回文串，则继续搜索
            if (isPalindrome(substring)) {
                level.add(substring);           // 选择当前回文子串
                dfs(i + 1, s);                  // 从下一个字符继续搜索
                level.remove(level.size() - 1); // 回溯，撤销选择
            }
        }
    }

    /**
     * 判断字符串是否为回文串
     * @param s 待判断的字符串
     * @return 如果是回文串返回true，否则返回false
     */
    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aab";
        List<List<String>> result = solution.partition(s);

        for (List<String> partition : result) {
            System.out.println(partition);
        }
    }
}