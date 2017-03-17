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

	ConstantClassInfo classInfo = (ConstantClassInfo) rcp.getConstantInfo(mri.classIndex());
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

    /* 对应cp_methodref.go里的ResolvedMethod() */
    public Method method() {
	if (method == null) {
	    resolveMethodRef();
	}
	return method;
    }

    private void resolveMethodRef() {
	Class cl = loader.loadClass(className);

	if (cl.isInterface()) {
	    throw new Error("java.lang.IncompatibleClassChangeError");
	}

	this.method = lookupMethod(cl, methodName, descriptor);

	if (this.method == null) {
	    throw new Error("java.lang.NoSuchMethodError");
	}
    }

    private Method lookupMethod(Class cl, String methodName, String descriptor) {
	Method method = lookupMethodInClass(cl, methodName, descriptor);

	if (method == null) {
	    method = lookupMethodInInterfaces(cl.interfaces(), methodName, descriptor);
	}
	return method;
    }

    private Method lookupMethodInClass(Class cl, String methodName, String descriptor) {
	for (Method m : cl.methods()) {
	    if (m.name().equals(methodName) && m.descriptor().equals(descriptor)) {
		return m;
	    }
	}

	if (cl.superClass() != null) {
	    return lookupMethodInClass(cl.superClass(), methodName, descriptor);
	}

	return null;
    }

    private Method lookupMethodInInterfaces(Class[] interfaces, String methodName, String descriptor) {
	for (Class iface : interfaces) {
	    for (Method m : iface.methods()) {
		if (m.name().equals(methodName) && m.descriptor().equals(descriptor)) {
		    return m;
		}
	    }

	    Method m = lookupMethodInInterfaces(iface.interfaces(), methodName, descriptor);
	    if (m != null) {
		return m;
	    }
	}

	return null;
    }
}
