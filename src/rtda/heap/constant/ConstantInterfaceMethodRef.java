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
    
    public ConstantInterfaceMethodRef(ClassLoader loader, RawConstantPool rcp, ConstantInterfaceMethodrefInfo fimri){
	this.loader = loader;
	
	ConstantClassInfo cci = (ConstantClassInfo) rcp.getConstantInfo(fimri.classIndex());
	this.className = rcp.getUtf8(cci.nameIndex);

	ConstantNameAndTypeInfo cnati = (ConstantNameAndTypeInfo) rcp.getConstantInfo(fimri.nameAndTypeIndex());
	this.methodName = rcp.getUtf8(cnati.nameIndex());
	this.descriptor = rcp.getUtf8(cnati.descriptorIndex());
    }

    private Method lookupMethod(Class class_) {
	// to do
	if(method==null){
            this.cl = loader.loadClass(className);
            this.method = lookupMethod(cl);
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
	}

	return method;
    }
}
