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

public class INVOKE_VIRTUAL extends Index16Instruction {

    public void execute(Frame frame) {
	Class currentClass = frame.method().class_();
	ConstantPool cp = currentClass.constantPool();
	ConstantMethodRef methodRef = (ConstantMethodRef) cp.getConstant((int) index);

	Method resolvedMethod = methodRef.method();

	Object ref = frame.operandStack().getRefFromTop(resolvedMethod.argSlotCount() - 1);

	if (ref == null) {
	    // hack println, System.out没有被初始化, 初值是null
	    if (methodRef.methodName().equals("println")) {
		String descriptor = methodRef.descriptor();
		if (descriptor.equals("(I)V")) {
		    System.out.println(frame.operandStack().popInt());
		}

		frame.operandStack().popRef();
		return;
	    }

	    throw new Error("java.lang.NullPointerException");
	}

	Method methodToBeInvoked = ref.class_().lookupMethodInClass(methodRef.methodName(), methodRef.descriptor());

	if (methodToBeInvoked == null) {
	    throw new Error("java.lang.AbstractMethodError");
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
