package instructions.loads;

import instructions.base.Index8Instruction;
import rtda.Frame;
import rtda.Object;

public class ALOAD extends Index8Instruction {

    public void execute(Frame frame) {
	Object ref = frame.localVars().getRef((int) index);
	frame.operandStack().pushRef(ref);
    }

}
