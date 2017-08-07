package classfile.constant;

import classfile.ClassReader;

// untest
public class ConstantDoubleInfo extends ConstantInfo {
    double val;

    public ConstantDoubleInfo(ClassReader reader) {
        long bits = reader.readU8();
        val = Double.longBitsToDouble(bits);
    }
}
