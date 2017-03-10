package rtda.heap.constant;

import rtda.heap.ConstantPool;
import rtda.heap.Method;

public class ConstantMethodRef implements Constant {
    ConstantPool cp;
    String className;
    Class cl;
    String methodName;
    String descriptor;
    Method method;

    public ConstantMethodRef() {
	// to do

    }

}
