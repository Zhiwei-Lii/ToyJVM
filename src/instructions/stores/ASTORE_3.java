package instructions.stores;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;
import rtda.Object;

public class ASTORE_3 extends NoOperandsInstruction {
    public void execute(Frame frame) {
	Object ref = frame.operandStack().popRef();
	frame.localVars().setRef(3, ref);
    }

}
