package rtda.heap;

import java.util.HashMap;
import java.util.Map;
import classfile.ClassFile;
import classpath.ClassPath;
import rtda.Slot;
import rtda.heap.constant.*;

public class ClassLoader {
    ClassPath classPath;
    Map<String, Class> classMap;

    public ClassLoader(ClassPath classPath) {
	this.classPath = classPath;
	this.classMap = new HashMap<String, Class>();
    }

    public Class loadClass(String name) {
	if (classMap.containsKey(name)) {
	    return classMap.get(name);
	}

	return loadNonArrayClass(name);
    }

    private Class loadNonArrayClass(String name) {
	byte[] data = classPath.readClass(name);
	Class cl = defineClass(data);
	link(cl);
	return cl;
    }

    private Class defineClass(byte[] data) {
	ClassFile cf = new ClassFile(data);
	Class cl = new Class(cf);
	cl.loader = this;
	resolveSuperClass(cl);
	resolveInterfaces(cl);
	classMap.put(cl.name, cl);
	return cl;
    }

    private void resolveInterfaces(Class cl) {
	for (int i = 0; i < cl.interfaces.length; i++) {
	    cl.interfaces[i] = cl.loader.loadClass(cl.interfaceNames[i]);
	}
    }

    private void resolveSuperClass(Class cl) {
	if (!cl.name.equals("java/lang/Object")) {
	    cl.superClass = cl.loader.loadClass(cl.superClassName);
	}
    }

    private void link(Class cl) {
	verify(cl);
	prepare(cl);
    }

    private void verify(Class cl) {

    }

    private void prepare(Class cl) {
	calcInstanceFieldSlotIds(cl);
	calcStaticFieldsSlotIds(cl);
	allocAndInitStaticVars(cl);
    }

    private void calcInstanceFieldSlotIds(Class cl) {
	int slotId = 0;
	if (cl.superClass != null) {
	    slotId = cl.superClass.instanceSlotCount;
	}

	for (Field f : cl.fields) {
	    if (!f.isStatic()) {
		f.slotId = slotId++;
	    }
	}

	cl.instanceSlotCount = slotId;
    }

    private void calcStaticFieldsSlotIds(Class cl) {
	int slotId = 0;

	for (Field f : cl.fields) {
	    if (f.isStatic()) {
		f.slotId = slotId++;
	    }
	}

	cl.staticSlotCount = slotId;
    }

    private void allocAndInitStaticVars(Class cl) {
	cl.staticVars = new Slot[cl.staticSlotCount];
	for (Field f : cl.fields) {

	    if (f.isStatic() && f.isFinal()) {
		initStaticFinalVar(cl, f);
	    }
	}
    }

    private void initStaticFinalVar(Class cl, Field f) {
	Slot[] vars = cl.staticVars;
	ConstantPool cp = cl.constantPool;
	int cpIndex = f.constantValueIndex;
	int slotId = f.slotId;

	if (cpIndex > 0) {
	    String des = f.descriptor;
	    if (des.equals("I")) {
		int val = ((ConstantInteger) cp.getConstant(cpIndex)).val();
		vars[slotId].setNum(val);
	    } else if (des.equals("Ljava/lang/String;")) {
		// to do
		throw new Error("This is String");
	    } else {
		throw new Error("Unsupported type");
	    }
	}
    }
}
