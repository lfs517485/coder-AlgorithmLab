package Hot100.栈.MinStack;

import java.util.Stack;

public class MinStack {

    Stack<Integer> stack;     // 主栈：存储所有压入的元素
    Stack<Integer> minStack;  // 辅助栈：存储当前栈深度的最小值

    /**
     * 初始化两个栈，并在辅助栈中预先放入一个最大值，便于后续比较
     */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
        minStack.push(Integer.MAX_VALUE);
    }

    /**
     * 将元素val压入栈顶
     * 主栈直接压入，辅助栈压入当前栈的最小值（新元素与当前最小值的较小者）
     */
    public void push(int val) {
        stack.push(val);
        minStack.push(Math.min(minStack.peek(), val));
    }

    /**
     * 弹出栈顶元素
     * 主栈和辅助栈同步弹出
     */
    public void pop() {
        stack.pop();
        minStack.pop();
    }

    /**
     * 获取栈顶元素（不弹出）
     */
    public int top() {
        return stack.peek();
    }

    /**
     * 获取当前栈中的最小值
     * 辅助栈的栈顶元素即为当前栈的最小值
     */
    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {

        // 按照操作序列进行测试
        MinStack obj = new MinStack();

        obj.push(-2);

        obj.push(0);

        obj.push(-3);

        int min1 = obj.getMin();
        System.out.println("getMin() -> " + min1);

        obj.pop();

        int top = obj.top();
        System.out.println("top() -> " + top);


        int min2 = obj.getMin();
        System.out.println("getMin() -> " + min2);
    }
}

