class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> lastSeen = new HashMap<>();
        int start = 0, maxLength = 0;

        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (lastSeen.containsKey(c) && lastSeen.get(c) >= start) {
                start = lastSeen.get(c) + 1;
            }
            lastSeen.put(c, end);
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }
}