package leetcode.easy.binarysearch;

/**
 * <a href="https://leetcode.com/problems/guess-number-higher-or-lower/">https://leetcode.com/problems/guess-number-higher-or-lower/</a>
 */
public class GuessNumberHigherLower {
    private final int pick;

    private GuessNumberHigherLower(int pick) {
        this.pick = pick;
    }

    private int guess(int n) {
        return Integer.compare(pick - n, 0);
    }

    public int guessNumber(int n) {
        return guessNumber(1, n);
    }

    private int guessNumber(int start, int end) {
        int mid = start + (end - start) / 2;
        int result = guess(mid);
        if (result == 0) return mid;
        if (result == 1) return guessNumber(mid + 1, end);
        return guessNumber(start, mid - 1);
    }

    public static void main(String[] args) {
        System.out.println(new GuessNumberHigherLower(6).guessNumber(10));
        System.out.println(new GuessNumberHigherLower(1).guessNumber(1));
        System.out.println(new GuessNumberHigherLower(1).guessNumber(2));
        System.out.println(new GuessNumberHigherLower(Integer.MAX_VALUE / 2).guessNumber(Integer.MAX_VALUE));
    }

}
