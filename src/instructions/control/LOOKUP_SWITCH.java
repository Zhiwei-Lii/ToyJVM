package instructions.control;

import instructions.base.BranchInstruction;
import instructions.base.BytecodeReader;
import rtda.Frame;

public class LOOKUP_SWITCH extends BranchInstruction {
    long defaultOffset;
    long npairs;
    long[] matchOffsets;

    public void fetchOperands(BytecodeReader reader) {
	reader.skipPadding();
	defaultOffset = reader.readU4();
	npairs = reader.readU4();

	for (int i = 0; i < npairs * 2; i++) {
	    matchOffsets[i] = reader.readU4();
	}
    }

    public void execute(Frame frame) {
	int key = frame.operandStack().popInt();
	for (int i = 0; i < npairs * 2; i = i + 2) {
	    if (matchOffsets[i] == key) {
		offset = (short) matchOffsets[i + 1];
		break;
	    }
	}

	branch(frame);
    }
}
