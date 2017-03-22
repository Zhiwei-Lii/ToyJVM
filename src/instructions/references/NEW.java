package instructions.references;

import instructions.base.Index16Instruction;
import rtda.Frame;
import rtda.heap.ConstantPool;
import rtda.heap.Method;
import rtda.heap.constant.ConstantClassRef;
import rtda.heap.Class;
import rtda.heap.Object;
import rtda.Thread;

public class NEW extends Index16Instruction {

    public void execute(Frame frame) {
	ConstantPool cp = frame.method().class_().constantPool();
	ConstantClassRef classRef = (ConstantClassRef) cp.getConstant((int) index);
	Class cl = classRef.class_();

	if (!cl.initStarted()) {
	    frame.unrollPc();
	    initClass(frame.thread(), cl);
	    return;
	}

	Object ref = cl.newObject();
	frame.operandStack().pushRef(ref);
    }

    private void initClass(Thread thread, Class cl) {
	cl.startInit();
	scheduleClinit(thread, cl);
	initSuperClass(thread, cl);
    }

    private void scheduleClinit(Thread thread, Class cl) {
	Method clinit = cl.getClinitMethod();
	if(clinit!=null){
	    Frame newFrame = new Frame(thread, clinit);
	    thread.pushFrame(newFrame);
	}
    }

    private void initSuperClass(Thread thread, Class cl) {
	if(!cl.isInterface()){
	    Class superClass = cl.superClass();
	    if(superClass!=null&&!superClass.initStarted()){
		initClass(thread, superClass);
	    }
	}
    }
}
