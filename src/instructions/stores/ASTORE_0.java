package instructions.stores;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;
import rtda.heap.Object;

public class ASTORE_0 extends NoOperandsInstruction {

    public void execute(Frame frame) {
	Object ref = frame.operandStack().popRef();
	frame.localVars().setRef(0, ref);
    }
}
