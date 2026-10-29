class MinStack {

    Deque<Integer> stack;
    Deque<Integer> minStack;

    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }
    
    public void push(int value) {
        stack.push(value);
        int min = minStack.isEmpty() ? value : Math.min(value, minStack.peek());
        minStack.push(min);
    }
    
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

// -----------------------------------------------------------------------

class MinStack {

    Deque<Integer> stack;
    int min;

    public MinStack() {
        stack = new ArrayDeque<>();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int value) {
        if(value <= min){
            stack.push(min);
            min = value;
        }
        stack.push(value);
    }
    
    public void pop() {
        if(stack.pop() == min){
            min = stack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}

// ----------------------------------------------------------------------

class MinStack {

    Deque<int[]> stack;
    public MinStack() {
        stack  = new ArrayDeque<>();
    }
    
    public void push(int value) {
        int min = stack.isEmpty() ? value: Math.min(value, stack.peek()[1]);
        stack.push(new int[]{value, min});
    }
    
    public void pop() {
        stack.pop();
    }
    
    public int top() {
        return stack.peek()[0];
    }
    
    public int getMin() {
        return stack.peek()[1];
    }
}

