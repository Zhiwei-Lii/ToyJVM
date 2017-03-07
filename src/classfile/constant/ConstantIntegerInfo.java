package classfile.constant;

import classfile.ClassReader;

// test
public class ConstantIntegerInfo extends ConstantInfo {
    long val;

    public ConstantIntegerInfo(ClassReader reader) {
	val = reader.readU4();
    }
}
