package instructions.control;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;
import rtda.Thread;
import rtda.heap.Object;

public class LRETURN extends NoOperandsInstruction {

    public void execute(Frame frame) {
        Thread thread = frame.thread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.topFrame();
        Object retRef = currentFrame.operandStack().popRef();
        invokerFrame.operandStack().pushRef(retRef);
    }

}
