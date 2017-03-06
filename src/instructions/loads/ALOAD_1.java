package instructions.loads;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;
import rtda.Object;

public class ALOAD_1 extends NoOperandsInstruction {
    public void execute(Frame frame) {
	Object ref = frame.localVars().getRef(1);
	frame.operandStack().pushRef(ref);
    }
}
