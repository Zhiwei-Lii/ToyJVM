import rtda.Thread;
import rtda.heap.Method;
import classfile.MemberInfo;
import classfile.attribute.CodeAttribute;
import instructions.base.BytecodeReader;
import instructions.base.Instruction;
import instructions.base.InstructionFactory;
import rtda.Frame;
import rtda.LocalVars;
import rtda.OperandStack;
import rtda.Slot;

public class Interpreter {

    public static void interpret(Method method) {
        Thread thread = new Thread();
        Frame frame = new Frame(thread, method);
        thread.pushFrame(frame);

        loop(thread);
    }

    private static void loop(Thread thread) {
        BytecodeReader reader = new BytecodeReader(thread.topFrame().method().code());

        while (true) {
            Frame frame = thread.topFrame();
            int pc = frame.pc();
            thread.setPc(pc);

            // decode
            if (frame.method().code() == null) {
                System.out.println(frame.method().name());
            }
            reader.reset(frame.method().code(), pc);
            long opcode = reader.readU1();
            Instruction inst = InstructionFactory.newInstruction(opcode);
            inst.fetchOperands(reader);
            frame.setPc(reader.pc());

            // execute
            inst.execute(frame);

            if (thread.isStackEmpty()) {
                break;
            }
        }
    }
}
