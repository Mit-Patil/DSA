class Solution {
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> mapS = new HashMap<>();
        HashMap<Character, Integer> mapT = new HashMap<>();
        int sLength = s.length();
        int tLength = t.length();

        if(sLength != tLength) return false;

        for (int i = 0; i < sLength; i++){
            mapS.merge(s.charAt(i), 1, Integer::sum);
            mapT.merge(t.charAt(i), 1, Integer::sum);
        }
        
        return mapS.equals(mapT);

    }
}

// -------------------------------------------------------------------

class Solution {
    public boolean isAnagram(String s, String t) {
        int[] count = new int[26];

        if(s.length() != t.length()) return false;

        for(char c : s.toCharArray()) count[c - 'a']++;
        for(char c : t.toCharArray()){
            if(--count[c - 'a'] < 0) return false;
        }
        
        return true;
    }
}