package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;

public class ICONST_4 extends NoOperandsInstruction {

    public void execute(Frame frame) {
	frame.operandStack().pushInt(4);
    }

}
