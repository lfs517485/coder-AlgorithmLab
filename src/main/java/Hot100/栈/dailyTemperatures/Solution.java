package Hot100.栈.dailyTemperatures;

import java.util.Stack;

class Solution {
    /**
     * 计算每天需要等待多少天才能得到更高的温度
     * 使用单调栈（递减栈）高效求解：栈中存储数组索引，对应温度保持递减顺序
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        // 直接用Stack模拟栈，存储数组索引
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // 当前温度大于栈顶温度时，说明找到了下一个更高温度
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            // 当前索引入栈，等待后续更高温度
            stack.push(i);
        }
        // 栈中剩余索引没有更高温度，ans对应位置已默认为0
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试用例
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = solution.dailyTemperatures(temperatures);

        // 打印结果数组
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) System.out.print(", ");
        }
    }
}
