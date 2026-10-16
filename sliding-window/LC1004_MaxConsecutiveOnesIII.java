class Solution {
    public int longestOnes(int[] nums, int k) {
        int[] maxFreq = new int[2];
        int left = 0;
        int max = 0;
        for(int right = 0; right < nums.length; right++){
            maxFreq[nums[right]]++;

            while(left < nums.length && maxFreq[0] > k){
                maxFreq[nums[left]]--;
                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        return max;

    }
}