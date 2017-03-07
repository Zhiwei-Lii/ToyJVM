package classfile.constant;

import classfile.ClassReader;

public class ConstantLongInfo extends ConstantInfo {
    long val;

    // 可能出问题
    public ConstantLongInfo(ClassReader reader) {
	val = reader.readU8();
    }
}
