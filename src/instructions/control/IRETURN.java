package instructions.control;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;
import rtda.Thread;

public class IRETURN extends NoOperandsInstruction {

    public void execute(Frame frame) {
        Thread thread = frame.thread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.topFrame();
        int retVal = currentFrame.operandStack().popInt();
        invokerFrame.operandStack().pushInt(retVal);
    }
}
