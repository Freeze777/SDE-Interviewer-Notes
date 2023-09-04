package leetcode.medium.math;

public class BinomialCoefficient {

    // nCr = n! / (r! * (n-r)!)
    // nCr = nC(n-r)
    // nCr = n-1Cr-1 + n-1Cr
    // nCr = n * n-1 * n-2 * ... * n-r+1 / 1 * 2 * 3 * ... * r
    public long nCr(int n, int r) {
        long numerator = 1;
        long denominator = 1;
        r = Math.min(r, n - r);
        for (int i = 1; i <= r; i++) {
            numerator *= (n - r + i);
            denominator *= i;
        }
        return numerator / denominator;
    }

    public static void main(String[] args) {
        System.out.println(new BinomialCoefficient().nCr(5, 2));
        System.out.println(new BinomialCoefficient().nCr(20, 10));
        System.out.println(new BinomialCoefficient().nCr(20, 7));
        System.out.println(new BinomialCoefficient().nCr(20, 13));
        System.out.println(new BinomialCoefficient().nCr(30, 13));
        System.out.println(new BinomialCoefficient().nCr(50, 17)); // overflow
    }
}
