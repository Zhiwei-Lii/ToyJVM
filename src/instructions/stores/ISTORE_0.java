package instructions.stores;

import instructions.base.NoOperandsInstruction;
import rtda.Frame;

public class ISTORE_0 extends NoOperandsInstruction {

    public void execute(Frame frame) {
	int val = frame.operandStack().popInt();
	frame.localVars().setInt(0, val);
    }    

}
