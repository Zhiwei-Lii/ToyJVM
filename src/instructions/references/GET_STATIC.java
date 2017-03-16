package instructions.references;

import instructions.base.Index16Instruction;
import rtda.Frame;
import rtda.OperandStack;
import rtda.heap.Class;
import rtda.heap.ConstantPool;
import rtda.heap.Field;
import rtda.heap.constant.ConstantFieldRef;

public class GET_STATIC extends Index16Instruction {

    public void execute(Frame frame) {
	ConstantPool cp = frame.method().class_().constantPool();
	ConstantFieldRef fieldRef = (ConstantFieldRef) cp.getConstant((int) index);

	Field field = fieldRef.field();
	Class cl = fieldRef.class_();

	String descriptor = field.descriptor();
	int slotId = field.slotId();

	OperandStack stack = frame.operandStack();
	
	if (descriptor.contains("I")) {
	    stack.pushInt((int) cl.staticVars()[slotId].num());
	} else if (descriptor.contains("L")) {
	    stack.pushRef(cl.staticVars()[slotId].ref());
	} else {
	    throw new Error("Unsupport");
	}

    }

}
