package instructions.references;

import instructions.base.Index16Instruction;
import rtda.Frame;
import rtda.OperandStack;
import rtda.heap.ConstantPool;
import rtda.heap.constant.ConstantMethodRef;

public class INVOKE_VIRTUAL extends Index16Instruction {

    public void execute(Frame frame) {
	ConstantPool cp = frame.method().class_().constantPool();
	ConstantMethodRef methodRef = (ConstantMethodRef) cp.getConstant((int) index);

	if (methodRef.methodName().equals("println")) {
	    OperandStack stack = frame.operandStack();

	    if (methodRef.descriptor().equals("(Z)V")) {
		System.out.println(stack.popInt());
	    } else if (methodRef.descriptor().equals("(B)V")) {
		System.out.println(stack.popInt());
	    } else if (methodRef.descriptor().equals("(S)V")) {
		System.out.println(stack.popInt());
	    } else if (methodRef.descriptor().equals("(I)V")) {
		System.out.println(stack.popInt());
	    } else {
		System.out.println("Unsupported printf");
	    }
	    
	    stack.popRef();
	}
    }

}
