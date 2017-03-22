package instructions.math;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;

public class ISUB extends NoOperandsInstruction {

    public void execute(Frame frame) {
	int v2 = frame.operandStack().popInt();
	int v1 = frame.operandStack().popInt();
	frame.operandStack().pushInt(v1 - v2);
    }

}
