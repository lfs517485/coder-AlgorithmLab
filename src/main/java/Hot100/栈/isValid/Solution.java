package Hot100.栈.isValid;

import java.util.Stack;

class Solution {

    public boolean isValid(String s) {
        // 使用 Stack 类存储左括号
        Stack<Character> stack = new Stack<>();

        // 遍历字符串中的每个字符
        for (char c : s.toCharArray()) {
            // 当前字符是左括号：压入栈中等待匹配
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            // 当前字符是右括号：尝试匹配
            else {
                // 栈为空：右括号没有对应的左括号
                if (stack.isEmpty()) {
                    return false;
                }

                // 弹出栈顶的左括号
                char top = stack.pop();

                // 检查左右括号是否匹配
                if ((c == ')' && top != '(') ||
                        (c == ']' && top != '[') ||
                        (c == '}' && top != '{')) {
                    return false; // 类型不匹配
                }
            }
        }

        // 遍历结束：栈应为空（所有左括号都已匹配）
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        Solution sol = new Solution();

        String s = "()[]{}";
        boolean result = sol.isValid(s);

        System.out.println(result);
    }
}