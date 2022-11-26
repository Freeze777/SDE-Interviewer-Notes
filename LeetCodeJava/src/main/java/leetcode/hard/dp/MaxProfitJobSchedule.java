package leetcode.hard.dp;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/maximum-profit-in-job-scheduling/">https://leetcode.com/problems/maximum-profit-in-job-scheduling/</a>
 */
public class MaxProfitJobSchedule {
    private static class Job implements Comparable<Job> {
        int startTime, endTime, profit;

        public Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }

        @Override
        public String toString() {
            return "Job{" + "startTime=" + startTime + ", endTime=" + endTime + ", profit=" + profit + "}\n";
        }

        @Override
        public int compareTo(Job other) {
            return Integer.compare(this.startTime, other.startTime);
        }
    }

    private static int getNextJobIndex(Job[] jobs, Job job, int from) {
        Job key = new Job(job.endTime, 0, 0);
        int index = Arrays.binarySearch(jobs, from, jobs.length, key);
        while (index > 0 && jobs[index - 1].startTime == key.startTime) index--; // leftmost match
        if (index < 0) index = Math.abs(index) - 1; // no match - start from insertion point
        return index;
    }

    private int jobScheduling(Job[] jobs, int start, int[] cache) {
        if (start >= jobs.length) return 0;
        if (cache[start] != Integer.MIN_VALUE) return cache[start];
        Job currentJob = jobs[start];
        int profitWithoutJob = jobScheduling(jobs, start + 1, cache);
        int nextJobIndex = getNextJobIndex(jobs, currentJob, start + 1);
        int profitWithJob = currentJob.profit + jobScheduling(jobs, nextJobIndex, cache);
        int ans = Math.max(profitWithoutJob, profitWithJob);
        return (cache[start] = ans);
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        Job[] jobs = new Job[startTime.length];
        for (int i = 0; i < jobs.length; i++)
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        Arrays.sort(jobs);
        int[] cache = new int[jobs.length];
        Arrays.fill(cache, Integer.MIN_VALUE);
        return jobScheduling(jobs, 0, cache);
    }

    public static void main(String[] args) {
        MaxProfitJobSchedule mpjs = new MaxProfitJobSchedule();
        //120
        System.out.println(mpjs.jobScheduling(new int[]{1, 2, 3, 3}, new int[]{3, 4, 5, 6}, new int[]{50, 10, 40, 70}));
        //150
        System.out.println(mpjs.jobScheduling(new int[]{1, 2, 3, 4, 6}, new int[]{3, 5, 10, 6, 9}, new int[]{20, 20, 100, 70, 60}));
        //6
        System.out.println(mpjs.jobScheduling(new int[]{1, 1, 1}, new int[]{2, 3, 4}, new int[]{5, 6, 4}));
        //100
        System.out.println(mpjs.jobScheduling(new int[]{10, 10, 10, 10, 10}, new int[]{20, 25, 15, 30, 40}, new int[]{20, 20, 100, 70, 60}));
        //100
        System.out.println(mpjs.jobScheduling(new int[]{10, 10, 10, 10, 10}, new int[]{20, 20, 20, 20, 20}, new int[]{20, 20, 100, 70, 60}));
        //120
        System.out.println(mpjs.jobScheduling(new int[]{10, 10, 20, 20, 20}, new int[]{20, 20, 25, 25, 25}, new int[]{20, 20, 100, 70, 60}));
        //7
        System.out.println(mpjs.jobScheduling(new int[]{1, 2, 2, 3}, new int[]{2, 5, 3, 4}, new int[]{3, 4, 1, 2}));
        //18
        System.out.println(mpjs.jobScheduling(new int[]{4, 2, 4, 8, 2}, new int[]{5, 5, 5, 10, 8}, new int[]{1, 2, 8, 10, 4}));
    }
}
