package classfile;
import rtda.Thread;
import instructions.base.BytecodeReader;
import instructions.base.Instruction;
import instructions.base.InstructionFactory;
import rtda.Frame;
import rtda.LocalVars;
import rtda.OperandStack;
import rtda.Slot;

public class Interpreter {
    public static void interpret(MemberInfo methodInfo){
	CodeAttribute codeAttr = methodInfo.codeAttribute();
	int maxLocals = (int) codeAttr.maxLocals;
	int maxStack = (int)codeAttr.maxStack;
	
	byte[] code = new byte[methodInfo.codeAttribute().code.length];
	for(int i=0; i<code.length; i++){
	    code[i] = (byte)methodInfo.codeAttribute().code[i];
	}
	
	Thread thread = new Thread();
	Frame frame = new Frame(thread, maxLocals, maxStack);
	thread.pushFrame(frame);
	
	loop(thread, code);
	
    }
    
    private static void loop(Thread thread, byte[] code){
	BytecodeReader reader = new BytecodeReader(code);
	Frame frame = thread.popFrame();
	
	while(true){
	    int pc = frame.pc();

	    reader.reset(code, pc);
	    long opcode = reader.readU1();
	    Instruction inst = InstructionFactory.newInstruction(opcode);
	    inst.fetchOperands(reader);
	    frame.setPc(reader.pc());
	    
	    System.out.println();
	    System.out.println();
	    System.out.println("LocalVars:");
	    LocalVars locals = frame.localVars();
	    for(int i=0; i<3;i++){
		System.out.println(locals.getInt(i));
	    }
	    System.out.println();
	    System.out.println("OperandStack:");
	    OperandStack stack = frame.operandStack();
	    stack.print();

	    inst.execute(frame);
	}
	
    }

}
