class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while(left < right){
            int newArea = (right - left) * Math.min(height[left], height[right]);
            if(newArea > maxArea){
                maxArea = newArea;
            }
           if(height[left] < height[right]){
            left++;
           }else{
            right--;
           }
        }

        return maxArea;
    }
}