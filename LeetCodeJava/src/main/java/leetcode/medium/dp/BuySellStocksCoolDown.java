package leetcode.medium.dp;

import java.awt.*;
import java.util.*;

/**
 * 309. Best Time to Buy and Sell Stock with Cooldown
 * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/">https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/</a>
 */
public class BuySellStocksCoolDown {

    private int maxProfit(int currentDay, int prevBuyDay, int[] prices, Map<Point, Integer> cache) {
        if (currentDay >= prices.length) return 0;
        var cacheKey = new Point(currentDay, prevBuyDay);
        if (cache.containsKey(cacheKey)) return cache.get(cacheKey);
        var maxProfit = 0;
        if (prevBuyDay == -1) {
            var buyNow = maxProfit(currentDay + 1, currentDay, prices, cache);
            var dontBuyNow = maxProfit(currentDay + 1, -1, prices, cache);
            maxProfit = Math.max(buyNow, dontBuyNow);
        } else if (prices[prevBuyDay] < prices[currentDay]) {
            var sellNow = (prices[currentDay] - prices[prevBuyDay]) + maxProfit(currentDay + 2, -1, prices, cache);
            var dontSellNow = maxProfit(currentDay + 1, prevBuyDay, prices, cache);
            maxProfit = Math.max(sellNow, dontSellNow);
        }
        cache.put(cacheKey, maxProfit);
        return maxProfit;
    }

    public int maxProfit(int[] prices) {
        return maxProfit(0, -1, prices, new HashMap<>());
    }

    public static void main(String[] args) {
        var bssc = new BuySellStocksCoolDown();
        System.out.println(bssc.maxProfit(new int[]{1, 2, 3, 0, 2}));//3
        System.out.println(bssc.maxProfit(new int[]{1}));//0
        System.out.println(bssc.maxProfit(new int[]{1, 2}));//1
        System.out.println(bssc.maxProfit(new int[]{2, 1}));//0
        System.out.println(bssc.maxProfit(new int[]{2, 1, 2, 0, 1}));//1
    }
}
