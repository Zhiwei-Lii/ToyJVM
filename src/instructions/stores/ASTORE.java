package instructions.stores;

import instructions.base.Index8Instruction;
import rtda.Frame;
import rtda.Object;

public class ASTORE extends Index8Instruction {

    public void execute(Frame frame) {
	Object ref = frame.operandStack().popRef();
	frame.localVars().setRef((int)index, ref);
    }

}
