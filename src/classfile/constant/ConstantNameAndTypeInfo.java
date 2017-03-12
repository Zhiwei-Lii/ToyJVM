package classfile.constant;

import classfile.ClassReader;

// test
public class ConstantNameAndTypeInfo extends ConstantInfo {
    long nameIndex;
    long descriptorIndex;

    public ConstantNameAndTypeInfo(ClassReader reader) {
	nameIndex = reader.readU2();
	descriptorIndex = reader.readU2();
    }

    public long nameIndex() {
	return nameIndex;
    }

    public long descriptorIndex() {
	return descriptorIndex;
    }
}
