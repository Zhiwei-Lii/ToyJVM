package instructions.references;

import instructions.base.Index16Instruction;
import rtda.Frame;
import rtda.OperandStack;
import rtda.heap.Class;
import rtda.heap.ConstantPool;
import rtda.heap.Field;
import rtda.heap.Method;
import rtda.heap.constant.ConstantFieldRef;
import rtda.Thread;

public class GET_STATIC extends Index16Instruction {

    public void execute(Frame frame) {
        ConstantPool cp = frame.method().class_().constantPool();
        ConstantFieldRef fieldRef = (ConstantFieldRef) cp.getConstant((int) index);

        Field field = fieldRef.field();
        Class cl = fieldRef.class_();

        if (!cl.initStarted()) {
            frame.unrollPc();
            initClass(frame.thread(), cl);
            return;
        }

        String descriptor = field.descriptor();
        int slotId = field.slotId();

        OperandStack stack = frame.operandStack();

        if (descriptor.contains("I")) {
            stack.pushInt((int) cl.staticVars()[slotId].num());
        }
        else if (descriptor.contains("L")) {
            stack.pushRef(cl.staticVars()[slotId].ref());
        }
        else {
            throw new Error("Unsupport");
        }

    }

    private void initClass(Thread thread, Class cl) {
        cl.startInit();
        scheduleClinit(thread, cl);
        initSuperClass(thread, cl);
    }

    private void scheduleClinit(Thread thread, Class cl) {
        Method clinit = cl.getClinitMethod();
        if (clinit != null) {
            Frame newFrame = new Frame(thread, clinit);
            thread.pushFrame(newFrame);
        }
    }

    private void initSuperClass(Thread thread, Class cl) {
        if (!cl.isInterface()) {
            Class superClass = cl.superClass();
            if (superClass != null && !superClass.initStarted()) {
                initClass(thread, superClass);
            }
        }
    }
}
