class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < magazine.length(); i++) {
            map.merge(magazine.charAt(i), 1, Integer::sum);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            int have = map.getOrDefault(c, 0);
            if (have == 0) return false; 
            map.put(c, have - 1);
        }

        return true;
    }
}

// ------------------------------------------------------------

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < magazine.length(); i++) {
            map.merge(magazine.charAt(i), 1, Integer::sum);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            int have = map.getOrDefault(c, 0);
            if (have == 0) return false; 
            map.put(c, have - 1);
        }

        return true;
    }
}