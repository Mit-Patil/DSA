class Solution {
    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()){
            int last = sb.length() - 1;
            if(last >= 0 && sb.charAt(last) == c) sb.deleteCharAt(last);
            else sb.append(c);
        }

        return sb.toString();
    }
}

// ----------------------------------------------------------------------------

class Solution {
    public String removeDuplicates(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for ( int i = 0; i < s.length(); i++ ){
            char c = s.charAt(i);
            if(!stack.isEmpty() && stack.peekLast() == c) stack.removeLast();
            else stack.addLast(c);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack) sb.append(c);
        return sb.toString();
    }
}