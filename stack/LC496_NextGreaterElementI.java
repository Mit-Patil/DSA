class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
       HashMap<Integer, Integer> map = new HashMap<>();
       Deque<Integer> stack = new ArrayDeque<>();

       for(int x : nums2){
            while(!stack.isEmpty() && stack.peek() < x){
                map.put(stack.pop(), x);
            }
            stack.push(x);
        }

        int[] ans = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++){
            ans[i] = map.getOrDefault(nums1[i], -1);
        }

        return ans;

    }
}

// --------------------------------------------------------------------

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        int[] ans = new int[n1];
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < n2; i++) map.put(nums2[i], i);

        for(int i = 0; i < n1; i++){
            if(map.containsKey(nums1[i])){
                int current = map.get(nums1[i]);
                int j = current + 1;
                while(j < n2 && nums2[j] <= nums2[current]) j++;
                ans[i] = (j < n2) ? nums2[j] : -1;
            }else{
                ans[i] = -1;
            }
        }

        return ans;
    }
}