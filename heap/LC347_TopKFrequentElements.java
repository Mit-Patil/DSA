class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        HashMap<Integer, Integer> count = new HashMap<>();
        for(int n : nums) count.merge(n, 1, Integer::sum);

        PriorityQueue<Integer> minHeap =
         new PriorityQueue<>((a,b) -> Integer.compare(count.get(a), count.get(b)));

        for (int n : count.keySet()){
            minHeap.add(n);
            if(minHeap.size() > k) minHeap.poll();
        }

        int[] ans = new int[k];
        for(int i = 0; i < k; i++) ans[i] = minHeap.poll();
        return ans;

    }
}