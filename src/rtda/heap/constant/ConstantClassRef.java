package rtda.heap.constant;

import classfile.RawConstantPool;
import classfile.constant.ConstantClassInfo;
import rtda.heap.Class;
import rtda.heap.ClassLoader;

public class ConstantClassRef implements Constant {
    ClassLoader loader;
    String className;
    Class cl;

    public ConstantClassRef(ClassLoader loader, RawConstantPool rcp, ConstantClassInfo cli) {
        this.className = rcp.getUtf8(cli.nameIndex);
        this.loader = loader;
    }

    public Class class_() {
        if (cl == null) {
            this.cl = loader.loadClass(className);
        }
        return cl;
    }

}
