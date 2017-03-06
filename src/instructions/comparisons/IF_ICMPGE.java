package instructions.comparisons;

import instructions.base.BranchInstruction;
import rtda.Frame;

public class IF_ICMPGE extends BranchInstruction {

    public void execute(Frame frame) {
	int val2 = frame.operandStack().popInt();
	int val1 = frame.operandStack().popInt();

	if (val1 >= val2) {
	    branch(frame);
	}
    }

}
