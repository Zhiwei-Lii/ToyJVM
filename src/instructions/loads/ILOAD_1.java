package instructions.loads;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;

public class ILOAD_1 extends NoOperandsInstruction {

    public void execute(Frame frame) {
        int val = frame.localVars().getInt(1);
        frame.operandStack().pushInt(val);
    }

}
