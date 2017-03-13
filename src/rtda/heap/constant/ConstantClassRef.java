package rtda.heap.constant;

import classfile.RawConstantPool;
import classfile.constant.ConstantClassInfo;
import rtda.heap.Class;
import rtda.heap.ClassLoader;

public class ConstantClassRef implements Constant {
    String className;
    Class cl;

    public ConstantClassRef(ClassLoader loader, RawConstantPool rcp, ConstantClassInfo cli) {
	this.className = rcp.getUtf8(cli.nameIndex);
	this.cl = loader.loadClass(className);
    }

    public Class class_() {
	return cl;
    }

}
