package instructions.references;

import instructions.base.Index16Instruction;
import rtda.Frame;
import rtda.heap.ConstantPool;
import rtda.heap.constant.ConstantClassRef;
import rtda.heap.Class;
import rtda.heap.Object;

public class NEW extends Index16Instruction {

    public void execute(Frame frame) {
	ConstantPool cp = frame.method().class_().constantPool();
	ConstantClassRef classRef = (ConstantClassRef) cp.getConstant((int) index);
	Class cl = classRef.class_();

	Object ref = cl.newObject();
	frame.operandStack().pushRef(ref);
    }

}
