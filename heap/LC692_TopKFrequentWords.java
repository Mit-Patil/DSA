class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        PriorityQueue<String> minHeap = new PriorityQueue<>((a,b) ->
        {
            int ca = map.get(a);
            int cb = map.get(b);
            if(ca != cb) return Integer.compare(ca, cb);
            return b.compareTo(a);
        } );

        for(String s : words) map.merge(s, 1, Integer::sum);

        for(String word : map.keySet()){
            minHeap.add(word);
            if(minHeap.size() > k) minHeap.poll();
        }

        List<String> result = new ArrayList<>();
        while(!minHeap.isEmpty()){
            result.add(minHeap.poll());
        }

        Collections.reverse(result);

        return result;
    }
}