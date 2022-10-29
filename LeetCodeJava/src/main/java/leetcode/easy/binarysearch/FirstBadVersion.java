package leetcode.easy.binarysearch;

/**
 * <a href="https://leetcode.com/problems/first-bad-version/">https://leetcode.com/problems/first-bad-version/</a>
 */
public class FirstBadVersion {
    private final int bad;

    private FirstBadVersion(int bad) {
        this.bad = bad;
    }

    private boolean isBadVersion(int version) {
        return version >= bad;
    }

    public int firstBadVersion(int n) {
        return firstBadVersion(1, n);
    }

    private int firstBadVersion(int start, int end) {
        int mid = (start + end) >>> 1; // avoids overflows
        boolean isMidBad = isBadVersion(mid);
        if (isMidBad) {
            boolean isPrevBad = isBadVersion(mid - 1);
            if (isPrevBad) return firstBadVersion(start, mid - 1);
            else return mid;
        } else {
            boolean isNextBad = isBadVersion(mid + 1);
            if (isNextBad) return mid + 1;
            else return firstBadVersion(mid + 1, end);
        }
    }

    public static void main(String[] args) {
        System.out.println(new FirstBadVersion(4).firstBadVersion(5)); //4
        System.out.println(new FirstBadVersion(1).firstBadVersion(1)); //1
        System.out.println(new FirstBadVersion(3).firstBadVersion(7)); //3
        System.out.println(new FirstBadVersion(6).firstBadVersion(7)); //6
        System.out.println(new FirstBadVersion(1702766719).firstBadVersion(2126753390)); //1702766719
        System.out.println(new FirstBadVersion(Integer.MAX_VALUE / 2).firstBadVersion(Integer.MAX_VALUE)); //1073741823
    }
}
