package instructions.references;

import instructions.base.Index16Instruction;
import rtda.Frame;
import rtda.OperandStack;
import rtda.heap.ConstantPool;
import rtda.heap.Object;
import rtda.heap.constant.ConstantClassRef;

public class INSTANCE_OF extends Index16Instruction {

    public void execute(Frame frame) {
        OperandStack stack = frame.operandStack();
        Object ref = stack.popRef();

        if (ref == null) {
            stack.pushInt(0);
            return;
        }

        ConstantPool cp = frame.method().class_().constantPool();
        ConstantClassRef cl = (ConstantClassRef) cp.getConstant((int) index);

        if (ref.isInstanceOf(cl.class_())) {
            stack.pushInt(1);
        }
        else {
            stack.pushInt(0);
        }
    }

}
