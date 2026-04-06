package Hot100.回溯.generateParenthesis;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // 存储所有有效的括号组合
    List<String> ans;
    // 存储当前正在构建的括号串
    StringBuilder level;

    public List<String> generateParenthesis(int n) {
        ans = new ArrayList<>();
        level = new StringBuilder();

        // 从0个左括号和0个右括号开始深度优先搜索
        dfs(0, 0, n);

        return ans;
    }

    /**
     * 深度优先搜索函数，使用回溯法生成所有有效的括号组合
     * @param l 当前已使用的左括号数量
     * @param r 当前已使用的右括号数量
     * @param n 目标括号对数
     */
    private void dfs(int l, int r, int n) {
        // 终止条件：已生成完整括号串（长度为2n）
        if (level.length() == n * 2) {
            ans.add(level.toString());
            return;
        }

        // 情况1：可以添加左括号的条件（左括号数量小于n）
        if (l < n) {
            level.append('(');
            dfs(l + 1, r, n);  // 注意：使用l+1而不是l++
            level.deleteCharAt(level.length() - 1);  // 回溯
        }

        // 情况2：可以添加右括号的条件（右括号数量小于左括号数量）
        if (r < l) {  // 关键：r < l 确保括号有效性，不能是 r <= l
            level.append(')');
            dfs(l, r + 1, n);  // 注意：使用r+1而不是r++
            level.deleteCharAt(level.length() - 1);  // 回溯
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 2;
        List<String> result = solution.generateParenthesis(n);

        for (String combination : result) {
            System.out.println(combination);
        }

    }

}