package rtda.heap.constant;

import rtda.heap.Method;
import classfile.RawConstantPool;
import classfile.constant.ConstantClassInfo;
import classfile.constant.ConstantInterfaceMethodrefInfo;
import classfile.constant.ConstantNameAndTypeInfo;
import rtda.heap.ClassLoader;
import rtda.heap.Class;

public class ConstantInterfaceMethodRef implements Constant {
    ClassLoader loader;
    String className;
    Class cl;
    String methodName;
    String descriptor;
    Method method;

    public ConstantInterfaceMethodRef(ClassLoader loader, RawConstantPool rcp, ConstantInterfaceMethodrefInfo fimri) {
	this.loader = loader;

	ConstantClassInfo cci = (ConstantClassInfo) rcp.getConstantInfo(fimri.classIndex());
	this.className = rcp.getUtf8(cci.nameIndex);

	ConstantNameAndTypeInfo cnati = (ConstantNameAndTypeInfo) rcp.getConstantInfo(fimri.nameAndTypeIndex());
	this.methodName = rcp.getUtf8(cnati.nameIndex());
	this.descriptor = rcp.getUtf8(cnati.descriptorIndex());
    }

    public Method method() {
	if (this.method == null) {
	    resolveInterfaceMethodRef();
	}

	return this.method;
    }

    public String methodName() {
	return methodName;
    }

    public String descriptor() {
	return descriptor;
    }

    private void resolveInterfaceMethodRef() {
	Class cl = this.loader.loadClass(className);

	if (!cl.isInterface()) {
	    throw new Error("java.lang.IncompatibleClassChangeError");
	}

	Method method = lookupInterfaceMethod(cl, this.methodName, this.descriptor);
	if (method == null) {
	    throw new Error("java.lang.NoSuchMethodError");
	}

	this.method = method;
    }

    private Method lookupInterfaceMethod(Class iface, String methodName, String descriptor) {
	for (Method m : iface.methods()) {
	    if (m.name().equals(methodName) && m.descriptor().equals(descriptor)) {
		return m;
	    }
	}

	return lookupMethodInInterfaces(iface.interfaces(), methodName, descriptor);
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
