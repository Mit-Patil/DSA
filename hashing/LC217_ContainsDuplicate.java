class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();

        for (int x : nums){
            if(!seen.add(x)) return true;
        }

        return false;
    }
}