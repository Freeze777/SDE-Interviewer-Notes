package leetcode.medium.greedy;

import java.util.Arrays;

/**
 * <a href="https://www.geeksforgeeks.org/convert-array-into-zig-zag-fashion/">https://www.geeksforgeeks.org/convert-array-into-zig-zag-fashion</a>
 */
public class ZigZagConversion {

    // a[0] < a[1] > a[2] < a[3] > a[4] < a[5] > a[6] < a[7] > a[8] < a[9]
    public int[] convert(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                if (i + 1 < arr.length && arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            } else {
                if (i + 1 < arr.length && arr[i] < arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
        return arr;
    }

    private void swap(int[] arr, int i, int i1) {
        int temp = arr[i];
        arr[i] = arr[i1];
        arr[i1] = temp;
    }

    public static void main(String[] args) {
        var zigZagConversion = new ZigZagConversion();
        System.out.println(Arrays.toString(zigZagConversion.convert(new int[]{4, 3, 7, 8, 6, 2, 1})));
        System.out.println(Arrays.toString(zigZagConversion.convert(new int[]{1, 4, 3, 2})));
        System.out.println(Arrays.toString(zigZagConversion.convert(new int[]{1, 4, 3, 2, 5})));
        System.out.println(Arrays.toString(zigZagConversion.convert(new int[]{1, 4, 3, 2, 5, 6})));
        System.out.println(Arrays.toString(zigZagConversion.convert(new int[]{1, 2, 3, 4, 5, 6, 7})));
    }
}
