package leetcode.medium.stack;

import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/online-stock-span/description/">https://leetcode.com/problems/online-stock-span/description/</a>
 */
public class StockSpanner {

    private static class StockSpan {
        int stockPrice;
        int span;

        public StockSpan(int stockPrice, int span) {
            this.stockPrice = stockPrice;
            this.span = span;
        }

        @Override
        public String toString() {
            return "StockSpan{" + "stockPrice=" + stockPrice + ", span=" + span + '}';
        }
    }

    private final Stack<StockSpan> spanStack = new Stack<>();

    public StockSpanner() {
    }

    public int next(int price) {
        if (spanStack.empty()) spanStack.add(new StockSpan(price, 1));
        else {
            int span = 1;
            while (!spanStack.empty() && spanStack.peek().stockPrice <= price) {
                span += spanStack.pop().span;
            }
            spanStack.add(new StockSpan(price, span));
        }
        return spanStack.peek().span;
    }

    public static void main(String[] args) {
        StockSpanner ss = new StockSpanner();
        int[] stocks = new int[]{100, 80, 60, 70, 60, 75, 85, 200};
        for (int stock : stocks) {
            System.out.println(ss.next(stock));
        }
    }
}
