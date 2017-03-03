package instructions.base;

public abstract class Index8Instruction implements Instruction {
    long index;

    public void fetchOperands(BytecodeReader reader) {
	index = reader.readU1();
    }
}