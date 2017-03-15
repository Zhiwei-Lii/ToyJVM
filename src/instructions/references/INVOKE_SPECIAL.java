package instructions.references;

import instructions.base.Index16Instruction;
import rtda.Frame;

public class INVOKE_SPECIAL extends Index16Instruction {

    // hack
    public void execute(Frame frame) {
	frame.operandStack().popRef();
    }

}
