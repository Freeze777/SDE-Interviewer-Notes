package leetcode.medium.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.com/problems/meeting-rooms-ii/">https://leetcode.com/problems/meeting-rooms-ii/</a>
 */
public class MinimumMeetingRooms {
    public int minMeetingRooms(int[][] meetings) {
        if (meetings.length < 2) return meetings.length;

        List<Integer> startTimes = Arrays.stream(meetings).map(meeting -> meeting[0])
                .sorted().collect(Collectors.toList());
        List<Integer> endTimes = Arrays.stream(meetings).map(meeting -> meeting[1])
                .sorted().collect(Collectors.toList());
        int ongoingMeetings = 1, maxMeetingRooms = 1, startIndex = 1, endIndex = 0;
        while (startIndex < meetings.length && endIndex < meetings.length) {
            if (endTimes.get(endIndex) < startTimes.get(startIndex)) {
                // a existing meeting ended
                endIndex++;
                ongoingMeetings--;
            } else if (endTimes.get(endIndex).equals(startTimes.get(startIndex))) {
                // a existing meeting ended and new meeting started simultaneously
                endIndex++;
                startIndex++;
            } else {
                // a new meeting started
                startIndex++;
                ongoingMeetings++;
                maxMeetingRooms = Math.max(ongoingMeetings, maxMeetingRooms);
            }
        }
        return maxMeetingRooms;
    }

    public static void main(String[] args) {
        MinimumMeetingRooms mmr = new MinimumMeetingRooms();
        System.out.println(mmr.minMeetingRooms(new int[][]{{15, 18}, {1, 3}, {8, 10}, {2, 6}}));
        System.out.println(mmr.minMeetingRooms(new int[][]{{9, 18}, {1, 3}, {8, 10}, {2, 6}}));
        System.out.println(mmr.minMeetingRooms(new int[][]{{9, 18}, {1, 3}, {7, 10}, {2, 8}}));
        System.out.println(mmr.minMeetingRooms(new int[][]{{9, 18}}));
        System.out.println(mmr.minMeetingRooms(new int[][]{{9, 18}, {20, 100}}));
        System.out.println(mmr.minMeetingRooms(new int[][]{{9, 18}, {18, 100}}));
        System.out.println(mmr.minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
    }
}
