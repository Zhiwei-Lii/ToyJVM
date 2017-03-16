package instructions.references;

import instructions.base.Index16Instruction;
import rtda.Frame;
import rtda.OperandStack;
import rtda.heap.ConstantPool;
import rtda.heap.Field;
import rtda.heap.constant.ConstantFieldRef;
import rtda.heap.Object;

public class PUT_FIELD extends Index16Instruction {

    public void execute(Frame frame) {
	ConstantPool cp = frame.method().class_().constantPool();
	ConstantFieldRef fieldRef = (ConstantFieldRef) cp.getConstant((int) index);

	Field field = fieldRef.field();

	String descriptor = field.descriptor();
	int slotId = field.slotId();

	OperandStack stack = frame.operandStack();

	if (descriptor.contains("I")) {
	    int val = frame.operandStack().popInt();
	    Object ref = stack.popRef();
	    ref.setFieldInt(slotId, val);
	} else if (descriptor.contains("L")) {
	    Object operand = stack.popRef();
	    Object ref = stack.popRef();

	    if (ref == null) {
		throw new Error("NullPointerException");
	    }

	    ref.setFieldRef(slotId, operand);
	} else {
	    throw new Error("Unsupport");
	}
    }
}
