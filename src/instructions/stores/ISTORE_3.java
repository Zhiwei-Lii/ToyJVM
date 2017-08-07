package instructions.stores;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;

public class ISTORE_3 extends NoOperandsInstruction {

    public void execute(Frame frame) {
        int val = frame.operandStack().popInt();
        frame.localVars().setInt(3, val);
    }
}
