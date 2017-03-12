package rtda.heap.constant;

import classfile.RawConstantPool;
import classfile.constant.ConstantFieldrefInfo;
import classfile.constant.ConstantNameAndTypeInfo;
import rtda.heap.Field;
import rtda.heap.ClassLoader;
import rtda.heap.Class;

public class ConstantFieldRef implements Constant {
    String className;
    Class cl;
    String fieldName;
    String descriptor;
    Field field;

    public ConstantFieldRef(ClassLoader loader, RawConstantPool rcp, ConstantFieldrefInfo cfri) {
	this.className = rcp.getUtf8(cfri.classIndex());
	this.cl = loader.loadClass(className);

	ConstantNameAndTypeInfo cnati = (ConstantNameAndTypeInfo) rcp.getConstantInfo(cfri.nameAndTypeIndex());
	this.fieldName = rcp.getUtf8(cnati.nameIndex());
	this.descriptor = rcp.getUtf8(cnati.descriptorIndex());

	this.field = lookupField(cl);
    }

    private Field lookupField(Class class_) {
	for (Field f : class_.fields()) {
	    if (f.name().equals(fieldName) && f.descriptor().equals(descriptor)) {
		return f;
	    }
	}

	for (Class inter : class_.interfaces()) {
	    Field f = lookupField(inter);

	    if (f != null) {
		return f;
	    }
	}

	if (class_.superClass() != null) {
	    return lookupField(class_.superClass());
	}

	return null;
    }

}
