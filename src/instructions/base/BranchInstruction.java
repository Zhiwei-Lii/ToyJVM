package instructions.base;

import rtda.Frame;
import rtda.Thread;

public abstract class BranchInstruction implements Instruction {
    protected short offset;

    public void fetchOperands(BytecodeReader reader) {
        offset = (short) reader.readU2();
    }

    protected void branch(Frame frame) {
        int newPc = frame.thread().pc() + offset; // 这里的pc用的是跳转指令对应的位置
        frame.setPc(newPc);
    }
}
