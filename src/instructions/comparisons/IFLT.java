package instructions.comparisons;

import instructions.base.BranchInstruction;
import rtda.Frame;

public class IFLT extends BranchInstruction {

    public void execute(Frame frame) {
        int val = frame.operandStack().popInt();

        if (val < 0) {
            branch(frame);
        }
    }

}
