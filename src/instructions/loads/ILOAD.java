package instructions.loads;

import instructions.base.Index8Instruction;
import rtda.Frame;

public class ILOAD extends Index8Instruction {

    public void execute(Frame frame) {
        int val = frame.localVars().getInt((int) index);
        frame.operandStack().pushInt(val);
    }
}
