package instructions.stack;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;

public class POP extends NoOperandsInstruction {

    public void execute(Frame frame) {
        frame.operandStack().popSlot();
    }

}
