package instructions.references;

import instructions.base.Index16Instruction;
import rtda.Frame;
import rtda.Slot;
import rtda.Thread;
import rtda.heap.ConstantPool;
import rtda.heap.Method;
import rtda.heap.constant.ConstantMethodRef;

public class INVOKE_STATIC extends Index16Instruction {

    public void execute(Frame frame) {
	ConstantPool cp = frame.method().class_().constantPool();
	ConstantMethodRef methodRef = (ConstantMethodRef) cp.getConstant((int) index);
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
    }

}
