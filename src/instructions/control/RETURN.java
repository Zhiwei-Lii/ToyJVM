package instructions.control;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;

public class RETURN extends NoOperandsInstruction {

    public void execute(Frame frame) {
        frame.thread().popFrame();
    }

}
