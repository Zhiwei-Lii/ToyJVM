package instructions.references;

import instructions.base.Index16Instruction;
import rtda.Frame;
import rtda.Slot;
import rtda.Thread;
import rtda.heap.ConstantPool;
import rtda.heap.Method;
import rtda.heap.Class;
import rtda.heap.constant.ConstantMethodRef;

public class INVOKE_STATIC extends Index16Instruction {

    public void execute(Frame frame) {
        ConstantPool cp = frame.method().class_().constantPool();
        ConstantMethodRef methodRef = (ConstantMethodRef) cp.getConstant((int) index);

        Class cl = methodRef.class_();
        if (!cl.initStarted()) {
            frame.unrollPc();
            initClass(frame.thread(), cl);
            return;
        }

        Method method = methodRef.method();
        invokeMethod(frame, method);
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

        if (method.isNative()) {
            if (method.name().equals("registerNatives")) {
                thread.popFrame();
            }
            else {
                throw new Error("Unsupported");
            }
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
