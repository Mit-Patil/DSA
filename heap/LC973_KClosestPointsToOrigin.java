class Solution {

    private int sqrt(int[] value){
        int sum = 0;
        for (int n : value) sum += (n*n);
        return sum;
    }

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((int[] a,int[] b) -> Integer.compare(sqrt(b), sqrt(a)));

        for(int[] n : points){
            maxHeap.add(n);
            if(maxHeap.size() > k) maxHeap.poll();
        }

        return maxHeap.toArray(new int[k][]);
    }
}