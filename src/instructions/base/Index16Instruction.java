package instructions.base;

public abstract class Index16Instruction implements Instruction {
    protected long index;

    public void fetchOperands(BytecodeReader reader) {
        index = reader.readU2();
    }
}
