class Solution {
    public int[] searchRange(int[] nums, int target) {
       int n = nums.length;
       return new int[]{findFirst(nums, target, n), findLast(nums, target, n)};
    }

    private int findFirst(int[] nums, int target, int n){
        int left = 0;
        int right = n - 1;
        int ans = -1;

        while(left <= right){
            int mid = left + (right - left)/2;

            if(nums[mid] == target){
                ans = mid;
                right = mid - 1;
            }else if(nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }

        return ans;
    }

    private int findLast(int[] nums, int target, int n){
        int left = 0, right = n - 1;
        int ans = -1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                ans = mid;
                left = mid + 1;
            }else if(nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }

        return ans;
    }
}