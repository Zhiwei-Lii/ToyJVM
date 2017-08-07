package instructions.stores;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;

public class ISTORE_1 extends NoOperandsInstruction {
    public void execute(Frame frame) {
        int val = frame.operandStack().popInt();
        frame.localVars().setInt(1, val);
    }

}
