package instructions.constants;

import instructions.base.Index8Instruction;
import rtda.Frame;
import rtda.OperandStack;
import rtda.heap.ConstantPool;
import rtda.heap.constant.Constant;
import rtda.heap.constant.ConstantClassRef;
import rtda.heap.constant.ConstantInteger;
import rtda.heap.constant.ConstantString;

public class LDC extends Index8Instruction {

    public void execute(Frame frame) {
	OperandStack stack = frame.operandStack();
	ConstantPool cp = frame.method().class_().constantPool();
	Constant c = cp.getConstant((int) index);

	if (c instanceof ConstantInteger) {
	    stack.pushInt(((ConstantInteger) c).val());
	} else if (c instanceof ConstantString) {

	} else if (c instanceof ConstantClassRef) {

	} else {
	    throw new Error("java.lang.ClassFormatError");
	}
    }

}
