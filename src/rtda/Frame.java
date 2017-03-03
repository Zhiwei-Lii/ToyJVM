package rtda;

public class Frame {
    LocalVars localVars; // local variables table
    OperandStack operandStack;

    public Frame(int maxLocals, int maxStack){
	localVars = new LocalVars(maxLocals);
	operandStack = new OperandStack(maxStack);
    }
    
    public LocalVars localVars(){
	return localVars;
    }
    
    public OperandStack operandStack(){
	return operandStack;
    }

}
