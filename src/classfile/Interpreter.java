package classfile;

import rtda.Thread;
import rtda.heap.Method;
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

        loop(thread, method.code());

    }

    private static void loop(Thread thread, byte[] code) {
        BytecodeReader reader = new BytecodeReader(code);
        Frame frame = thread.popFrame();

        while (true) {
            int pc = frame.pc();
            thread.setPc(pc);

            reader.reset(code, pc);
            long opcode = reader.readU1();
            Instruction inst = InstructionFactory.newInstruction(opcode);
            inst.fetchOperands(reader);
            frame.setPc(reader.pc());

            inst.execute(frame);

            /*
             * System.out.println(); System.out.println(); System.out.println("LocalVars:");
             * LocalVars locals = frame.localVars(); for(int i=0; i<3;i++){
             * System.out.println(locals.getInt(i)); } System.out.println();
             * System.out.println("OperandStack:"); OperandStack stack = frame.operandStack();
             * stack.print();
             */
        }

    }

}
