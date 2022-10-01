package leetcode.medium.math;

import java.util.Set;
import java.util.TreeSet;

public class KthFactorOfN {

    public int kthFactor(int n, int k) {
        int mid = (int) Math.ceil(Math.sqrt(n));
        Set<Integer> ts = new TreeSet<>();
        for (int i = 1; i <= mid; i++) {
            if (n % i == 0) {
                ts.add(i);
                ts.add(n / i);
            }
        }
        Object[] factors = ts.toArray();
        return factors.length < k ? -1 : (int) factors[k - 1];
    }

    public static void main(String[] args) {
        KthFactorOfN kthFactorOfN = new KthFactorOfN();
        System.out.println(kthFactorOfN.kthFactor(1, 1));
        System.out.println(kthFactorOfN.kthFactor(4, 4));
        System.out.println(kthFactorOfN.kthFactor(2, 2));
        System.out.println(kthFactorOfN.kthFactor(7, 2));
        System.out.println(kthFactorOfN.kthFactor(12, 3));
    }


}
