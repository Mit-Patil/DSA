class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int totalStones = 0;

        for(int p : piles) {
            maxHeap.add(p);
            totalStones += p;
        }

        for(int i = 0; i < k; i++){
            int biggest = maxHeap.poll();
            int removed = biggest / 2;
            totalStones -= removed;
            maxHeap.add(biggest - removed);
        }


        return totalStones;
    }
}