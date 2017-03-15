package instructions.references;

import instructions.base.Index16Instruction;
import rtda.Frame;
import rtda.OperandStack;
import rtda.heap.ConstantPool;
import rtda.heap.Object;
import rtda.heap.Class;
import rtda.heap.constant.ConstantClassRef;

public class CHECK_CAST extends Index16Instruction {

    public void execute(Frame frame) {
	OperandStack stack = frame.operandStack();
	Object ref = stack.popRef();
	stack.pushRef(ref);

	if (ref == null) {
	    return;
	}

	ConstantPool cp = frame.method().class_().constantPool();
	ConstantClassRef clRef = (ConstantClassRef) cp.getConstant((int) index);
	Class cl = clRef.class_();
	
	if(!ref.isInstanceOf(cl)){
	    throw new Error("java.lang.ClassCastException");
	}
    }

}
