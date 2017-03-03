package instructions.loads;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;

public class ILOAD_0 extends NoOperandsInstruction {
    public void execute(Frame frame) {
	int val = frame.localVars().getInt(0);
	frame.operandStack().pushInt(val);
    }
}
