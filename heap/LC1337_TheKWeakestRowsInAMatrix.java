class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
       int m = mat.length;
       int[] soldiers = new int[m];

       for(int i = 0; i < m; i++){
        for (int j = 0; j < mat[i].length; j++) {
            if(mat[i][j] == 1) soldiers[i]++;
       }
    }

    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> {
        if(soldiers[a] != soldiers[b]) return Integer.compare(soldiers[b], soldiers[a]);
        return Integer.compare(b,a);
    });

    for (int i = 0; i < m; i++) {
        maxHeap.add(i);
        if (maxHeap.size() > k) maxHeap.poll();
    }

    int[] result = new int[k];
    for (int i = k - 1; i >= 0; i--) {
        result[i] = maxHeap.poll();
    }

    return result;
    }
}