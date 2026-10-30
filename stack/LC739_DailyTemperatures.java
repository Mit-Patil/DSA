class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for(int i = 0; i < n; i++){
            while(top >= 0 && temperatures[stack[top]] < temperatures[i]){
                int prev = stack[top--];
                answer[prev] = i - prev;
            }
            stack[++top] = i;
        }

        return answer;
    }
}

// --------------------------------------------------------------

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < n; i++){
            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                int prev = stack.pop();
                answer[prev] = i - prev;
            }
            stack.push(i);
        }

        return answer;
    }
}