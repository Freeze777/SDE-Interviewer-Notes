package leetcode.medium.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * <a href="https://leetcode.com/problems/time-needed-to-inform-all-employees/">...</a>
 *
 **/
public class TimeToInformAll {

    public int dfs(Map<Integer, List<Integer>> adjList, int manager, int[] informTime, int timeTillNow) {
        int max = timeTillNow;
        List<Integer> reportees = adjList.containsKey(manager) ? adjList.get(manager) : new ArrayList<Integer>();
        for (Integer reportee : reportees) {
            max = Math.max(max, dfs(adjList, reportee, informTime, timeTillNow + informTime[manager]));
        }
        return max;
    }

    public int numOfMinutes(int n, int headId, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int mgr = manager[i];
            if (!adjList.containsKey(mgr)) {
                adjList.put(mgr, new ArrayList<Integer>());
            }
            adjList.get(mgr).add(i);
        }
        return dfs(adjList, headId, informTime, 0);
    }

    public static void main(String[] args) {
        TimeToInformAll toInformAll = new TimeToInformAll();
        System.out.println(toInformAll.numOfMinutes(6, 2, new int[]{2, 2, -1, 2, 2, 2}, new int[]{0, 0, 1, 0, 0, 0}));
        System.out.println(toInformAll.numOfMinutes(1, 0, new int[]{-1}, new int[]{0}));

        System.out.println(toInformAll.numOfMinutes(4, 0, new int[]{-1, 0, 1, 2}, new int[]{1, 1, 0, 0}));
        System.out.println(toInformAll.numOfMinutes(4, 0, new int[]{-1, 0, 1, 1}, new int[]{1, 100, 0, 0}));
    }
}
