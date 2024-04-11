package leetcode.easy.stack;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * <a href="https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/">
 * 1700. Number of Students Unable to Eat Lunch</a>
 */
public class EatLunch {
    public int countStudents(int[] students, int[] sandwiches) {
        var sandwichCount = 0;
        var q = new LinkedList<Integer>();
        Arrays.stream(students).forEach(q::add);
        var prevSize = q.size();
        var deadlockCounter = 0;
        while (!q.isEmpty() && sandwichCount < students.length) {
            if (sandwiches[sandwichCount] == q.peek()) {
                q.poll();
                sandwichCount++;
            } else q.add(q.poll());

            if (prevSize == q.size()) {
                deadlockCounter++;
                if (deadlockCounter == q.size()) break;
            } else deadlockCounter = 0;

            prevSize = q.size();
        }
        return q.size();
    }

    public static void main(String[] args) {
        var el = new EatLunch();
        System.out.println(el.countStudents(new int[]{1, 1, 0, 0}, new int[]{0, 1, 0, 1}));
        System.out.println(el.countStudents(new int[]{1, 1, 1, 0, 0, 1}, new int[]{1, 0, 0, 0, 1, 1}));
    }
}
