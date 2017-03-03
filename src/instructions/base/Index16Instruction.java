package instructions.base;

public abstract class Index16Instruction implements Instruction {
    long index;

    public void fetchOperands(BytecodeReader reader) {
	index = reader.readU2();
    }
}
