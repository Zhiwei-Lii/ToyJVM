package rtda;

public class Frame {
    LocalVars localVars;
    OperandStack operandStack;
    Thread thread;
    int pc;

    public Frame(Thread thread, int maxLocals, int maxStack) {
	this.thread = thread;
	this.localVars = new LocalVars(maxLocals);
	this.operandStack = new OperandStack(maxStack);
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
    
    public void setPc(int pc){
	this.pc = pc;
    }
}
