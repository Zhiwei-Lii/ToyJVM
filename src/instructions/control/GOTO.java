package instructions.control;

import instructions.base.BranchInstruction;
import rtda.Frame;

public class GOTO extends BranchInstruction {

    public void execute(Frame frame) {
	branch(frame);
    }

}
