class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] i1 = new int[26];
        int[] i2 = new int[26];
        int k = s1.length();
        int left = 0;

        for(int i = 0 ; i < k; i++){
            char c = s1.charAt(i);
            i1[c - 'a']++;
        }

        for(int right = 0; right < s2.length(); right++){
            char s2Char = s2.charAt(right);
            i2[s2Char - 'a']++;
            if((right - left + 1) == k){
                if(Arrays.equals(i1, i2)){
                    return true;
                }
                char s2CharLeft = s2.charAt(left);
                i2[s2CharLeft - 'a']--;
                left++;
            }
        }
            return false;

    }
}