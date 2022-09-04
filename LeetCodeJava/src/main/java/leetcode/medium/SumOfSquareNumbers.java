package leetcode.medium;

public class SumOfSquareNumbers {

    public boolean judgeSquareSum(int c) {
        long max = (int) Math.sqrt(c);
        long min = 0;
        while (min <= max) {
            long sum = (min * min) + (max * max);
            if (sum == c) {
                return true;
            }
            long tmp = ((sum > c) ? max-- : min++);
        }
        return false;
    }

    public static void main(String[] args) {
        SumOfSquareNumbers sumOfSquareNumbers = new SumOfSquareNumbers();

        // true
        System.out.println(sumOfSquareNumbers.judgeSquareSum(101));
        System.out.println(sumOfSquareNumbers.judgeSquareSum(100));
        System.out.println(sumOfSquareNumbers.judgeSquareSum(5));
        System.out.println(sumOfSquareNumbers.judgeSquareSum(2));
        System.out.println(sumOfSquareNumbers.judgeSquareSum(162));
        System.out.println(sumOfSquareNumbers.judgeSquareSum(2147483600));


        // false
        System.out.println(sumOfSquareNumbers.judgeSquareSum(3));
        System.out.println(sumOfSquareNumbers.judgeSquareSum(99));
        System.out.println(sumOfSquareNumbers.judgeSquareSum(2147483647));
    }
}

