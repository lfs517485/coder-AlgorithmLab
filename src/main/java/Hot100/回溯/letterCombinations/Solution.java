package Hot100.回溯.letterCombinations;

import java.util.*;

class Solution {
    // 数字到字母的映射表
    Map<Character, String> map;
    // 存储所有组合结果
    List<String> ans;
    // 存储当前正在构建的组合
    StringBuilder level;

    public List<String> letterCombinations(String digits) {
        // 处理空字符串特例
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        // 初始化数字到字母的映射
        initPhoneMap();

        // 初始化结果列表和当前组合
        ans = new ArrayList<>();
        level = new StringBuilder();

        // 开始深度优先搜索
        dfs(0, digits);

        return ans;
    }

    /**
     * 初始化电话号码映射表
     */
    private void initPhoneMap() {
        map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
    }

    /**
     * 深度优先搜索函数，使用回溯法生成所有字母组合
     */
    private void dfs(int index, String digits) {
        // 终止条件：已处理完所有数字
        if (index == digits.length()) {
            ans.add(level.toString()); // 保存当前组合
            return;
        }

        // 获取当前数字对应的字母字符串
        char digit = digits.charAt(index);
        String letters = map.get(digit);

        // 遍历当前数字对应的所有字母
        for (char letter : letters.toCharArray()) {
            level.append(letter);           // 选择当前字母
            dfs(index + 1, digits);         // 递归处理下一个数字
            level.deleteCharAt(level.length() - 1); // 回溯，撤销选择
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试用例
        String digits = "23";
        List<String> result = solution.letterCombinations(digits);

        // 输出结果
        System.out.println("输入: digits = \"" + digits + "\"");
        System.out.println("输出: " + result);
    }
}
