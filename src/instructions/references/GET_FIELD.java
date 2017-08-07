package instructions.references;

import instructions.base.Index16Instruction;
import rtda.Frame;
import rtda.OperandStack;
import rtda.heap.ConstantPool;
import rtda.heap.Field;
import rtda.heap.constant.ConstantFieldRef;
import rtda.heap.Object;

public class GET_FIELD extends Index16Instruction {

    public void execute(Frame frame) {
        ConstantPool cp = frame.method().class_().constantPool();
        ConstantFieldRef fieldRef = (ConstantFieldRef) cp.getConstant((int) index);

        Field field = fieldRef.field();

        String descriptor = field.descriptor();
        int slotId = field.slotId();

        OperandStack stack = frame.operandStack();

        if (descriptor.contains("I")) {
            Object ref = frame.operandStack().popRef();
            stack.pushInt(ref.getFieldInt(slotId));
        }
        else if (descriptor.contains("L")) {
            Object ref = frame.operandStack().popRef();

            if (ref == null) {
                throw new Error("NullPointerException");
            }

            stack.pushRef(ref.getFieldRef(slotId));
        }
        else {
            throw new Error("Unsupport");
        }
    }

}
