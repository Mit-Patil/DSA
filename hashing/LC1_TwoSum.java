class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            Integer j = map.get(target - nums[i]);

            if(j != null) return new int[]{j, i};

            map.put(nums[i], i);
        }

        return new int[]{};
    }
}