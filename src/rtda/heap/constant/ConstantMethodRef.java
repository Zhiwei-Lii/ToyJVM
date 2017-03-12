package rtda.heap.constant;

import rtda.heap.Method;
import classfile.RawConstantPool;
import classfile.constant.ConstantMethodrefInfo;
import classfile.constant.ConstantNameAndTypeInfo;
import rtda.heap.ClassLoader;
import rtda.heap.Class;

public class ConstantMethodRef implements Constant {
    String className;
    Class cl;
    String methodName;
    String descriptor;
    Method method;

    public ConstantMethodRef(ClassLoader loader, RawConstantPool rcp, ConstantMethodrefInfo mri) {
	this.className = rcp.getUtf8(mri.classIndex());
	this.cl = loader.loadClass(className);

	ConstantNameAndTypeInfo cnati = (ConstantNameAndTypeInfo) rcp.getConstantInfo(mri.nameAndTypeIndex());
	this.methodName = rcp.getUtf8(cnati.nameIndex());
	this.descriptor = rcp.getUtf8(cnati.descriptorIndex());

	this.method = lookupMethod(cl);
    }

    private Method lookupMethod(Class class_) {
	for (Method m : class_.methods()){
	    if (m.name().equals(methodName) && m.descriptor().equals(descriptor)) {
		return m;
	    }
	}

	for (Class inter : class_.interfaces()) {
	    Method m = lookupMethod(inter);

	    if (m != null) {
		return m;
	    }
	}

	if (class_.superClass() != null) {
	    return lookupMethod(class_.superClass());
	}

	return null;
    }

}
