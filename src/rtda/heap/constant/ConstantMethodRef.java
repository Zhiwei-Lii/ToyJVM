package rtda.heap.constant;

import rtda.heap.Method;
import classfile.RawConstantPool;
import classfile.constant.ConstantClassInfo;
import classfile.constant.ConstantMethodrefInfo;
import classfile.constant.ConstantNameAndTypeInfo;
import rtda.heap.ClassLoader;
import rtda.heap.Class;

public class ConstantMethodRef implements Constant {
    ClassLoader loader;
    String className;
    Class cl;
    String methodName;
    String descriptor;
    Method method;

    public ConstantMethodRef(ClassLoader loader, RawConstantPool rcp, ConstantMethodrefInfo mri) {
	this.loader = loader;

	ConstantClassInfo classInfo = (ConstantClassInfo)rcp.getConstantInfo(mri.classIndex());
	this.className = rcp.getUtf8(classInfo.nameIndex);

	ConstantNameAndTypeInfo cnati = (ConstantNameAndTypeInfo) rcp.getConstantInfo(mri.nameAndTypeIndex());
	this.methodName = rcp.getUtf8(cnati.nameIndex());
	this.descriptor = rcp.getUtf8(cnati.descriptorIndex());
    }

    public String methodName() {
	return methodName;
    }

    public String descriptor() {
	return descriptor;
    }

    public Method method() {
	if(method==null){
            this.cl = loader.loadClass(className);
            this.method = lookupMethod(cl);
	}
	return method;
    }

    private Method lookupMethod(Class class_) {
	for (Method m : class_.methods()) {
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
