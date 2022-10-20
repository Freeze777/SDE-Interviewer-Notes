package leetcode.medium.math;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class IntegerToRoman {
    int[] powerOfTen;
    Map<Integer, String> fixedRomanLetters = new HashMap<>();

    IntegerToRoman() {
        powerOfTen = new int[]{1, 10, 100, 1000};
        fixedRomanLetters.put(1, "I");
        fixedRomanLetters.put(5, "V");
        fixedRomanLetters.put(10, "X");
        fixedRomanLetters.put(50, "L");
        fixedRomanLetters.put(100, "C");
        fixedRomanLetters.put(500, "D");
        fixedRomanLetters.put(1000, "M");
    }

    public String intToRoman(int num) {
        return intToRoman(Integer.toString(num).toCharArray(), 0);
    }

    private String intToRoman(char[] num, int placeIndex) {
        if (placeIndex >= num.length) return "";
        int significantDigit = num[placeIndex] - '0';
        int tenthPower = powerOfTen[num.length - placeIndex - 1];
        return getRootRomanDigit(significantDigit, tenthPower) + intToRoman(num, placeIndex + 1);
    }

    private String getRootRomanDigit(int significantDigit, int tenthPower) {
        if (significantDigit == 1 || significantDigit == 5) {
            return fixedRomanLetters.get(significantDigit * tenthPower);
        } else if (significantDigit == 4) {
            return fixedRomanLetters.get(tenthPower) + fixedRomanLetters.get(5 * tenthPower);
        } else if (significantDigit < 4) {
            return String.join("", Collections.nCopies(significantDigit, fixedRomanLetters.get(tenthPower)));
        } else if (significantDigit == 9) {
            return fixedRomanLetters.get(tenthPower) + fixedRomanLetters.get(10 * tenthPower);
        } else {
            return fixedRomanLetters.get(5 * tenthPower) + String.join("", Collections.nCopies((significantDigit - 5), fixedRomanLetters.get(tenthPower)));
        }
    }


    public static void main(String[] args) {
        IntegerToRoman itr = new IntegerToRoman();
        System.out.println(itr.intToRoman(1994));
        System.out.println(itr.intToRoman(1000));
        System.out.println(itr.intToRoman(2000));
        System.out.println(itr.intToRoman(900));
        System.out.println(itr.intToRoman(90));
        System.out.println(itr.intToRoman(200));
        System.out.println(itr.intToRoman(700));
        System.out.println(itr.intToRoman(400));
    }
}
