class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];

        for(int i = 0; i < nums.length; i++){
            if(!deque.isEmpty() && deque.peekFirst() <= i - k){
                deque.pollFirst();
            }

            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.pollLast();
            }
            deque.offerLast(i);

            if(i >= k -1){
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }
}