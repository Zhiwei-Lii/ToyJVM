package instructions.control;

import instructions.base.BranchInstruction;
import instructions.base.BytecodeReader;
import rtda.Frame;

public class TABLE_SWITCH extends BranchInstruction {
    long defaultOffset;
    long low;
    long high;
    long[] jumpOffsets;

    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.readU4();

        low = reader.readU4();
        high = reader.readU4();

        int jumpOffsetsCount = (int) (high - low + 1);
        jumpOffsets = new long[jumpOffsetsCount];

        for (int i = 0; i < jumpOffsetsCount; i++) {
            jumpOffsets[i] = reader.readU4();
        }
    }

    public void execute(Frame frame) {
        int index = frame.operandStack().popInt();
        if (index >= low && index <= high) {
            offset = (short) jumpOffsets[(int) (index - low)];
        }
        else {
            offset = (short) defaultOffset;
        }
        branch(frame);
    }

}
