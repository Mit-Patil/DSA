class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
      HashMap<String, List<String>> map = new HashMap<>();

      for(String word : strs){
        char[] letters = word.toCharArray();
        Arrays.sort(letters);
        String label = new String(letters);

        if(!map.containsKey(label)){
            map.put(label, new ArrayList<>());
        }
        map.get(label).add(word);
      }

      return new ArrayList<>(map.values());
    }
}