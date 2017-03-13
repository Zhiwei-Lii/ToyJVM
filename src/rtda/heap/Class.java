package rtda.heap;

import classfile.ClassFile;
import rtda.Slot;
import rtda.heap.ClassLoader;
import rtda.heap.Object;

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
    Method[] methods;
    ClassLoader loader;
    Class superClass;
    Class[] interfaces;
    int instanceSlotCount;
    int staticSlotCount;
    Slot[] staticVars;

    public Class(ClassFile cf) {
	accessFlags = cf.accessFlags();
	name = cf.thisClassName();
	superClassName = cf.superClassName();
	interfaceNames = cf.interfaceNames();
	constantPool = new ConstantPool(this, cf.rawConstantPool());
	fields = newFields(cf);
	methods = newMethods(cf);
    }

    public Class[] interfaces() {
	return interfaces;
    }

    public Class superClass() {
	return superClass;
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

    public Field[] fields() {
	return fields;
    }

    public Method[] methods() {
	return methods;
    }
    
    public Slot[] staticVars(){
	return staticVars;
    }

    public ConstantPool constantPool() {
	return constantPool;
    }

    public Object newObject() {
	return new Object(this);
    }

    private Field[] newFields(ClassFile cf) {
	Field[] fs = new Field[cf.fields().length];
	for (int i = 0; i < fs.length; i++) {
	    fs[i] = new Field(this, cf.fields()[i]);
	}
	return fs;
    }

    private Method[] newMethods(ClassFile cf) {
	Method[] ms = new Method[cf.methods().length];
	for (int i = 0; i < ms.length; i++) {
	    ms[i] = new Method(this, cf.methods()[i]);
	}
	return ms;
    }

}
