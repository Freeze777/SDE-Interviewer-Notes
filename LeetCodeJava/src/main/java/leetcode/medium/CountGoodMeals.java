package leetcode.medium;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CountGoodMeals {

    private static int countGoodMeals(int[] deliciousness) {
        int mod = 1000000007;
        int[] powersOfTwo = new int[22];
        Arrays.fill(powersOfTwo, 1);
        for (int i = 1; i < powersOfTwo.length; i++) {
            powersOfTwo[i] = powersOfTwo[i - 1] * 2;
        }


        Map<Integer, Long> frequency = Arrays.stream(deliciousness).boxed()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        int result = 0;
        for (Integer key : frequency.keySet()) {
            for (int j : powersOfTwo) {
                if (j >= key) {
                    int other = j - key;
                    if (frequency.containsKey(other)) {
                        if (key > other) {
                            result += (frequency.get(key) * frequency.get(other));
                        } else if (key == other) {
                            result += ((frequency.get(key) * (frequency.get(other) - 1)) / 2);
                        }
                    }
                    result = result > mod ? result % mod : result;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(countGoodMeals(new int[]{1, 3, 5, 7, 9})); // 4
        System.out.println(countGoodMeals(new int[]{1, 1, 1, 3, 3, 3, 7})); // 15
        System.out.println(countGoodMeals(new int[]{149, 107, 1, 63, 0, 1, 6867, 1325, 5611, 2581, 39, 89, 46, 18, 12, 20, 22, 234})); // 12
        System.out.println(countGoodMeals(new int[]{64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64})); // 528
    }
}
