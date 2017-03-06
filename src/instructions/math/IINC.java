package instructions.math;

import instructions.base.BytecodeReader;
import instructions.base.Instruction;
import rtda.Frame;

public class IINC implements Instruction {
    int index;
    int constant;

    public void fetchOperands(BytecodeReader reader) {
	index = (int) reader.readU1();
	constant = (int) reader.readU1();
    }

    public void execute(Frame frame) {
	int val = frame.localVars().getInt(index);
	frame.localVars().setInt(index, val + constant);
    }
}
