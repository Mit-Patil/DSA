class Solution {
    public double findMaxAverage(int[] nums, int k) {
        long subArraySum = 0;

        for(int i=0; i < k; i++){
            subArraySum += nums[i];
        }

        long maxSum = subArraySum;
        for(int i = k; i < nums.length; i++){
            subArraySum = (nums[i] + subArraySum) - nums[i-k];
            if(subArraySum > maxSum){
                maxSum = subArraySum; 
            }
        }

        return (double)maxSum/k;
    }
}