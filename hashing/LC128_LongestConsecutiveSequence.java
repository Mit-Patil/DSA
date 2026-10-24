class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int x : nums) set.add(x);

        int best = 0;
        for(int x : set){
            if(!set.contains(x - 1)){
                int length = 1;
                int current = x;

                while(set.contains(current + 1)){
                    length++;
                    current++;
                }

                best = Math.max(best, length);
            }
        }

        return best;

    }
}