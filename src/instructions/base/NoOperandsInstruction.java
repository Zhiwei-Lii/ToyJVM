package instructions.base;

public abstract class NoOperandsInstruction implements Instruction {
    public void fetchOperands(BytecodeReader reader) {
	// do nothing
    }
}
