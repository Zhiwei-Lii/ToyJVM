package instructions.references;

import instructions.base.Index16Instruction;
import rtda.Frame;
import rtda.heap.Class;
import rtda.heap.ConstantPool;
import rtda.heap.constant.ConstantMethodRef;
import rtda.heap.Method;
import rtda.heap.Object;
import rtda.Thread;
import rtda.Slot;

public class INVOKE_SPECIAL extends Index16Instruction {

    public void execute(Frame frame) {
        Class currentClass = frame.method().class_();
        ConstantPool cp = currentClass.constantPool();
        ConstantMethodRef methodRef = (ConstantMethodRef) cp.getConstant((int) index);

        Class resolvedClass = methodRef.class_();
        Method resolvedMethod = methodRef.method();

        Object ref = frame.operandStack().getRefFromTop(resolvedMethod.argSlotCount() - 1);

        if (ref == null) {
            throw new Error("java.lang.NullPointerException");
        }

        Method methodToBeInvoked = resolvedMethod;

        if (currentClass.isSuper() && resolvedClass.isSuperClassOf(currentClass)
                && !resolvedMethod.name().equals("<init>")) {
            Class superClass = currentClass.superClass();
            methodToBeInvoked =
                    superClass.lookupMethodInClass(methodRef.methodName(), methodRef.descriptor());
        }

        invokeMethod(frame, methodToBeInvoked);
    }

    private void invokeMethod(Frame invokerFrame, Method method) {
        Thread thread = invokerFrame.thread();
        Frame newFrame = new Frame(thread, method);
        thread.pushFrame(newFrame);

        int argSlotCount = method.argSlotCount();

        if (argSlotCount > 0) {
            for (int i = argSlotCount - 1; i >= 0; i--) {
                Slot slot = invokerFrame.operandStack().popSlot();
                newFrame.localVars().setSlot(i, slot);
            }
        }
    }

}
