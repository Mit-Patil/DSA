class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        Arrays.fill(ans, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < 2*n; i++){
            int x = nums[i % n];
            while(!stack.isEmpty() && nums[stack.peek()] < x){
                ans[stack.pop()] = x;
            }
            if(i < n) stack.push(i);
        }

        return ans;
    }
}