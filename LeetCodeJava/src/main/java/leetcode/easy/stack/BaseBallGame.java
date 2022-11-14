package leetcode.easy.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/baseball-game/">https://leetcode.com/problems/baseball-game/</a>
 */
public class BaseBallGame {
    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();
        for (String op : operations) {
            switch (op) {
                case "+":
                    Integer last = stack.pop();
                    Integer secondLast = stack.pop();
                    stack.addAll(Arrays.asList(secondLast, last, last + secondLast));
                    break;
                case "D":
                    stack.push(stack.peek() * 2);
                    break;
                case "C":
                    stack.pop();
                    break;
                default:
                    stack.push(Integer.parseInt(op));
                    break;
            }
        }
        return stack.stream().mapToInt(Integer::intValue).sum();
    }

    public static void main(String[] args) {
        BaseBallGame bg = new BaseBallGame();
        System.out.println(bg.calPoints(new String[]{"5", "2", "C", "D", "+"}));
        System.out.println(bg.calPoints(new String[]{"5", "-2", "4", "C", "D", "9", "+", "+"}));
        System.out.println(bg.calPoints(new String[]{"1", "C"}));
        System.out.println(bg.calPoints(new String[]{"-60", "D", "-36", "30", "13", "C", "C", "-33", "53", "79"}));
    }
}
