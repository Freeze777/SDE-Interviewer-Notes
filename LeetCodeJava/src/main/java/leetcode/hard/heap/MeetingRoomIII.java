package leetcode.hard.heap;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/meeting-rooms-iii/">Meeting Rooms III</a>
 */
public class MeetingRoomIII {
    private record Meeting(long startTime, long endTime) {
    }

    private record BookedMeeting(long endTime, int room) implements Comparable<BookedMeeting> {
        @Override
        public int compareTo(BookedMeeting o) {
            if (this.endTime == o.endTime)
                return Integer.compare(this.room, o.room);
            else
                return Long.compare(this.endTime, o.endTime);
        }
    }

    private final Queue<Integer> availableRooms = new PriorityQueue<>();
    private final Queue<Meeting> upcomingMeetings = new PriorityQueue<>(Comparator.comparingLong(a -> a.startTime));
    private final Queue<BookedMeeting> ongoingMeetings = new PriorityQueue<>();
    private final Map<Integer, Integer> roomStats = new TreeMap<>();

    public int mostBooked(int n, int[][] meetings) {
        for (int[] meeting : meetings)
            upcomingMeetings.add(new Meeting(meeting[0], meeting[1]));
        for (int i = 0; i < n; i++)
            availableRooms.add(i);

        while (!upcomingMeetings.isEmpty()) {
            var currentMeeting = upcomingMeetings.poll();
            while (!ongoingMeetings.isEmpty() && ongoingMeetings.peek().endTime <= currentMeeting.startTime)
                availableRooms.add(ongoingMeetings.poll().room);
            if (!availableRooms.isEmpty()) { // rooms are free
                var availableRoom = availableRooms.poll();
                ongoingMeetings.add(new BookedMeeting(currentMeeting.endTime, availableRoom));
                roomStats.compute(availableRoom, (k, v) -> (v == null) ? 1 : v + 1);
            } else { // wait for ongoing meeting to finish
                if (ongoingMeetings.isEmpty())
                    break;
                var completedMeeting = ongoingMeetings.poll();
                ongoingMeetings.add(new BookedMeeting(completedMeeting.endTime + (currentMeeting.endTime - currentMeeting.startTime),
                        completedMeeting.room));
                roomStats.compute(completedMeeting.room, (k, v) -> (v == null) ? 1 : v + 1);
            }
        }
        int max = Integer.MIN_VALUE;
        int maxRoom = n;
        for (var entry : roomStats.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                maxRoom = entry.getKey();
            }
        }
        return maxRoom;
    }

    public static void main(String[] args) {
        var meetingIII = new MeetingRoomIII();
        System.out.println(meetingIII.mostBooked(2, new int[][]{{0, 10}, {1, 5}, {2, 7}, {3, 4}}));//0
        System.out.println(meetingIII.mostBooked(3, new int[][]{{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}}));//1
    }

}
