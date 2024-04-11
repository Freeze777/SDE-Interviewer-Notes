package leetcode.medium.stack;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses">Minimum Remove to Make Valid Parentheses</a>
 */
public class MinRemovesValidParentheses {

    private Set<Integer> removeExcess(String s, char bracket) {
        var removed = new HashSet<Integer>();
        var excessBracket = 0;
        var stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i)))
                stack.push(s.charAt(i));
            else if (s.charAt(i) == bracket) {
                excessBracket++;
                stack.push(s.charAt(i));
            } else {
                if (excessBracket == 0) {
                    removed.add(i);
                    continue;
                }
                while (true) if (stack.pop() == bracket) break;
                excessBracket--;
            }
        }
        return removed;
    }

    public String minRemoveToMakeValid(String s) {
        var removedClosing = removeExcess(s, '(');
        var removedOpening = removeExcess((new StringBuilder(s)).reverse().toString(), ')');
        var removed = new HashSet<>(removedClosing);
        for (var i : removedOpening) removed.add(s.length() - i - 1);
        var result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) if (!removed.contains(i)) result.append(s.charAt(i));
        return result.toString();
    }

    public static void main(String[] args) {
        var mr = new MinRemovesValidParentheses();
        System.out.println(mr.minRemoveToMakeValid("))(("));
        System.out.println(mr.minRemoveToMakeValid("a)b(c)d"));
        System.out.println(mr.minRemoveToMakeValid("a)b(c)d("));
        System.out.println(mr.minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println(mr.minRemoveToMakeValid("))(())(("));
    }
}
