package rtda;

import rtda.heap.Method;

public class Frame {
    LocalVars localVars;
    OperandStack operandStack;
    Thread thread;
    Method method;
    int pc;

    public Frame(Thread thread, Method method) {
        this.thread = thread;
        this.method = method;
        this.localVars = new LocalVars(method.maxLocals());
        this.operandStack = new OperandStack(method.maxStack());
    }

    public LocalVars localVars() {
        return localVars;
    }

    public OperandStack operandStack() {
        return operandStack;
    }

    public Thread thread() {
        return thread;
    }

    public int pc() {
        return pc;
    }

    public Method method() {
        return method;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void unrollPc() {
        this.pc = this.thread.pc(); // thread持有的pc是取指前的pc
    }

}
