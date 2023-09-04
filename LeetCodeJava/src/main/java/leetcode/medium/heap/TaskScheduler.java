package leetcode.medium.heap;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/task-scheduler/">621. Task Scheduler</a>
 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (char task : tasks) map.compute(task, (k, v) -> v == null ? 1 : v + 1);
        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int tick = 0;
        maxHeap.addAll(map.values());
        while (true) {
            int cpuCycle = n + 1;
            List<Integer> coolDown = new ArrayList<>();
            while (!maxHeap.isEmpty() && cpuCycle > 0) {
                int task = maxHeap.poll();
                task--;
                if (task > 0) coolDown.add(task);
                tick++;
                cpuCycle--;
            }
            maxHeap.addAll(coolDown);
            if (maxHeap.isEmpty()) break;
            tick += cpuCycle;//idle time
        }
        return tick;
    }

    public static void main(String[] args) {
        var ts = new TaskScheduler();
        System.out.println(ts.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));//8
        System.out.println(ts.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 0));//6
        System.out.println(ts.leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));//22
        System.out.println(ts.leastInterval(new char[]{'A', 'B', 'C', 'D', 'E', 'A', 'B', 'C', 'D', 'E'}, 4));//10
        System.out.println(ts.leastInterval(new char[]{'A', 'B', 'A', 'B', 'A', 'B'}, 2));//8
        System.out.println(ts.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C'}, 2));//9
        System.out.println(ts.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 50));//104
    }
}
