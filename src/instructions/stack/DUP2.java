package instructions.stack;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;
import rtda.Slot;

public class DUP2 extends NoOperandsInstruction {

    public void execute(Frame frame) {
	Slot slot = frame.operandStack().popSlot();
	frame.operandStack().pushSlot(slot);
	frame.operandStack().pushSlot(slot);
	frame.operandStack().pushSlot(slot);
    }

}
