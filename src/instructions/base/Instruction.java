package instructions.base;

import rtda.Frame;

public interface Instruction {
    public void fetchOperands(BytecodeReader reader);

    public void execute(Frame frame);

}
