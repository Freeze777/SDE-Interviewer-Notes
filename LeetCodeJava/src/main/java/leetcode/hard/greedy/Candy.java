package leetcode.hard.greedy;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/candy/">https://leetcode.com/problems/candy/</a>
 */
public class Candy {

    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1); // everyone gets at-least 1 candy
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) candies[i] = candies[i - 1] + 1;
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) candies[i] = candies[i + 1] + 1;
        }
        return Arrays.stream(candies).sum();
    }

    public static void main(String[] args) {
        Candy c = new Candy();
        System.out.println(c.candy(new int[]{1, 0, 2})); //5
        System.out.println(c.candy(new int[]{1, 2, 2})); //4
        System.out.println(c.candy(new int[]{1, 2, 87, 87, 87, 2, 1})); //13
    }
}
