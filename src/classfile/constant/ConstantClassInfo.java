package classfile.constant;

import classfile.ClassReader;

// test
public class ConstantClassInfo extends ConstantInfo {
    public long nameIndex;

    public ConstantClassInfo(ClassReader reader) {
	nameIndex = reader.readU2();
    }

}
