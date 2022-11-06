package leetcode.hard.arrays;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/orderly-queue/">https://leetcode.com/problems/orderly-queue/</a>
 */
public class OrderlyQueue {
    private void reverse(char[] data, int left, int right) {
        for (; left < right; left++, right--) {
            char temp = data[left];
            data[left] = data[right];
            data[right] = temp;
        }
    }

    private char[] rotate(char[] arr) {
        reverse(arr, 1, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
        return arr;
    }

    public String orderlyQueue(String s, int k) {
        char[] arr = s.toCharArray();
        String min = s;
        if (k > 1) {
            Arrays.sort(arr);
            min = String.valueOf(arr);
        } else {
            for (int i = 0; i < arr.length; i++) {
                rotate(arr);
                String rotation = String.valueOf(arr);
                min = rotation.compareTo(min) < 0 ? rotation : min;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        OrderlyQueue oq = new OrderlyQueue();
        System.out.println(oq.orderlyQueue("cba", 1));
        System.out.println(oq.orderlyQueue("baaca", 3));
    }
}
