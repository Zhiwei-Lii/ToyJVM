package instructions.base;

import rtda.Frame;
import rtda.Thread;

public abstract class BranchInstruction implements Instruction {
    protected short offset;

    public void fetchOperands(BytecodeReader reader) {
	offset = (short) reader.readU2();
    }
    
    protected void branch(Frame frame){
	int newPc = frame.pc() + offset;
	frame.setPc(newPc);
    }
}
