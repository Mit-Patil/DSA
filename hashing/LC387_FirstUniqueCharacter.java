class Solution {
    public int firstUniqChar(String s) {
        int[] unique = new int[26];

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            unique[c - 'a']++;
        }

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(unique[c - 'a'] == 1){
                return i;
            }
        }

        return -1;
    }
}

// -------------------------------------------------------------

class Solution {
    public int firstUniqChar(String s) {
    HashMap<Character, Integer> map = new HashMap<>();

    for(int i = 0; i < s.length(); i++){
        char c = s.charAt(i);
        map.merge(c, 1, Integer::sum);
    }
        for( int i = 0 ; i < s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c) && map.get(c) == 1) return i;
        }

        return -1;
    }
}

// ----------------------------------------------------------------

class Solution {
    public int firstUniqChar(String s) {
    HashMap<Character, Integer> map = new HashMap<>();

    for(int i = 0; i < s.length(); i++){
        char c = s.charAt(i);
        if(map.containsKey(c)) map.put(c, -1);
        else map.put(c, i);
    }

    int ans = Integer.MAX_VALUE;
    for(int idx: map.values()){
        if(idx!= -1 && idx < ans) ans = idx;
    }

    return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }
}