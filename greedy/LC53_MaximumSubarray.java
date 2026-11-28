class Solution {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;

        for(int n : nums){
            sum += n;
            maxSum = Math.max(sum, maxSum);
            if(sum < 0) sum = 0;
        }

        return maxSum;
    }
}