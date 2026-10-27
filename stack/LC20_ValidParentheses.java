class Solution {
    public boolean isValid(String s) {
        if(s.length() % 2 == 1) return false;

        Deque<Character> stack = new ArrayDeque<>();
        HashMap<Character, Character> map = new HashMap<>();

        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        for(char c : s.toCharArray()){
            if(map.containsKey(c)){
                if(stack.isEmpty()) return false;
                char top = stack.pop();
                if(map.get(c) != top) return false;
            }else{
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}

// -------------------------------------------------------------------

class Solution {
    public boolean isValid(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if(c == '(' || c == '{' || c == '['){
                stack.push(c);
            }
            else{
                if(stack.isEmpty()) return false;
                char top = stack.pop();
                if(c == ')' && top != '(') return false;
                else if(c == '}' && top != '{') return false;
                else if(c == ']' && top != '[') return false;
            }
        }
        
        
        return stack.isEmpty();
    }
}