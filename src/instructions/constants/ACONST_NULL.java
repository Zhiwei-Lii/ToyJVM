package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;

public class ACONST_NULL extends NoOperandsInstruction {

    public void execute(Frame frame) {
	frame.operandStack().pushRef(null);
    }
}
