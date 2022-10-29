package leetcode.medium.stack;

import java.util.Stack;

public class MinStack {

    Stack<Integer> main = new Stack<>();
    Stack<Integer> min = new Stack<>();

    public MinStack() {

    }

    public void push(int val) {
        main.push(val);
        if (min.isEmpty()) min.push(val);
        else if (val <= min.peek()) min.push(val);
    }

    public void pop() {
        int val = main.pop();
        if (val == min.peek()) min.pop();
    }

    public int top() {
        return main.peek();
    }

    public int getMin() {
        return min.peek();
    }

    public static void main(String[] args) {
        MinStack ms = new MinStack();
        ms.push(-2);
        ms.push(-0);
        ms.push(-3);
        System.out.println(ms.getMin());
        ms.pop();
        System.out.println(ms.top());
        System.out.println(ms.getMin());
    }
}
