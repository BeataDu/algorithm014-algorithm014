package test20200905;

public class MaxProfitObj {
    public int maxProfit(int[] prices) {
        int res = 0;
        int len = prices.length;
        for (int i = 0; i < len - 1; i++) {
            res += Math.max(prices[i + 1] - prices[i], 0);
        }
        return res;
    }
}
