class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int best = 0;

        for(int price : prices){
            if (price < minPrice) minPrice = price;
            else best = Math.max(best, price - minPrice);
        }

        return best;
    }
}