package instructions.loads;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;
import rtda.Object;

public class ALOAD_2 extends NoOperandsInstruction {
    public void execute(Frame frame) {
	Object ref = frame.localVars().getRef(2);
	frame.operandStack().pushRef(ref);
    }
}
