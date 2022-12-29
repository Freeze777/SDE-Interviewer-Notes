package leetcode.medium.dp;

/**
 * 790. Domino and Tromino Tiling
 * <a href="https://leetcode.com/problems/domino-and-tromino-tiling/">https://leetcode.com/problems/domino-and-tromino-tiling/</a>
 */
public class DominoTrominoTiling {

    /**
     * linear equation <br>
     * f(n) = x * f(n-1) + y * f(n-2) + z * f(n-3) <br>
     * n = 4 => 5x + 2y + z = 11 <br>
     * n = 5 => 11x + 5y + 2z = 24 <br>
     * n = 6 => 24x + 11y + 5z = 53 <br>
     * with 3 equations we can find 3 unknowns. <br>
     * x = 2 <br>
     * y = 0 <br>
     * z = 1 <br>
     * f(n) = 2*f(n-1) + f(n-3)
     */
    public int numTilings(int n) {
        if (n <= 2) return n;
        int[] cache = new int[n + 1];
        int mod = 1000000007;
        cache[0] = cache[1] = 1;
        cache[2] = 2;
        for (int i = 3; i <= n; i++)
            cache[i] = ((2 * cache[i - 1] % mod) + cache[i - 3]) % mod;
        return cache[n];
    }

    public static void main(String[] args) {
        var dtt = new DominoTrominoTiling();
        System.out.println(dtt.numTilings(3));//5
        System.out.println(dtt.numTilings(4));//11
        System.out.println(dtt.numTilings(5));//24
        System.out.println(dtt.numTilings(6));//53
        System.out.println(dtt.numTilings(1000));//979232805
    }
}
