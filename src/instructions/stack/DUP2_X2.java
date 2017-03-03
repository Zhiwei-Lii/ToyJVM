package instructions.stack;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;
import rtda.OperandStack;
import rtda.Slot;

public class DUP2_X2 extends NoOperandsInstruction {

    public void execute(Frame frame) {
	OperandStack stack = frame.operandStack();
	Slot slot1 = stack.popSlot();
	Slot slot2 = stack.popSlot();
	Slot slot3 = stack.popSlot();
	Slot slot4 = stack.popSlot();
	stack.pushSlot(slot2);
	stack.pushSlot(slot1);
	stack.pushSlot(slot4);
	stack.pushSlot(slot3);
	stack.pushSlot(slot2);
	stack.pushSlot(slot1);
    }

}
