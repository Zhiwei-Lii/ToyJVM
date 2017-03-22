package instructions.references;

import instructions.base.BytecodeReader;
import instructions.base.Instruction;
import rtda.Frame;
import rtda.heap.Class;
import rtda.heap.ConstantPool;
import rtda.heap.constant.ConstantInterfaceMethodRef;
import rtda.heap.Method;
import rtda.heap.Object;
import rtda.Thread;
import rtda.Slot;

public class INVOKE_INTERFACE implements Instruction {
    long index;

    public void fetchOperands(BytecodeReader reader) {
	index = reader.readU2();
	reader.readU1();
	reader.readU1();
    }

    public void execute(Frame frame) {
	Class currentClass = frame.method().class_();
	ConstantPool cp = currentClass.constantPool();
	ConstantInterfaceMethodRef methodRef = (ConstantInterfaceMethodRef) cp.getConstant((int) index);

	Method resolvedMethod = methodRef.method();

	Object ref = frame.operandStack().getRefFromTop(resolvedMethod.argSlotCount() - 1);

	if (ref == null) {
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
	
	/*
	if(method.isNative()){
            if(method.name().equals("registerNatives")){
                thread.popFrame();
            }
            else{
                throw new Error("Unsupported");
            }
	}
	*/
    }

}
