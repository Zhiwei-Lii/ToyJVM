package instructions.comparisons;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;

public class ICMP extends NoOperandsInstruction {

    public void execute(Frame frame) {
	int v2 = frame.operandStack().popInt();
	int v1 = frame.operandStack().popInt();

	if (v1 > v2) {
	    frame.operandStack().pushInt(1);
	} else if (v1 == v2) {
	    frame.operandStack().pushInt(0);
	} else {
	    frame.operandStack().pushInt(-1);
	}
    }

}
