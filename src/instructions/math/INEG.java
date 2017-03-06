package instructions.math;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;

public class INEG extends NoOperandsInstruction {

    public void execute(Frame frame) {
	int v1 = frame.operandStack().popInt();
	frame.operandStack().pushInt(-v1);
    }

}
