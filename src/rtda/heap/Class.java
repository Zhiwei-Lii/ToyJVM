package rtda.heap;

import classfile.ClassFile;
import classfile.ConstantPool;

/**
 * This class is the refinement of ClassFile
 * 
 * @author misen
 *
 */

public class Class {
    int accessFlags;
    String name;
    String superClassName;
    String[] interfaceNames;
    ConstantPool constantPool;
    Field[] fields;
    Methdod[] methods;
    rtda.heap.ClassLoader loader;
    Class superClass;
    Class[] interfaces;
    int instanceSlotCount;
    int staticSlotCount;
    Slots staticVars;

    public Class(ClassFile cf) {
	accessFlags = cf.accessFlags();
	name = cf.thisClassName();
	superClassName = cf.superClassName();
	interfaceNames = cf.interfaceNames();
	constantPool = newConstantPool(cf);
	fields = newFields(cf);
	methods = newMethods(cf);
    }

    public boolean isPublic() {
	return 0 != (accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isFinal() {
	return 0 != (accessFlags & AccessFlags.ACC_FINAL);
    }

    public boolean isSuper() {
	return 0 != (accessFlags & AccessFlags.ACC_SUPER);
    }

    public boolean isInterface() {
	return 0 != (accessFlags & AccessFlags.ACC_INTERFACE);
    }

    public boolean isAbstract() {
	return 0 != (accessFlags & AccessFlags.ACC_ABSTRACT);
    }

    public boolean isSynthetic() {
	return 0 != (accessFlags & AccessFlags.ACC_SYNTHETIC);
    }

    public boolean isAnnotation() {
	return 0 != (accessFlags & AccessFlags.ACC_ANNOTATION);
    }

    public boolean isEnum() {
	return 0 != (accessFlags & AccessFlags.ACC_ENUM);
    }

    private Field[] newFields(ClassFile cf) {
	Field[] fs = new Field[cf.fields().length];
	for (int i = 0; i < fs.length; i++) {
	    fs[i] = new Field(this, cf.fields()[i]);
	}
	return fs;
    }
    
    private Method[] newMethods(ClassFile cf){
	Method[] ms = new Method[cf.methods().length];
	for (int i = 0; i < ms.length; i++) {
	    ms[i] = new Method(this, cf.methods()[i]);
	}
	return ms;
    }

}
