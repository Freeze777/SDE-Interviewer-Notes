package leetcode.medium.arrays;

import java.awt.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/equal-row-and-column-pairs/description/">Equal Row and Column Pairs</a>
 */
public class EqualRowColumnPairs {
    private String getMD5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] hashBytes = md.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                hexString.append(String.format("%02x", hashByte));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public int equalPairs(int[][] arr) {
        StringBuilder[] rows = new StringBuilder[arr.length];
        StringBuilder[] columns = new StringBuilder[arr.length];
        for (int i = 0; i < arr.length; i++) {
            rows[i] = new StringBuilder();
            columns[i] = new StringBuilder();
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                rows[i].append(arr[i][j]).append(",");
                columns[j].append(arr[i][j]).append(",");
            }
        }

        Map<String, Point> pairs = new HashMap<>();
        Arrays.stream(rows).forEach(row -> pairs.compute(getMD5Hash(row.toString()),
                (key, value) -> value == null ? new Point(1, 0) : new Point(value.x + 1, value.y)));
        Arrays.stream(columns).forEach(column -> pairs.compute(getMD5Hash(column.toString()),
                (key, value) -> value == null ? new Point(0, 1) : new Point(value.x, value.y + 1)));

        return pairs.values()
                .stream()
                .mapToInt(p -> p.x * p.y)
                .sum();
    }

    public static void main(String[] args) {
        var ercp = new EqualRowColumnPairs();
        System.out.println(ercp.equalPairs(new int[][]{{1, 3, 4, 5}, {3, 4, 4, 5}, {4, 3, 4, 5}, {5, 3, 4, 5}}));//1
        System.out.println(ercp.equalPairs(new int[][]{{1, 3, 1}, {3, 10, 3}, {1, 3, 1}}));//5
    }

}
