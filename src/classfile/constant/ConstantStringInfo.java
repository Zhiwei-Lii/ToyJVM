package classfile.constant;

import classfile.ClassReader;

// test
public class ConstantStringInfo extends ConstantInfo {
    long strIndex;

    public ConstantStringInfo(ClassReader reader) {
        strIndex = reader.readU2();
    }

    public int index() {
        return (int) strIndex;
    }
}
