package leetcode.hard.unionfind;

import leetcode.medium.unionfind.UnionFind;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/find-all-people-with-secret/">2092. Find All People With Secret</a>
 */
public class PeopleWithSecret {
    private record Meeting(int attendee, int otherAttendee) {
    }

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        var meetingsByTime = new TreeMap<Integer, List<Meeting>>();
        var secretHolders = new HashSet<Integer>();
        secretHolders.add(0);
        secretHolders.add(firstPerson);
        Arrays.stream(meetings).forEach(meeting ->
                meetingsByTime.compute(meeting[2], (key, value) -> {
                    var currentMeetings = value != null ? value : new ArrayList<Meeting>();
                    currentMeetings.add(new Meeting(meeting[0], meeting[1]));
                    return currentMeetings;
                }));

        for (var meetingsNow : meetingsByTime.entrySet()) {
            var attendees = new ArrayList<Integer>();
            var secretHoldersAtStart = new HashSet<Integer>();
            for (var meeting : meetingsNow.getValue()) {
                attendees.add(meeting.attendee);
                attendees.add(meeting.otherAttendee);
                if (secretHolders.contains(meeting.attendee)) secretHoldersAtStart.add(meeting.attendee);
                if (secretHolders.contains(meeting.otherAttendee)) secretHoldersAtStart.add(meeting.otherAttendee);
            }
            var unionFind = new UnionFind(attendees);
            for (var meeting : meetingsNow.getValue()) unionFind.union(meeting.attendee, meeting.otherAttendee);
            var groups = unionFind.groupByParent(false);
            for (var secretHolder : secretHoldersAtStart)
                secretHolders.addAll(groups.get(unionFind.find(secretHolder)));
        }

        return secretHolders.stream().toList();
    }

    public static void main(String[] args) {
        var pws = new PeopleWithSecret();
        System.out.println(pws.findAllPeople(6, new int[][]{{1, 2, 5}, {2, 3, 8}, {1, 5, 10}}, 1));
        System.out.println(pws.findAllPeople(4, new int[][]{{3, 1, 3}, {1, 2, 2}, {0, 3, 3}}, 3));
        System.out.println(pws.findAllPeople(5, new int[][]{{3, 4, 2}, {1, 2, 1}, {2, 3, 1}}, 1));
        System.out.println(pws.findAllPeople(4, new int[][]{{3, 1, 3}, {1, 2, 2}, {2, 1, 3}, {0, 3, 3}}, 3));
    }
}
