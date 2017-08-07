package rtda;

import java.util.Stack;

public class Thread {
    private int pc; // 这里的pc保留的是frame里的pc的上一个位置 参见Interpreter.java
    private Stack<Frame> stack;

    public Thread() {
        pc = 0;
        stack = new Stack<Frame>();
    }

    public int pc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void pushFrame(Frame frame) {
        stack.push(frame);
    }

    public Frame popFrame() {
        return stack.pop();
    }

    public Frame topFrame() {
        return stack.peek();
    }

    public boolean isStackEmpty() {
        return stack.isEmpty();
    }
}
