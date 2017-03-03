package instructions.base;

public abstract class BranchInstruction implements Instruction {
    short offset;

    public void fetchOperands(BytecodeReader reader) {
	offset = (short) reader.readU2();
    }
}
