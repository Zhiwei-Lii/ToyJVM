package instructions.stores;

import instructions.base.Index8Instruction;
import rtda.Frame;

public class ISTORE extends Index8Instruction {

    public void execute(Frame frame) {
	int val = frame.operandStack().popInt();
	frame.localVars().setInt((int)index, val);
    }

}
