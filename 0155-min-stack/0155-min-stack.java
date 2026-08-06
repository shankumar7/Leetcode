class MinStack {
    Stack<Integer> s1, s2;

    public MinStack() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int value) {
        s1.push(value);  

        if (s2.isEmpty()) {
            s2.push(value);
        } else {
            s2.push(Math.min(value, s2.peek()));
        }
    }

    public void pop() {
        s1.pop();
        s2.pop();
    }

    public int top() {
        return s1.peek();
    }

    public int getMin() {
        return s2.peek();
    }
}