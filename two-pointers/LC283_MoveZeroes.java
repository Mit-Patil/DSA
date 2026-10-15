class Solution {
    public void moveZeroes(int[] nums) {
      int left = 0;
      int right = 1;

      while(right < nums.length){
        if(nums[left] == 0){
            if(nums[right] !=0){
                nums[left] =  nums[right];
                nums[right] = 0;
                left++;
                right++;
            }
            else{
                right++;
            }
        }else{
                left++;
                right++;   
        }
      }

    }
}