package instructions.constants;

import instructions.base.BytecodeReader;
import instructions.base.Instruction;
import rtda.Frame;

public class SIPUSH implements Instruction {
    long val;

    public void fetchOperands(BytecodeReader reader) {
	val = reader.readU2();
    }

    public void execute(Frame frame) {
	frame.operandStack().pushInt((int) val);
    }

}
