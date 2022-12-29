package leetcode.medium.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1834. Single-Threaded CPU
 * <a href="https://leetcode.com/problems/single-threaded-cpu/">https://leetcode.com/problems/single-threaded-cpu/</a>
 */
public class SingleThreadedCpu {

    private static class Task {
        int index, enqueueTime, processingTime;

        public Task(int index, int enqueueTime, int processingTime) {
            this.index = index;
            this.enqueueTime = enqueueTime;
            this.processingTime = processingTime;
        }
    }

    public int[] getOrder(int[][] tasks) {
        var inputQueue = new PriorityQueue<Task>((a, b) -> (a.enqueueTime == b.enqueueTime) ? a.processingTime - b.processingTime : a.enqueueTime - b.enqueueTime);
        var executionQueue = new PriorityQueue<Task>((a, b) -> (a.processingTime == b.processingTime) ? a.index - b.index : a.processingTime - b.processingTime);

        int[] result = new int[tasks.length];
        for (int i = 0; i < tasks.length; i++) inputQueue.add(new Task(i, tasks[i][0], tasks[i][1]));
        int time = inputQueue.peek().enqueueTime;
        int index = 0;
        while (!inputQueue.isEmpty() || !executionQueue.isEmpty()) {
            while (!inputQueue.isEmpty() && inputQueue.peek().enqueueTime <= time) {
                executionQueue.add(inputQueue.poll());
            }
            if (executionQueue.isEmpty()) {
                time = inputQueue.peek().enqueueTime;
            } else {
                var task = executionQueue.poll();
                result[index++] = task.index;
                time += task.processingTime;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        var cpu = new SingleThreadedCpu();
        //[0,2,3,1]
        System.out.println(Arrays.toString(cpu.getOrder(new int[][]{{1, 2}, {2, 4}, {3, 2}, {4, 1}})));
        //[4,3,2,0,1]
        System.out.println(Arrays.toString(cpu.getOrder(new int[][]{{7, 10}, {7, 12}, {7, 5}, {7, 4}, {7, 2}})));
        //[6,1,2,9,4,10,0,11,5,13,3,8,12,7]
        System.out.println(Arrays.toString(cpu.getOrder(new int[][]{{19, 13}, {16, 9}, {21, 10}, {32, 25}, {37, 4}, {49, 24}, {2, 15}, {38, 41}, {37, 34}, {33, 6}, {45, 4}, {18, 18}, {46, 39}, {12, 24}})));
        //[0,1,3,2]
        System.out.println(Arrays.toString(cpu.getOrder(new int[][]{{1, 3}, {2, 4}, {3, 9}, {4, 5}})));
    }
}
