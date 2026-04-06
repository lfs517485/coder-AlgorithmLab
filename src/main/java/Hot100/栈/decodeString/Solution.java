package Hot100.栈.decodeString;

import java.util.LinkedList;

class Solution {
    /**
     * 解码字符串：将编码的字符串转换为原始字符串
     * 编码规则：k[encoded_string] 表示 encoded_string 重复 k 次
     * 使用双栈法：数字栈存储重复次数，字符串栈存储前缀结果
     */
    public String decodeString(String s) {
        // 数字栈：存储遇到的数字（重复次数）
        LinkedList<Integer> numStack = new LinkedList<>();
        // 字符串栈：存储遇到'['之前的字符串结果
        LinkedList<String> strStack = new LinkedList<>();
        // 当前层正在构建的字符串
        StringBuilder cur = new StringBuilder();
        // 当前正在读取的数字（可能有多位）
        int num = 0;

        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                // 数字字符：累加构成完整数字（处理多位数）
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                // 遇到左括号：将当前数字和字符串入栈，并重置变量
                numStack.push(num);
                strStack.push(cur.toString());
                cur = new StringBuilder();
                num = 0;
            } else if (c == ']') {
                // 遇到右括号：弹出栈顶数字和字符串，进行重复拼接
                int repeatTimes = numStack.pop();
                StringBuilder temp = new StringBuilder(strStack.pop());
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(cur);
                }
                cur = temp;
            } else {
                // 字母字符：直接添加到当前字符串
                cur.append(c);
            }
        }

        return cur.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String s1 = "3[a2[c]]";
        String result1 = solution.decodeString(s1);
        System.out.println(result1);
    }
}