package rtda;
import java.util.Stack;

public class Thread {
    private int pc;
    private Stack<Frame> stack;
    
    public Thread(){
	pc = 0;
	stack = new Stack<Frame>();
    }
    
    public int pc(){
	return pc;
    }
    
    public void setPc(int pc){
	this.pc = pc;
    }
    
    public void pushFrame(Frame frame){
	stack.push(frame);
    }
    
    public Frame popFrame(){
	return stack.pop();
    }
    
    public Frame currentFrame(){
	return stack.peek();
    }
}
