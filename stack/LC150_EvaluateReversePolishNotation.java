class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        
        for(String s : tokens){
            if(s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*")){
                int secondValue = stack.pop();
                int firstValue = stack.pop();

                int ans = switch(s){
                    case "+" -> firstValue + secondValue;
                    case "-" -> firstValue - secondValue;
                    case "*" -> firstValue * secondValue;
                    case "/" -> firstValue / secondValue;
                    default -> throw new IllegalStateException("Unexpected value: " + s);
                };

                stack.push(ans);

            }else{
                stack.push(Integer.valueOf(s));
            }
        }

        return stack.peek();
    }
}