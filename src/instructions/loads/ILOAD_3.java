package instructions.loads;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;

public class ILOAD_3 extends NoOperandsInstruction {

    public void execute(Frame frame) {
        int val = frame.localVars().getInt(3);
        frame.operandStack().pushInt(val);
    }

}
