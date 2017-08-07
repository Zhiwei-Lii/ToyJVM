package instructions.comparisons;

import instructions.base.BranchInstruction;
import rtda.Frame;
import rtda.heap.Object;

public class IF_ACMPNE extends BranchInstruction {

    public void execute(Frame frame) {
        Object ref2 = frame.operandStack().popRef();
        Object ref1 = frame.operandStack().popRef();

        if (ref1 != ref2) {
            branch(frame);
        }
    }

}
