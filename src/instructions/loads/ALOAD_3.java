package instructions.loads;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;
import rtda.heap.Object;

public class ALOAD_3 extends NoOperandsInstruction {
    public void execute(Frame frame) {
	Object ref = frame.localVars().getRef(3);
	frame.operandStack().pushRef(ref);
    }
}
